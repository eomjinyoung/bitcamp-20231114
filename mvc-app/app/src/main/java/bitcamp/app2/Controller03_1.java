package bitcamp.app2;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c03_1")
public class Controller03_1 {
  @GetMapping(value = "h1", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler1(HttpSession session) {
    session.setAttribute("name", "홍길동");
    session.setAttribute("age", "20");
    session.setAttribute("name2", "임꺽정");
    session.setAttribute("age2", "30");

    return "세션에 값을 보관했음!";
  }

  @GetMapping(value = "h2", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler2(HttpSession session) {
    return String.format("name=%s, age=%s, name2=%s, age2=%s, tel2=%s",
        session.getAttribute("name"),
        session.getAttribute("age"),
        session.getAttribute("name2"),
        session.getAttribute("age2"),
        session.getAttribute("tel2"));
  }

  @GetMapping(value = "h3", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler3(HttpSession session) {
    session.invalidate();
    return "세션을 무효화시켰음!";
  }

  @GetMapping(value = "h4", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler4(
      // 현재 페이지 컨트롤러의 @SessionAttributes 에 지정된 이름이 아니라면,
      // 프론트 컨트롤러는 요청 파라미터에서 해당 이름의 값을 찾아 넘겨준다.
      // 요청 파라미터에 해당 이름의 값이 없다면,
      // 프론트 컨트롤러는 빈 문자열을 넘겨준다.
      @ModelAttribute("name") String name,
      @ModelAttribute("age") String age,
      @ModelAttribute("name2") String name2,
      @ModelAttribute("age2") String age2) {

    return String.format("name=%s, age=%s, name2=%s, age2=%s",
        name, age, name2, age2);
  }
}
