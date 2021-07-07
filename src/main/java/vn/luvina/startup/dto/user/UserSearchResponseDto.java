package vn.luvina.startup.dto.user;

import java.util.List;

import lombok.Data;
import vn.luvina.startup.dto.base.MetaResponse;

@Data
public class UserSearchResponseDto {
  
  private List<UserResponseDto> data;

  private MetaResponse meta;

}
