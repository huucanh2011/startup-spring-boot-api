package vn.luvina.startup.security.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.enums.UserStatus;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.User;
import vn.luvina.startup.repository.UserRepository;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      if (user.get().getStatus().toLowerCase().equals(UserStatus.BLOCKED.toString().toLowerCase())) {
        throw new ServiceRuntimeException(HttpStatus.FORBIDDEN, StartupMessages.ERR_AUTH_006);
      }
      return UserDetailsImpl.build(user.get());
    }
    throw new UsernameNotFoundException(email);
  }

}
