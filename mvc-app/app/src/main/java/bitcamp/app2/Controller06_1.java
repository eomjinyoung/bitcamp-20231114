package bitcamp.app2;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/c06_1")
public class Controller06_1 {

  @GetMapping("get")
  public void get() {
  }

  @PostMapping("post")
  public void post() {
  }

  @GetMapping("error1")
  public void error1() throws Exception {
    throw new Exception("request handler에서 Exception 발생!");
    // Request Handler에서 예외를 던졌을 때 처리 절차!
    // 1) 페이지 컨트롤러 안에 예외 처리기가 있다면,
    // => 그 메서드를 호출한다.
    // 2) @ControllerAdvice 객체에 예외 처리기가 있다면,
    // => 그 메서드를 호출한다.
    // 3) web.xml 에 지정된 오류 처리 기본 페이지가 설정되어 있다면,
    // => 그 페이지를 실행한다.
    // 4) 서블릿 컨테이너의 기본 오류 처리 페이지를 실행한다.
    //
  }

  @GetMapping("error2")
  public void error() throws Exception {
    throw new IOException("request handler에서 IOException 발생!");
  }

  @GetMapping("error3")
  public void error3() throws Exception {
    throw new SQLException("request handler에서 SQLException 발생!");
  }

  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception ex) {
    System.out.println("Controller06_1.exceptionHandler() 호출됨!");
    ModelAndView mv = new ModelAndView();
    mv.addObject("error", ex);
    mv.setViewName("error6");
    return mv;
  }
}
