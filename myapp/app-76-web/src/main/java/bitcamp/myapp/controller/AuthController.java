package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final Log log = LogFactory.getLog(this.getClass());
  MemberDao memberDao;

  public AuthController(MemberDao memberDao) {
    log.debug("AuthController() 호출됨!");
    this.memberDao = memberDao;
  }

  @GetMapping("form")
  public String form(
      @CookieValue(value = "email", required = false) String email,
      Model model) {
    model.addAttribute("email", email);
    return "/auth/form.jsp";
  }

  @PostMapping("login")
  public String login(
      String email,
      String password,
      String saveEmail,
      HttpServletResponse response,
      HttpSession session) throws Exception {

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member member = memberDao.findByEmailAndPassword(email, password);
    if (member != null) {
      session.setAttribute("loginUser", member);
    }
    return "/auth/login.jsp";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/index.html";
  }
}
