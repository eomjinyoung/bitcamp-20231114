package bitcamp.myapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final Log log = LogFactory.getLog(this.getClass());

  public HomeController() {
    log.debug("HomeController() 호출됨!");
  }

  @GetMapping("/home")
  public String home()
      throws Exception {
    return "/home.jsp";
  }
}
