package bitcamp.web.app;

import bitcamp.web.admin.BoardController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  private static Log log = LogFactory.getLog(BoardController.class);

  public HelloController() {
    log.debug("HelloController() 호출됨!");
  }

  @RequestMapping("/hello")
  @ResponseBody
  public String hello() throws Exception {
    return "<html><body><h1>Hello!</h1></body></html>";
  }
}
