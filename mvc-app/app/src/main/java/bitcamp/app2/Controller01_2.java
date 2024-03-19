package bitcamp.app2;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/c01_2")
public class Controller01_2 {

  @GetMapping("h1")
  public String handler1(Model model) {

    model.addAttribute("name", "홍길동");
    model.addAttribute("age", 20);

    return "c01_1";
  }

  @GetMapping("h2")
  public void handler2(Model model) {
    model.addAttribute("name", "홍길동2");
    model.addAttribute("age", 30);

    // view name = c01_2/h2
    // 최종 계산된 JSP 경로 = /WEB-INF/jsp/ + c01_2/h2 + .jsp
    //                      = /WEB-INF/jsp/c01_2/h2.jsp
  }

  @GetMapping("h3")
  public Map<String, Object> handler3() {

    Map<String, Object> map = new HashMap<>();
    map.put("name", "홍길동3");
    map.put("age", 40);

    return map;
    // view name = c01_2/h3
    // JSP URL = /WEB-INF/jsp/c01_2/h3.jsp
  }

  @GetMapping("h4")
  public ModelAndView handler4() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("name", "홍길동3");
    mv.addObject("age", 40);
    mv.setViewName("c01_2/h4");

    // ModelAndView 객체에 값과 뷰 이름을 담아 리턴하면
    // 프론트 컨트롤러는 ModelAndView 객체에 보관되어 있는 값들을
    // ServletRequest 보관소로 옮기고,
    // 설정된 뷰 이름을 ViewResolver에게 넘긴다.
    //
    return mv;
  }

  @GetMapping("h5")
  public ModelAndView handler5() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("name", "홍길동3");
    mv.addObject("age", 40);

    // ModelAndView 객체에 값과 뷰 이름을 담아 리턴하면
    // 프론트 컨트롤러는 ModelAndView 객체에 보관되어 있는 값들을
    // ServletRequest 보관소로 옮기고,
    // 뷰 이름을 지정하지 않으면 request handler의 path를 ViewResolver에게 넘긴다.
    // => c01_2/h5
    // InternalResourceViewResolver 는 위 URL을 다음과 같이 바꾼다.
    // => /WEB-INF/jsp/c01_2/h5.jsp
    // 그런 후에 해당 URL의 JSP를 실행한다.
    return mv;
  }
}
