package bitcamp.myapp.security;

import bitcamp.myapp.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final Log log = LogFactory.getLog(SecurityConfig.class);

  public SecurityConfig() {
    log.debug("SecurityConfig 객체 생성됨!");
  }

  // Spring Security를 처리할 필터 체인을 준비한다.
  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http
      //,HttpSessionCsrfTokenRepository sessionCsrfRepository
      //,CookieCsrfTokenRepository cookieCsrfRepository
      ) throws Exception {

    return http
        //.csrf().csrfTokenRepository(sessionCsrfRepository).and()
        //.csrf().csrfTokenRepository(cookieCsrfRepository).and()
        .authorizeHttpRequests(authorize -> authorize
            .mvcMatchers("/member/form", "/member/add", "/img/**", "/home", "/", "*/list", "*/view", "/logout").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(formLoginConfigurer ->
          formLoginConfigurer
              .loginPage("/auth/form") // 로그인 폼을 제공하는 URL
              .loginProcessingUrl("/auth/login") // 클라이언트가 로그인을 하기 위해 요청하는 URL (페이지 컨트롤러와 상관없다)
              .usernameParameter("email") // 로그인 수행할 때 사용할 사용자 아이디 또는 이메일(principal) 파라미터 명
              .passwordParameter("password") // 로그인 수행할 때 사용할 사용자 암호(credential) 파라미터 명
              .successForwardUrl("/auth/loginSuccess") // 로그인 성공 후 포워딩 할 URL
              .permitAll() // 모든 권한 부여
        )
        .logout(Customizer.withDefaults())
        // 로그아웃 기본 URL: /logout
        // CSRF가 활성화된 경우:
        // - POST 요청을 해야 한다.
        // - 서버에서 받은 CSRF 토큰을 요청 헤더에 넣어야 한다.
        .build();
  }


  //@Bean
  HttpSessionCsrfTokenRepository sessionCsrfRepository() {
    HttpSessionCsrfTokenRepository csrfRepository = new HttpSessionCsrfTokenRepository();

    // HTTP 헤더에서 토큰을 저장할 때 사용할 이름
//    csrfRepository.setHeaderName("X-CSRF-TOKEN"); // 기본 값: X-CSRF-TOKEN

    // URL 파라미터로 토큰을 전송할 때 사용할 이름
//    csrfRepository.setParameterName("_csrf"); // 기본 값: _csrf

    // 세션에 CSRF 토큰을 저장할 때 사용할 이름
    // => 기본 값: "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN"
    csrfRepository.setSessionAttributeName("CSRF_TOKEN");

    return csrfRepository;
  }

  //@Bean
  CookieCsrfTokenRepository cookieCsrfRepository() {
    CookieCsrfTokenRepository csrfRepository = new CookieCsrfTokenRepository();

    csrfRepository.setCookieHttpOnly(false); // 기본 값: true
//    csrfRepository.setHeaderName("X-CSRF-TOKEN"); // 기본 값: X-CSRF-TOKEN
//    csrfRepository.setParameterName("_csrf"); // 기본 값: _csrf
//    csrfRepository.setCookieName("XSRF-TOKEN"); // 기본 값: XSRF-TOKEN
//    csrfRepository.setCookiePath(request.getContextPath()); // 기본값: request.getContextPath()

    return csrfRepository;
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
    return new BCryptPasswordEncoder();
  }
}
