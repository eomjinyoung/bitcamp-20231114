package bitcamp.myapp.controller;

public class HomeController {

  @RequestMapping("/home")
  public String home()
      throws Exception {
    return "/home.jsp";
  }
}
