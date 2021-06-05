package vn.luvina.startup.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vn.luvina.startup.dto.user.UserResponseDto;

@Data
public class JwtResponseDto {

  @ApiModelProperty(value = "token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjA2MmI5Nzk1LTViYmUtNGVkNS1hOTRmLWQ2MDQ5OTU4ODY0NSIsImlhdCI6MTYyMDg5Mjc5MCwiZXhwIjoxNjIxNDk3NTkwfQ.jyre9ZzZXx7eznXSfynBgryLodCY8t82qBxlGFZokmg")
  private String token;

  @ApiModelProperty(value = "type", example = "Bearer")
  private String type = "Bearer";

  private UserResponseDto user;

}
