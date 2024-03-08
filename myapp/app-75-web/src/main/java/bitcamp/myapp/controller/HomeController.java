package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
