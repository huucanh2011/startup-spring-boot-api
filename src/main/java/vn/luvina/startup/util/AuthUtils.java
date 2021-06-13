package vn.luvina.startup.util;

import org.springframework.security.core.context.SecurityContextHolder;

import vn.luvina.startup.security.services.UserDetailsImpl;

public class AuthUtils {

  public static UserDetailsImpl getUserDetailsImplFormContext() {
    return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
