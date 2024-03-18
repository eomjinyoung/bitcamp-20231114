package bitcamp.app1;

import java.io.PrintWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c04_6")
public class Controller04_6 {
  // 테스트:
  // http://.../c04_6/h1
  @GetMapping("h1")
  @ResponseBody
  public void handler1(
      PrintWriter out,
      @RequestHeader("Accept") String accept,
      @RequestHeader("User-Agent") String userAgent) {

    out.printf("Accept=%s\n", accept);
    out.printf("User-Agent=%s\n", userAgent);

    if (userAgent.matches(".*Edg.*")) {
      out.println("Edge");
    } else if (userAgent.matches(".*Chrome.*")) {
      out.println("chrome");
    } else if (userAgent.matches(".*Safari.*")) {
      out.println("safari");
    } else if (userAgent.matches(".*Firefox.*")) {
      out.println("firefox");
    } else {
      out.println("etc");
    }
  }
}
