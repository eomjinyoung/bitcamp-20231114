package bitcamp.menu;

import bitcamp.util.AnsiEscape;
import bitcamp.util.Prompt;

public abstract class AbstractMenuHandler implements MenuHandler {

  protected Menu menu;

  @Override
  public void action(Menu menu, Prompt prompt) {
    this.printMenuTitle(menu.getTitle(), prompt);
    this.menu = menu;
    this.action(prompt);
  }

  private void printMenuTitle(String title, Prompt prompt) {
    prompt.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, title);
  }

  protected abstract void action(Prompt prompt);
}
