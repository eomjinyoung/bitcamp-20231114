package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c04_1")
public class Controller04_1 {

  @GetMapping("h1") // ==> /c04_1/h1
  public String handler1() {
    System.out.println("c04_1.handler1() 호출");
    return "c04_1";
  }

  @GetMapping("a/h2") // ==> /c04_1/a/h2
  public String handler2() {
    System.out.println("c04_1.handler2() 호출");
    return "c04_1";
  }

  @GetMapping("b/h3") // ==> /c04_1/b/h3
  public String handler3() {
    System.out.println("c04_1.handler3() 호출");
    return "c04_1";
  }
}
