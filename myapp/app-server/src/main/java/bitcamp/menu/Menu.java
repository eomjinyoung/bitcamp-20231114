package bitcamp.menu;

import bitcamp.util.Prompt;

// Menu를 처리하는 객체의 사용법을 정의한다.
//
public interface Menu {

  // 객체를 실행할 때 호출할 메서드를 선언한다.
  // 구현을 해서는 안된다.
  // => 추상 메서드
  void execute(Prompt prompt);

  String getTitle();
}
