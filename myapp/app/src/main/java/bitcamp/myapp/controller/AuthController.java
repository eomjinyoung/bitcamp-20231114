package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthController {

  MemberDao memberDao;

  public AuthController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/auth/login")
  public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals("email")) {
            request.setAttribute("email", cookie.getValue());
            break;
          }
        }
      }
      return "/auth/form.jsp";
    }

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    String saveEmail = request.getParameter("saveEmail");
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
      request.getSession().setAttribute("loginUser", member);
    }
    return "/auth/login.jsp";
  }

  @RequestMapping("/auth/logout")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    return "redirect:/index.html";
  }
}
