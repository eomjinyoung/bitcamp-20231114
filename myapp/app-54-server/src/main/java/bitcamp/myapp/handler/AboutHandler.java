package bitcamp.myapp.handler;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.util.Prompt;

public class AboutHandler extends AbstractMenuHandler {

  @Override
  protected void action(Prompt prompt) {
    prompt.println("비트캠프 네이버 데브옵스 과정 5기");
    prompt.println("MyAPP ver. 49");
    prompt.println("모든 권리는 비트캠프에 있습니다.");
  }
}
