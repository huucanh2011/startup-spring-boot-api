package vn.luvina.startup.dto.user;

import java.util.UUID;

import lombok.Data;

@Data
public class UserResponseDto {

  private UUID id;

  private String name;

  private String email;

  private String avatarPath;

  private String address;

  private String phoneNumber;

  private String deliveryAddress;

  private String role;

}
