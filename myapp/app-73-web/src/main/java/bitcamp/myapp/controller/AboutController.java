package bitcamp.myapp.controller;

import bitcamp.util.Component;

@Component
public class AboutController {

  @RequestMapping("/about")
  public String about()
      throws Exception {
    return "/about.jsp";
  }
}
