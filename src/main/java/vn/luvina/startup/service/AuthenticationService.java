package vn.luvina.startup.service;

import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.auth.LoginRequestDto;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.dto.auth.UpdateDetailRequestDto;
import vn.luvina.startup.dto.auth.UpdatePasswordRequestDto;
import vn.luvina.startup.dto.base.MessageResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;

public interface AuthenticationService {

  RegisterResponseDto register(RegisterRequestDto registerRequestDto);

  JwtResponseDto login(LoginRequestDto loginRequestDto);

  JwtResponseDto me();

  UserResponseDto updateDetail(UpdateDetailRequestDto updateDetailRequestDto);

  MessageResponseDto updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto);

}
