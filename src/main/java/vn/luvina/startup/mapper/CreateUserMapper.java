package vn.luvina.startup.mapper;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.user.CreateUserRequestDto;
import vn.luvina.startup.enums.UserStatus;
import vn.luvina.startup.model.User;

@Component
@RequiredArgsConstructor
public class CreateUserMapper {
    
    private final PasswordEncoder passwordEncoder;

    public User convertReqToUser(CreateUserRequestDto createUserRequestDto){
        User user = new User();
        user.setName(createUserRequestDto.getName());
        user.setEmail(createUserRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        user.setAvatarPath(createUserRequestDto.getAvatarPath());
        user.setAddress(createUserRequestDto.getAddress());
        user.setPhoneNumber(createUserRequestDto.getPhoneNumber());
        user.setDeliveryAddress(createUserRequestDto.getDeliveryAddress());
        user.setRole(createUserRequestDto.getRole());
        user.setStatus(UserStatus.ACTIVED.toString());
        return user;
    }

}
