package bitcamp.myapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private static final Log log = LogFactory.getLog(HomeController.class);

  @GetMapping("/home")
  public void home() {
    // return "home"; // ==> ThymeleafViewResolver가 처리한다.
  }

  @GetMapping("/home1")
  public String home1() {
    return "home.jsp"; // ==> InternalResourceViewResolver가 처리한다.
  }

  @GetMapping("/home2")
  public String home2() {
    return "home.html"; // ==> ThymeleafViewResolver가 처리한다.
  }
}
