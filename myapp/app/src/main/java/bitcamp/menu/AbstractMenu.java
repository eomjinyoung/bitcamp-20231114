package bitcamp.menu;

public abstract class AbstractMenu implements Menu {

  String title;

  public AbstractMenu(String title) {
    this.title = title;
  }

  @Override
  public String getTitle() {
    return this.title;
  }
}
