package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.ForgotPasswordRequestDto;
import vn.luvina.startup.dto.auth.ResetPasswordRequestDto;
import vn.luvina.startup.dto.base.MessageResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.PasswordReset;
import vn.luvina.startup.model.User;
import vn.luvina.startup.repository.PasswordResetRepository;
import vn.luvina.startup.repository.UserRepository;
import vn.luvina.startup.service.PasswordResetService;
import vn.luvina.startup.service.UserMailService;
import vn.luvina.startup.util.RandomStringUtil;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

  private final UserRepository userRepository;

  private final PasswordResetRepository passwordResetRepository;

  private final UserMailService userMailService;

  private final PasswordEncoder passwordEncoder;

  @Override
  public MessageResponseDto sendMail(ForgotPasswordRequestDto forgotPasswordRequestDto) {
    String email = forgotPasswordRequestDto.getEmail();
    if (!validateMail(email)) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_AUTH_003);
    }
    send(email);
    MessageResponseDto messageResponseDto = new MessageResponseDto();
    messageResponseDto.setMessage(StartupMessages.MSG_AUTH_002);
    return messageResponseDto;
  }

  private void send(String email) {
    String token = createToken(email);
    userMailService.sendMailForgotPassword(email, token);
  }

  @Transactional(readOnly = true)
  private String createToken(String email) {
    Optional<PasswordReset> passwordReset = passwordResetRepository.findById(email);
    if (passwordReset.isPresent()) {
      return passwordReset.get().getToken();
    }
    String token = RandomStringUtil.generateToken();
    saveToken(email, token);
    return token;
  }

  @Transactional
  private void saveToken(String email, String token) {
    PasswordReset passwordReset = new PasswordReset();
    passwordReset.setEmail(email);
    passwordReset.setToken(token);
    passwordResetRepository.save(passwordReset);
  }

  @Transactional(readOnly = true)
  private boolean validateMail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public MessageResponseDto resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
    String email = resetPasswordRequestDto.getEmail();
    String password = resetPasswordRequestDto.getPassword();
    String resetToken = resetPasswordRequestDto.getResetToken();
    List<PasswordReset> passwordResets = passwordResetRepository.findAllByEmailAndToken(email, resetToken);
    if (passwordResets.size() > 0) {
      User user = userRepository.findByEmail(email).get();
      user.setPassword(passwordEncoder.encode(password));
      userRepository.save(user);
      removePasswordReset(email);
      MessageResponseDto messageResponseDto = new MessageResponseDto();
      messageResponseDto.setMessage(StartupMessages.MSG_AUTH_003);
      return messageResponseDto;
    }
    throw new ServiceRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, StartupMessages.ERR_AUTH_004);
  }

  @Transactional
  private void removePasswordReset(String email) {
    passwordResetRepository.deleteById(email);
  }

}
