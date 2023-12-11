package bitcamp.myapp.menu;

// Composite 패턴에서 '복합 객체(composite object)' 역할을 하는 클래스
// - 다른 Menu 객체를 포함한다.
public class MenuGroup implements Menu {

  String title;
  Menu[] menus = new Menu[10];
  int menuSize;

  public MenuGroup(String title) {
    this.title = title;
  }

  @Override // 인터페이스나 수퍼 클래스의 메서드를 정의하겠다고 컴파일러에게 알린다.
  public void execute() {
    System.out.printf("[%s]\n", this.title);
  }

  public void add(Menu menu) {

  }

  public void remove(Menu menu) {
    
  }
}
