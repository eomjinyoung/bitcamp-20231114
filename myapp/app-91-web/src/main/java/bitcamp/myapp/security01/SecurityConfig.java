package bitcamp.myapp.security01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    // 필터 체인에 들어갈 필터를 설정한다.
    // 1) 어떤 요청에 대해 시큐리티 필터를 적용할 지 설정한다.
    //    => 모든 요청에 대해 시큐리티 인증 필터를 통과하도록 설정한다.
    //       즉 인증받지 않은 사용자는 모든 요청이 거부된다.
//    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());

    // 2) 로그인 폼을 지정한다.
    //    => Spring Security가 만들어주는 로그인 폼을 그대로 사용한다.
//    http.formLogin(Customizer.withDefaults());

    // 위의 설정 코드를 메서드 체인 방식으로 호출한다.
    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults());

    // HttpSecurity 객체에 설정한대로 동작할 수 있는 필터를 구성한다.
    return http.build();
  }

  // 사용자 정보를 리턴해주는 객체
  @Bean
  public UserDetailsService userDetailsService() {

    // 로그인 사용자 정보
    UserDetails userDetails = User.withDefaultPasswordEncoder()
        .username("hong@test.com")
        .password("1111")
        .roles("USER")
        .build();

    // 메모리에 사용자 정보(UserDetails 객체)를 보관한다.
    return new InMemoryUserDetailsManager(userDetails);
  }
}
