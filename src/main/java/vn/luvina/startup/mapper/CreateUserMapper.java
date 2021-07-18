package vn.luvina.startup.mapper;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.user.UserRequestDto;
import vn.luvina.startup.model.User;

@Component
@RequiredArgsConstructor
public class CreateUserMapper {
    
    private final PasswordEncoder passwordEncoder;

    public User convertReqToUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setAvatarPath(userRequestDto.getAvatar_path());
        user.setAddress(userRequestDto.getAddress());
        user.setPhoneNumber(userRequestDto.getPhone_number());
        user.setDeliveryAddress(userRequestDto.getDelivery_address());
        user.setRole(userRequestDto.getRole());
        user.setStatus(userRequestDto.getStatus());
        return user;
    }

}
