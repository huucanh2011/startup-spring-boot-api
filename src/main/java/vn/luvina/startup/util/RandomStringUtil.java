package vn.luvina.startup.util;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomStringUtil {

  private static final String SECRET_STRING = "abcde12345";

  public static String generateRandomString(int n, String inputString) {
    String inputStringUpperCase = inputString.trim().toUpperCase().replaceAll(" ", "").concat(SECRET_STRING);

    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {
      int index = (int) (inputStringUpperCase.length() * Math.random());
      sb.append(inputStringUpperCase.charAt(index));
    }

    return sb.toString();
  }

  public static String generateToken() {
    final SecureRandom secureRandom = new SecureRandom();
    final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    final byte[] randomByte = new byte[32];
    secureRandom.nextBytes(randomByte);
    return base64Encoder.encodeToString(randomByte);
  }

}
