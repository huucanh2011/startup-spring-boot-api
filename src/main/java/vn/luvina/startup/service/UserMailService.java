package vn.luvina.startup.service;

public interface UserMailService {

  void sendMailRegister(String email);

  void sendMailForgotPassword(String email, String token);

}
