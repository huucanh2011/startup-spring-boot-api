package vn.luvina.startup.service;

import vn.luvina.startup.dto.auth.ForgotPasswordRequestDto;
import vn.luvina.startup.dto.auth.ForgotPasswordResponseDto;

public interface PasswordResetService {

  ForgotPasswordResponseDto sendMail(ForgotPasswordRequestDto forgotPasswordRequestDto);

}
