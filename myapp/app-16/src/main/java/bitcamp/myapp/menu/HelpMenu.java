package bitcamp.myapp.menu;

import bitcamp.util.Prompt;

// 메뉴 객체의 사용 규칙에 따라 클래스를 정의한다.
//
public class HelpMenu implements Menu {

  String title;
  Prompt prompt;

  public HelpMenu(String title, Prompt prompt) {
    this.title = title;
    this.prompt = prompt;
  }

  public void execute() {
    System.out.printf("[%s]\n", this.title);
    System.out.println("도움말입니다.");
  }
}
