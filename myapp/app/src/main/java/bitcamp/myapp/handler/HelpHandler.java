package bitcamp.myapp.handler;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.util.Prompt;

public class HelpHandler extends AbstractMenuHandler {

  public HelpHandler(Prompt prompt) {
    super(prompt);
  }

  @Override
  protected void action() {
    System.out.println("도움말입니다.");
  }
}
