package vn.luvina.startup.dto.base;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseResponseDto {

  @ApiModelProperty(value = "message", example = "Đăng ký thành công.")
  private String message;

}
