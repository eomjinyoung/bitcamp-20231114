package bitcamp.menu;

import bitcamp.util.AnsiEscape;
import bitcamp.util.Prompt;

public abstract class AbstractMenuHandler implements MenuHandler {

  protected Prompt prompt;
  protected Menu menu;

  public AbstractMenuHandler() {
  }

  public AbstractMenuHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    this.printMenuTitle(menu.getTitle());
    this.menu = menu;
    this.action();
  }

  @Override
  public void action(Menu menu, Prompt prompt) {
    this.printMenuTitle(menu.getTitle(), prompt);
    this.menu = menu;
    this.action(prompt);
  }

  private void printMenuTitle(String title) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, title);
  }

  private void printMenuTitle(String title, Prompt prompt) {
    prompt.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, title);
  }

  protected void action() {
  }

  protected void action(Prompt prompt) {
  }
}
