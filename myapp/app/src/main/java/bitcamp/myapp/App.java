package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.handler.board.BoardAddHandler;
import bitcamp.myapp.handler.board.BoardDeleteHandler;
import bitcamp.myapp.handler.board.BoardListHandler;
import bitcamp.myapp.handler.board.BoardModifyHandler;
import bitcamp.myapp.handler.board.BoardRepository;
import bitcamp.myapp.handler.board.BoardViewHandler;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) throws Exception {
    Prompt prompt = new Prompt(System.in);
    //new MainMenu(prompt).execute();

    BoardRepository boardRepository = new BoardRepository();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup assignmentMenu = new MenuGroup("과제");
    assignmentMenu.add(new MenuItem("등록"));
    assignmentMenu.add(new MenuItem("조회"));
    assignmentMenu.add(new MenuItem("변경"));
    assignmentMenu.add(new MenuItem("삭제"));
    assignmentMenu.add(new MenuItem("목록"));
    mainMenu.add(assignmentMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new MenuItem("등록", new BoardAddHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("조회", new BoardViewHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("변경", new BoardModifyHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("목록", new BoardListHandler(boardRepository)));
    mainMenu.add(boardMenu);

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new MenuItem("등록"));
    memberMenu.add(new MenuItem("조회"));
    memberMenu.add(new MenuItem("변경"));
    memberMenu.add(new MenuItem("삭제"));
    memberMenu.add(new MenuItem("목록"));
    mainMenu.add(memberMenu);

    MenuGroup greetingMenu = new MenuGroup("가입인사");
    greetingMenu.add(new MenuItem("등록"));
    greetingMenu.add(new MenuItem("조회"));
    greetingMenu.add(new MenuItem("변경"));
    greetingMenu.add(new MenuItem("삭제"));
    greetingMenu.add(new MenuItem("목록"));
    mainMenu.add(greetingMenu);

    mainMenu.add(new MenuItem("도움말"));

    mainMenu.execute(prompt);

    prompt.close();
  }
}
