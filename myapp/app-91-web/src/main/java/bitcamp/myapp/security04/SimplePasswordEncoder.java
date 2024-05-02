package bitcamp.myapp.security04;

import org.springframework.security.crypto.password.PasswordEncoder;

// 사용자가 입력한 암호와 DB에 저장된 암호를 비교하는 일을 수행
public class SimplePasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    // 사용자가 입력한 암호를 암호화하여 리턴한다.

    // 현재는 암호화하여 리턴하지 않고 암호화하기 전 상태 그대로 리턴한다.
    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    // DB에 보관된 암호와 사용자가 입력한 암호가 같은지를 비교하여 리턴한다.

    // 현재는 암호화를 따로 하지 않기 때문에 원래 암호 그대로 비교한다.
    return encodedPassword.equals(this.encode(rawPassword));
  }
}
