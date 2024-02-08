package bitcamp.menu;

import bitcamp.util.Prompt;

public interface Menu {

  void execute(Prompt prompt) throws Exception;

  String getTitle();
}
