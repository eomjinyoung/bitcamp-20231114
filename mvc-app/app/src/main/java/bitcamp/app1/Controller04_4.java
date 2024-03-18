package bitcamp.app1;

import java.beans.PropertyEditor;
import java.io.PrintWriter;
import java.sql.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c04_4")
public class Controller04_4 {
// 클라이언트가 보낸 요청 파라미터 값(String 타입)을
  // request handler의 아규먼트 타입(String, int, boolean 등)의 값으로 바꿀 때
  // primitive type에 대해서만 자동으로 변환해 준다.
  // 그 외의 타입에 대해서는 프로퍼티 에디터(타입 변환기)가 없으면 예외를 발생시킨다.

  // 테스트:
  // http://.../c04_4/h1?model=sonata&capacity=5&auto=true&createdDate=2019-4-19
  @GetMapping("h1")
  @ResponseBody
  public void handler1(
      PrintWriter out,
      String model,
      @RequestParam(defaultValue = "5") int capacity, // String ===> int : Integer.parseInt(String)
      boolean auto, // String ===> boolean : Boolean.parseBoolean(String)
      Date createdDate // 프로퍼티 에디터를 설정하지 않으면 변환 오류 발생
  ) {

    out.printf("model=%s\n", model);
    out.printf("capacity=%s\n", capacity);
    out.printf("auto=%s\n", auto);
    out.printf("createdDate=%s\n", createdDate);
  }

  // 테스트:
  // http://.../c04_4/h2?car=sonata,5,true,2019-4-19
  @GetMapping("h2")
  @ResponseBody
  public void handler2(PrintWriter out,
      // 콤마(,)로 구분된 문자열을 Car 객체로 변환하기?
      // => String ===> Car 프로퍼티 에디터를 등록하면 된다.
      @RequestParam("car") Car car) {

    out.println(car);
  }

  // 테스트:
  // http://.../c04_4/h3?engine=bitengine,3500,16
  @GetMapping("h3")
  @ResponseBody
  public void handler3(PrintWriter out,
      // 콤마(,)로 구분된 문자열을 Engine 객체로 변환하기?
      // => String ===> Engine 프로퍼티 에디터를 등록하면 된다.
      @RequestParam("engine") Engine engine) {

    out.println(engine);
  }
}
