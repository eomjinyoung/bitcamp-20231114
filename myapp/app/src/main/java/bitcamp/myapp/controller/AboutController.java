package bitcamp.myapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

  private final Log log = LogFactory.getLog(this.getClass());

  public AboutController() {
    log.debug("AboutController() 호출됨!");
  }

  @GetMapping("/about")
  public String about()
      throws Exception {
    return "/about.jsp";
  }
}
