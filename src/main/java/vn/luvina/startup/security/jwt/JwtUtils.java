package vn.luvina.startup.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import vn.luvina.startup.security.services.UserDetailsImpl;

@Component
public class JwtUtils {

  @Value("${startup.jwtSecret}")
  private String jwtSecret;

  @Value("${startup.jwtExpirationMs}")
  private Integer jwtExpiration;

  public String generateJwtToken(UserDetailsImpl userDetailsImpl) {
    Date now = new Date();
    Date expiredDate = new Date(now.getTime() + jwtExpiration);
    return Jwts.builder().setSubject((userDetailsImpl.getUsername())).setIssuedAt(now).setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      System.out.println("Invalid JWT signature: " + e.getMessage());
    } catch (MalformedJwtException e) {
      System.out.println("Invalid JWT token: " + e.getMessage());
    } catch (ExpiredJwtException e) {
      System.out.println("JWT token is expired: " + e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.out.println("JWT token is unsupported: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println("JWT claims string is empty: " + e.getMessage());
    }
    return false;
  }

}
