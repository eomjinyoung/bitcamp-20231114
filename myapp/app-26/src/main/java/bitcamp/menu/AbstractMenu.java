package bitcamp.menu;

public abstract class AbstractMenu implements Menu {

  private String title;

  public AbstractMenu(String title) {
    this.title = title;
  }

  @Override
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
