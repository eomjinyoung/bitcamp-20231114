package bitcamp.menu;

import bitcamp.myapp.vo.Member;
import bitcamp.util.AnsiEscape;
import bitcamp.util.Prompt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MenuGroup extends AbstractMenu {

  private List<Menu> menus = new LinkedList<>();

  private MenuGroup(String title) {
    super(title);
  }

  public static MenuGroup getInstance(String title) {
    return new MenuGroup(title);
  }

  @Override
  public void execute(Prompt prompt) throws Exception {

    prompt.pushPath(this.title);

    this.printMenu(prompt);

    while (true) {
      String input = prompt.input("%s%s>", getLoginUsername(prompt), prompt.getFullPath());

      if (input.equals("menu")) {
        this.printMenu(prompt);
        continue;
      } else if (input.equals("0")) {
        break;
      }

      try {
        int menuNo = Integer.parseInt(input);
        if (menuNo < 1 || menuNo > this.menus.size()) {
          System.out.println("메뉴 번호가 옳지 않습니다.");
          continue;
        }

        this.menus.get(menuNo - 1).execute(prompt);

      } catch (Exception e) {
        System.out.println("메뉴가 옳지 않습니다!");
      }
    }

    // 메뉴를 나갈 때 breadcrumb 메뉴 경로에서 메뉴 제목을 제거한다.
    prompt.popPath();
  }

  private String getLoginUsername(Prompt prompt) {
//    Member loginUser = prompt.getLoginUser();
//    Member loginUser = (Member) prompt.getAttribute("loginUser");
    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
    if (loginUser != null) {
      return AnsiEscape.ANSI_BOLD_RED + loginUser.getName() + ":" + AnsiEscape.ANSI_CLEAR;
    } else {
      return "";
    }
  }

  private void printMenu(Prompt prompt) {
    prompt.printf("[%s]\n", this.getTitle());

    Iterator<Menu> iterator = this.menus.iterator();
    int i = 1;
    while (iterator.hasNext()) {
      Menu menu = iterator.next();
      prompt.printf("%d. %s\n", i++, menu.getTitle());
    }

    prompt.printf("0. %s\n", "이전");
  }

  public void add(Menu menu) {
    this.menus.add(menu);
  }

  public MenuItem addItem(String title, MenuHandler handler) {
    MenuItem menuItem = new MenuItem(title, handler);
    this.add(menuItem);
    return menuItem;
  }

  public MenuGroup addGroup(String title) {
    MenuGroup menuGroup = new MenuGroup(title);
    this.add(menuGroup);
    return menuGroup;
  }

  public void remove(Menu menu) {
    this.menus.remove(menu);
  }
}
