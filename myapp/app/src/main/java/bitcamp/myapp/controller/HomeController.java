package bitcamp.myapp.controller;

import org.springframework.stereotype.Component;

@Component
public class HomeController {

  public HomeController() {
    System.out.println("HomeController() 호출됨!");
  }

  @RequestMapping("/home")
  public String home()
      throws Exception {
    return "/home.jsp";
  }
}
