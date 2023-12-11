package bitcamp.myapp.menu;

// Composite 패턴에서 leaf 역할을 수행하는 클래스
// Leaf?
// - 하위 항목을 포함하지 않는 말단 객체
// - 예를들어 파일시스템에서 '파일'에 해당한다.
//
public class MenuItem implements Menu {

  String title;

  public MenuItem(String title) {
    this.title = title;
  }

  public void execute() {
    System.out.printf("[%s]\n", this.title);
  }
}