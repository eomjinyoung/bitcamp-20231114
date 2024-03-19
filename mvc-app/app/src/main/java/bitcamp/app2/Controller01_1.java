package bitcamp.app2;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c01_1")
public class Controller01_1 {

  @GetMapping("h1")
  public String handler1(Model model) {

    model.addAttribute("name", "홍길동");
    model.addAttribute("age", 20);

    return "/jsp/c01_1.jsp";

    // InternalResourceViewResolver로 교체한 다음의 JSP URL은?
    // => /WEB-INF/jsp2//jsp/c01_1.jsp.jsp
  }

  @GetMapping("h2")
  public void handler2(Model model) {
    model.addAttribute("name", "홍길동2");
    model.addAttribute("age", 30);

    // InternalResourceViewResolver로 교체한 다음은?
    // => 리턴 값이 없으면 요청 URL(/c01_1/h2)을 리턴 값으로 사용한다.
    // => 따라서 ViewResolver가 계산한 최종 URL은
    // /WEB-INF/jsp2/c01_1/h2.jsp
    //

  }

  @GetMapping("h3")
  public String handler3(Map<String, Object> map) {

    map.put("name", "홍길동3");
    map.put("age", 40);

    return "/WEB-INF/jsp/c01_1.jsp";
  }
}
