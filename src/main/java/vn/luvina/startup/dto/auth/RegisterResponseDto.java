package vn.luvina.startup.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.luvina.startup.dto.base.BaseResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterResponseDto extends BaseResponseDto {

  private UserResponseDto data;

}
