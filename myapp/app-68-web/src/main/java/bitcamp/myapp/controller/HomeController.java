package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements PageController {

  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return "/home.jsp";
  }
}
