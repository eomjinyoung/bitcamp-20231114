package bitcamp.myapp.handler;

import bitcamp.menu.MenuHandler;

public class HelpHandler implements MenuHandler {

  @Override
  public void action() {
    System.out.println("도움말입니다.");
  }
}
