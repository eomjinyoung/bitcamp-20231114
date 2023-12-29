package bitcamp.menu;

import bitcamp.util.Stack;

public abstract class AbstractMenu implements Menu {

  protected Stack<String> breadcrumb;
  String title;

  public AbstractMenu(String title, Stack<String> breadcrumb) {
    this.title = title;
    this.breadcrumb = breadcrumb;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  public String getMenuPath() {
    return String.join("/", breadcrumb.toArray(new String[0]));
  }
}
