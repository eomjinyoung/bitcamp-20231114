package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c02_2")
public class Controller02_2 {
  @GetMapping
  @ResponseBody
  public String handler1(String name, int age) {
    // Query String 으로 값 받기
    return String.format("name=%s, age=%d", name, age);
  }

  @GetMapping(value = "{value}", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler2(
      @PathVariable("value") String value,
      @MatrixVariable(required = false) String name,
      @MatrixVariable(defaultValue = "20") int age
  ) {

    return String.format("value:%s \n name:%s, age:%d", value, name, age);
  }

  @GetMapping(value = "{team}/{task}", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler3(
      // 여러 개의 패스 변수가 있을 때 값을 꺼내는 방법
      @MatrixVariable String name,
      @MatrixVariable int qty,
      @MatrixVariable String title,
      @MatrixVariable int state
  ) {

    return String.format("team: %s(%d)\n task: %s, %d", name, qty, title, state);
  }

  // http://.../app2/c02_2/h4/name=teamA;qty=5/name=work1;qty=1
  @GetMapping(value = "h4/{team}/{task}", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler4(
      // 여러 개의 패스 변수가 있을 때 값을 꺼내는 방법
      // => 만약 항목의 이름이 같다면?
      @MatrixVariable(name = "name", pathVar = "team") String name1,
      @MatrixVariable(name = "qty", pathVar = "team") int qty1,
      @MatrixVariable(name = "name", pathVar = "task") String name2,
      @MatrixVariable(name = "qty", pathVar = "task") int qty2) {

    return String.format("team: %s(%d)\n task: %s, %d", name1, qty1, name2, qty2);
  }
}
