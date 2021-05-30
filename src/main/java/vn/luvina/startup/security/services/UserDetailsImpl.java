package vn.luvina.startup.security.services;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import vn.luvina.startup.model.User;

@Getter
public class UserDetailsImpl implements UserDetails {
  
  private static final long serialVersionUID = 1L;

  private UUID id;

  private String username;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(UUID id, String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(),
        Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getUsername() {
    return username;
  }

}
