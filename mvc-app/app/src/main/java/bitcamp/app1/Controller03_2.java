package bitcamp.app1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c03_2")
public class Controller03_2 {
  @RequestMapping(method = RequestMethod.GET, headers = "name")
  @ResponseBody
  public String handler1() {
    return "handler1";
  }

  @RequestMapping(method = RequestMethod.GET, headers = "age")
  @ResponseBody
  public String handler2() {
    return "handler2";
  }

  @GetMapping(headers = {"age", "name"})
  @ResponseBody
  public String handler3() {
    return "handler3";
  }

  @GetMapping
  @ResponseBody
  public String handler4() {
    return "handler4";
  }
}
