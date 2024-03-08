package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

  public AboutController() {
    System.out.println("AboutController() 호출됨!");
  }

  @RequestMapping("/about")
  public String about()
      throws Exception {
    return "/about.jsp";
  }
}
