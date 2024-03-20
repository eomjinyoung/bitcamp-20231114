package bitcamp.app2;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception ex) {
    System.out.println("GlobalControllerAdvice.exceptionHandler() 호출됨!");
    ModelAndView mv = new ModelAndView();
    mv.addObject("error", ex);
    mv.setViewName("error3");
    return mv;
  }
}
