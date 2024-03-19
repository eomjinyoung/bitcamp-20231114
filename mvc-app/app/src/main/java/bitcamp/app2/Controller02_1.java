package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c02_1")
public class Controller02_1 {

  @GetMapping
  @ResponseBody
  public String handler1(String name, int age) {
    return String.format("name=%s, age=%d", name, age);
  }

  @GetMapping("{name}/{age}")
  @ResponseBody
  public String handler2(
      @PathVariable String name,
      @PathVariable int age
  ) {
    return String.format("name=%s, age=%d", name, age);
  }

  @GetMapping("{name}_{age}")
  @ResponseBody
  public String handler3(
      @PathVariable String name,
      @PathVariable int age
  ) {
    return String.format("name=%s, age=%d", name, age);
  }
}
