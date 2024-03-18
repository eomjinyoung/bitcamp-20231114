package bitcamp.app1;

import java.io.PrintWriter;
import java.sql.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c04_5")
public class Controller04_5 {
  // 테스트:
  //    http://.../c04_5/h1?model=sonata&capacity=5&auto=true&createdDate=2019-4-19
  @GetMapping("h1")
  @ResponseBody
  public void handler1(
      PrintWriter out,
      String model,
      int capacity,
      boolean auto,
      Date createdDate
  ) {

    out.printf("model=%s\n", model);
    out.printf("capacity=%s\n", capacity);
    out.printf("auto=%s\n", auto);
    out.printf("createdDate=%s\n", createdDate);
  }

  //테스트:
  //    http://.../c04_5/h2?car=sonata,5,true,2019-4-19
  @GetMapping("h2")
  @ResponseBody
  public void handler2(
      PrintWriter out,
      @RequestParam("car") Car car) {

    out.println(car);
  }

  //테스트:
  //    http://.../c04_5/h3?engine=bitengine,3500,16
  @GetMapping("h3")
  @ResponseBody
  public void handler3(
      PrintWriter out,
      @RequestParam("engine") Engine engine) {

    out.println(engine);
  }
}
