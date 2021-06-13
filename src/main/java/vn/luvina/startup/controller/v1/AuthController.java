package vn.luvina.startup.controller.v1;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.ForgotPasswordRequestDto;
import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.auth.LoginRequestDto;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.dto.auth.ResetPasswordRequestDto;
import vn.luvina.startup.dto.auth.UpdateDetailRequestDto;
import vn.luvina.startup.dto.auth.UpdatePasswordRequestDto;
import vn.luvina.startup.dto.base.MessageResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.service.AuthenticationService;
import vn.luvina.startup.service.PasswordResetService;
import vn.luvina.startup.util.StartupMessages;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;

  private final PasswordResetService passwordResetService;

  @PostMapping("/register")
  @ApiOperation("Đăng ký")
  @ApiResponses({ @ApiResponse(code = 200, message = ""),
      @ApiResponse(code = 401, message = StartupMessages.ERR_AUTH_001) })
  public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
    return new ResponseEntity<>(authenticationService.register(registerRequestDto), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  @ApiOperation("Đăng nhập")
  @ApiResponses({ @ApiResponse(code = 200, message = ""),
      @ApiResponse(code = 401, message = StartupMessages.ERR_AUTH_001) })
  public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
  }

  @PostMapping("/me")
  @ApiOperation("Lấy thông tin đăng nhập hiện tại.")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<JwtResponseDto> me() {
    return new ResponseEntity<>(authenticationService.me(), HttpStatus.OK);
  }

  @PostMapping("/forgot-password")
  @ApiOperation("Quên mật khẩu.")
  @ApiResponses({ @ApiResponse(code = 200, message = StartupMessages.MSG_AUTH_002) })
  public ResponseEntity<MessageResponseDto> forgotPassword(
      @Valid @RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) {
    return new ResponseEntity<>(passwordResetService.sendMail(forgotPasswordRequestDto), HttpStatus.OK);
  }

  @PutMapping("/reset-password")
  @ApiOperation("Reset password.")
  @ApiResponses({ @ApiResponse(code = 200, message = StartupMessages.MSG_AUTH_003) })
  public ResponseEntity<MessageResponseDto> resetPassword(
      @Valid @RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
    return new ResponseEntity<>(passwordResetService.resetPassword(resetPasswordRequestDto), HttpStatus.OK);
  }

  @PutMapping("/update-detail")
  @ApiOperation("Cập nhật thông tin tài khoản.")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<UserResponseDto> updateDetail(
      @Valid @RequestBody UpdateDetailRequestDto updateDetailRequestDto) {
    return new ResponseEntity<>(authenticationService.updateDetail(updateDetailRequestDto), HttpStatus.OK);
  }

  @PutMapping("/update-password")
  @ApiOperation("Cập nhật mật khẩu.")
  @ApiResponses({ @ApiResponse(code = 200, message = StartupMessages.MSG_AUTH_003) })
  public ResponseEntity<MessageResponseDto> updatePassword(
      @Valid @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
    return new ResponseEntity<>(authenticationService.updatePassword(updatePasswordRequestDto), HttpStatus.OK);
  }

}
