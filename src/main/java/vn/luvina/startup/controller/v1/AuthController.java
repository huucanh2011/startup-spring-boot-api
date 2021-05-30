package vn.luvina.startup.controller.v1;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.auth.LoginRequestDto;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
    return new ResponseEntity<>(authenticationService.register(registerRequestDto), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
  }

  @GetMapping("/me")
  public ResponseEntity<JwtResponseDto> me() {
    return new ResponseEntity<>(authenticationService.me(), HttpStatus.OK);
  }

}
