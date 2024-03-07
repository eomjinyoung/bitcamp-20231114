package bitcamp.myapp.controller;

import org.springframework.stereotype.Component;

@Component
public class HomeController {

  @RequestMapping("/home")
  public String home()
      throws Exception {
    return "/home.jsp";
  }
}
