package vn.luvina.startup.service;

import vn.luvina.startup.dto.auth.ForgotPasswordRequestDto;
import vn.luvina.startup.dto.auth.ResetPasswordRequestDto;
import vn.luvina.startup.dto.base.MessageResponseDto;

public interface PasswordResetService {

  MessageResponseDto sendMail(ForgotPasswordRequestDto forgotPasswordRequestDto);

  MessageResponseDto resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);

}
