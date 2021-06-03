package vn.luvina.startup.service.Impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.service.UserMailService;

@Service
@RequiredArgsConstructor
public class UserMailServiceImpl implements UserMailService {

  private final JavaMailSender mailSender;

  @Override
  public void sendMailRegister(String email) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(email);
    message.setSubject("Đăng ký thành công.");
    message.setText("Cảm ơn bạn đã đăng ký.");

    mailSender.send(message);
  }

  @Override
  public void sendMailForgotPassword(String email, String token) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(email);
    message.setSubject("Quên mật khẩu.");
    message.setText("Link: http://localhost:3000/response-password-reset?token=" + token);

    mailSender.send(message);
  }
}
