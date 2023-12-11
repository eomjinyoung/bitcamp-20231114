package bitcamp.myapp.menu;

import bitcamp.util.Prompt;

// 메뉴 객체의 사용 규칙에 따라 클래스를 정의한다.
//
public class CalculatorMenu implements Menu {

  String title;
  Prompt prompt;

  public CalculatorMenu(String title, Prompt prompt) {
    this.title = title;
    this.prompt = prompt;
  }

  public void execute() {
    System.out.printf("[%s]\n", this.title);
    int a = prompt.inputInt("a? ");
    int b = prompt.inputInt("b? ");
    System.out.printf("%d + %d = %d\n", a, b, (a + b));
  }
}
