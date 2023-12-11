package bitcamp.myapp;

import bitcamp.myapp.menu.MainMenu;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) throws Exception {
    Prompt prompt = new Prompt(System.in);
    new MainMenu(prompt).execute();
    prompt.close();
  }
}
