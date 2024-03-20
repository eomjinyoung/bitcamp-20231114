package bitcamp.app2;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error2")
public class ControllerError {

  @RequestMapping
  public ModelAndView error(HttpServletRequest request) {
    // 서블릿 컨테이너가 서블릿을 실행하다가 예외가 발생하면
    // ServletRequest 보관소에 다음 이름으로 오류의 상태나 그 내용을 저장한다.
    // 그 값을 꺼내서 JSP가 출력하면 된다.
    ModelAndView mv = new ModelAndView();
    mv.addObject("status", request.getAttribute("javax.servlet.error.status_code"));
    mv.addObject("reason", request.getAttribute("javax.servlet.error.message"));
    mv.setViewName("error2");
    return mv;
  }
}
