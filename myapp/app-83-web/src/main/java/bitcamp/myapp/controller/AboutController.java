package bitcamp.myapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

  private static final Log log = LogFactory.getLog(AboutController.class);

  @GetMapping("/about")
  public void about() {
  }
}
