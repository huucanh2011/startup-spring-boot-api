package vn.luvina.startup.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "name không được trống.")
    @Size(message = "name tối đa 40 kí tự.", max = 40)
    private String name;

    @ApiModelProperty(value = "email", example = "example@example.xyz")
    @NotBlank(message = "Email không được trống.")
    @Email(message = "Email không đúng định dạng.")
    @Size(message = "Email tối đa 60 kí tự.", max = 60)
    private String email;

    @ApiModelProperty(value = "password", example = "password")
    @NotBlank(message = "Mật khẩu không được trống.")
    @Size(message = "Mật khẩu từ 6 đến 60 kí tự.", min = 6, max = 60)
    private String password;

    @Size(message = "avatar_path tối đa 255 kí tự.", max = 255)
    private String avatar_path;

    @Size(message = "Địa chỉ tối đa 100 kí tự.", max = 100)
    private String address;

    @Size(message = "Số điện thoại tối đa 11 kí tự.", max = 11)
    private String phone_number;

    @Size(message = "Địa chỉ giao hàng tối đa 100 kí tự.", max = 100)
    private String delivery_address;

    private String role;
    
    private String status;
}
