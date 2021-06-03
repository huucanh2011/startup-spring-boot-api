package vn.luvina.startup.service.Impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.ForgotPasswordRequestDto;
import vn.luvina.startup.dto.auth.ForgotPasswordResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.PasswordReset;
import vn.luvina.startup.model.User;
import vn.luvina.startup.repository.PasswordResetRepository;
import vn.luvina.startup.repository.UserRepository;
import vn.luvina.startup.service.PasswordResetService;
import vn.luvina.startup.service.UserMailService;
import vn.luvina.startup.util.RandomStringUtil;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

  private final UserRepository userRepository;

  private final PasswordResetRepository passwordResetRepository;

  private final UserMailService userMailService;

  @Override
  public ForgotPasswordResponseDto sendMail(ForgotPasswordRequestDto forgotPasswordRequestDto) {
    String email = forgotPasswordRequestDto.getEmail();
    if (!validateMail(email)) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, "Không tìm thấy email.");
    }
    send(email);
    ForgotPasswordResponseDto forgotPasswordResponseDto = new ForgotPasswordResponseDto();
    forgotPasswordResponseDto
        .setMessage("Link reset mật khẩu được gửi thành công, vui lòng kiểm tra hộp thư đến của bạn.");
    return forgotPasswordResponseDto;
  }

  private void send(String email) {
    String token = createToken(email);
    userMailService.sendMailForgotPassword(email, token);
  }

  private String createToken(String email) {
    Optional<PasswordReset> passwordReset = passwordResetRepository.findById(email);
    if (passwordReset.isPresent()) {
      return passwordReset.get().getToken();
    }
    String token = RandomStringUtil.generateToken();
    saveToken(email, token);
    return token;
  }

  private void saveToken(String email, String token) {
    PasswordReset passwordReset = new PasswordReset();
    passwordReset.setEmail(email);
    passwordReset.setToken(token);
    passwordResetRepository.save(passwordReset);
  }

  private boolean validateMail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return true;
    }
    return false;
  }

}
