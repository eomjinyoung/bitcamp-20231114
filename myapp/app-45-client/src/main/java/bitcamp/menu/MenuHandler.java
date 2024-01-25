package bitcamp.menu;

// 메뉴를 실행시킬 때 작업을 수행할 객체의 사용 규칙 정의
// - 메뉴를 실행시키면, 해당 메뉴는 등록된 MenuHandler 객체를 실행한다.
//
public interface MenuHandler {

  // 사용자가 메뉴를 선택하면,
  // MenuItem 객체를 다음 규칙에 따라 메서드를 호출할 것이다.
  //
  void action(Menu menu);
}
