package bitcamp.app1;

import java.io.PrintWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c04_3")
public class Controller04_3 {
  @GetMapping("h1")
  @ResponseBody
  public void handler1(
      PrintWriter out,

      String model,

      String maker,

      @RequestParam(defaultValue = "100") int capacity, // 프론트 컨트롤러가 String 값을 int로 변환해 준다.
      // 단 변환할 수 없을 경우 예외가 발생한다.

      boolean auto,
      // 프론트 컨트롤러가 String 값을 boolean으로 변환해 준다.
      // 단 변환할 수 없을 경우 예외가 발생한다.
      // "true", "false"는 대소문자 구분없이 true, false로 변환해 준다.
      // 1 ==> true, 0 ==> false 로 변환해 준다. 그 외 숫자는 예외 발생!

      Car car
      // 아규먼트가 값 객체이면 요청 파라미터 중에서 값 객체의 프로퍼티 이름과 일치하는
      // 항목에 대해 값을 넣어준다.
      // 값 객체 안에 또 값 객체가 있을 때는 OGNL 방식으로 요청 파라미터 값을
      // 지정하면 된다.
      // 예) ...&engine.model=ok&engine.cc=1980&engine.valve=16
  ) {

    out.printf("model=%s\n", model);
    out.printf("maker=%s\n", maker);
    out.printf("capacity=%s\n", capacity);
    out.printf("auto=%s\n", auto);
    out.printf("car=%s\n", car);
  }
}
