package bitcamp.menu;

import bitcamp.util.AnsiEscape;

public abstract class AbstractMenuHandler implements MenuHandler {

  @Override
  public void action(Menu menu) {
    printMenuTitle(menu.getTitle());

    // Menu를 실행할 때 이 메서드가 호출되면
    // 즉시 서브 클래스의 다음 메서드를 호출한다.
    action();
  }

  private void printMenuTitle(String title) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, title);
  }

  // 서브 클래스가 구현해야 할 메서드
  public abstract void action();
}
