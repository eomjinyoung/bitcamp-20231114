package bitcamp.app1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c03_3")
public class Controller03_3 {

  @GetMapping("text")
  @ResponseBody
  public String text() {
    return "text";
  }

  @GetMapping("html")
  @ResponseBody
  public String html() {
    return "<html><body><h1>text</h1></body></html>";
  }

  @GetMapping("json")
  @ResponseBody
  public String json() {
    return "{\"title\":\"text\"}";
  }


  @GetMapping(produces = "text/plain")
  @ResponseBody
  public String handler1() {
    return "text";
  }

  @GetMapping(produces = "text/html")
  @ResponseBody
  public String handler2() {
    return "<html><body><h1>text</h1></body></html>";
  }

  @GetMapping(produces = "application/json")
  @ResponseBody
  public String handler3() {
    return "{\"title\":\"text\"}";
  }

  @GetMapping(produces = "text/csv")
  @ResponseBody
  public String handler4() {
    return "1,hong,20,female";
  }

  @GetMapping
  @ResponseBody
  public String handler5() {
    return "other...";
  }
}
