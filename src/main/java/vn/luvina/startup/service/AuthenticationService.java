package vn.luvina.startup.service;

import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.auth.LoginRequestDto;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;

public interface AuthenticationService {

  RegisterResponseDto register(RegisterRequestDto registerRequestDto);

  JwtResponseDto login(LoginRequestDto loginRequestDto);

  JwtResponseDto me();
}
