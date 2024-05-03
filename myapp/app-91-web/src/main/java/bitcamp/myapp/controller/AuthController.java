package bitcamp.myapp.controller;

import bitcamp.myapp.security.MemberUserDetails;
import bitcamp.myapp.service.MemberService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

  private static final Log log = LogFactory.getLog(AuthController.class);
  private final MemberService memberService;

  @GetMapping("form")
  public void form(@CookieValue(required = false) String email, Model model) {
    model.addAttribute("email", email);
  }

  @RequestMapping("loginSuccess")
  public String loginSuccess(
      String saveEmail,
      @AuthenticationPrincipal MemberUserDetails principal,
      HttpServletResponse response,
      CsrfToken csrfToken,
      HttpSession session) throws Exception {
    log.debug("로그인 성공!!!");

    log.debug(saveEmail);
    log.debug(principal);

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", principal.getUsername());
      cookie.setMaxAge(60 * 60 * 24 * 7);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    log.debug(csrfToken.getHeaderName());
    log.debug(csrfToken.getParameterName());
    log.debug(csrfToken.getToken());

    session.setAttribute("loginUser", principal.getMember());

    return "redirect:/index.html";
  }
}
