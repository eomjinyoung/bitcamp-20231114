package bitcamp.myapp.security05;

import bitcamp.myapp.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

  private static final Log log = LogFactory.getLog(SecurityConfig.class);

  public SecurityConfig() {
    log.debug("SecurityConfig 객체 생성됨!");
  }

  // Spring Security를 처리할 필터 체인을 준비한다.
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .mvcMatchers("/member/form", "/member/add", "/img/**", "/home", "/").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(formLoginConfigurer -> {
            formLoginConfigurer
                .loginPage("/auth/form") // 로그인 폼을 제공하는 URL
                .loginProcessingUrl("/auth/login") // 클라이언트가 로그인을 하기 위해 요청하는 URL (페이지 컨트롤러와 상관없다)
                .usernameParameter("email") // 로그인 수행할 때 사용할 사용자 아이디 또는 이메일(principal) 파라미터 명
                .passwordParameter("password") // 로그인 수행할 때 사용할 사용자 암호(credential) 파라미터 명
                .defaultSuccessUrl("/home", true) // 로그인 성공 후 리다이렉트 할 URL
                .permitAll(); // 모든 권한 부여
        });
    return http.build();
  }

  // 사용자 정보를 리턴해주는 객체
  @Bean
  public UserDetailsService userDetailsService(MemberService memberService) {
    // 우리가 만든 UserDetailsService 객체를 사용한다.
    // => DB에서 사용자 정보를 가져올 것이다.
    return new MyUserDetailsService(memberService);
  }

  // 로그인 폼에서 입력한 암호와 DB에서 꺼낸 암호가 같은지 비교하는 객체를 준비한다.
  // => Spring Security는 이 객체를 사용하여 암호를 비교한다.
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new SimplePasswordEncoder();
  }
}
