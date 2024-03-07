package bitcamp.myapp.controller;

import bitcamp.util.Component;

@Component
public class HomeController {

  @RequestMapping("/home")
  public String home()
      throws Exception {
    return "/home.jsp";
  }
}
