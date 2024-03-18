package bitcamp.app1;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalInitBinder {

  // 페이지 컨트롤러의 request handler를 호출할 때
  // 요청 파라미터의 값을 request handler의 아규먼트로 바꿀 때 마다 호출되는 메서드
  // => 이 메서드에서 파라미터 값을 형변환시키는 도구를 설정한다.
  @InitBinder
  public void initBinder(WebDataBinder 데이터변환등록기) {
    데이터변환등록기.registerCustomEditor(
        java.sql.Date.class, // String 값을 어떤 타입으로 바꿀 것인지 지정
        new DatePropertyEditor() // String 값을 해당 타입으로 변환해줄 변환기 지정
    );

    데이터변환등록기.registerCustomEditor(
        Car.class, // String 값을 Car 객체로 만들 것이라고 지정
        new CarPropertyEditor() // String 값을 Car 객체로 변환해줄 변환기 지정
    );

    데이터변환등록기.registerCustomEditor(
        Engine.class, // String 값을 Engine 객체로 만들 것이라고 지정
        new EnginePropertyEditor() // String 값을 Engine 객체로 변환해줄 변환기 지정
    );
  }
}
