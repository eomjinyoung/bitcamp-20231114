package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.handler.HelpHandler;
import bitcamp.myapp.handler.assignment.AssignmentAddHandler;
import bitcamp.myapp.handler.assignment.AssignmentDeleteHandler;
import bitcamp.myapp.handler.assignment.AssignmentListHandler;
import bitcamp.myapp.handler.assignment.AssignmentModifyHandler;
import bitcamp.myapp.handler.assignment.AssignmentViewHandler;
import bitcamp.myapp.handler.board.BoardAddHandler;
import bitcamp.myapp.handler.board.BoardDeleteHandler;
import bitcamp.myapp.handler.board.BoardListHandler;
import bitcamp.myapp.handler.board.BoardModifyHandler;
import bitcamp.myapp.handler.board.BoardViewHandler;
import bitcamp.myapp.handler.member.MemberAddHandler;
import bitcamp.myapp.handler.member.MemberDeleteHandler;
import bitcamp.myapp.handler.member.MemberListHandler;
import bitcamp.myapp.handler.member.MemberModifyHandler;
import bitcamp.myapp.handler.member.MemberViewHandler;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) throws Exception {
    Prompt prompt = new Prompt(System.in);
    //new MainMenu(prompt).execute();

    ObjectRepository boardRepository = new ObjectRepository();
    ObjectRepository assignmentRepository = new ObjectRepository();
    ObjectRepository memberRepository = new ObjectRepository();
    ObjectRepository greetingRepository = new ObjectRepository();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup assignmentMenu = new MenuGroup("과제");
    assignmentMenu.add(new MenuItem("등록", new AssignmentAddHandler(assignmentRepository, prompt)));
    assignmentMenu.add(new MenuItem("조회", new AssignmentViewHandler(assignmentRepository, prompt)));
    assignmentMenu.add(
        new MenuItem("변경", new AssignmentModifyHandler(assignmentRepository, prompt)));
    assignmentMenu.add(
        new MenuItem("삭제", new AssignmentDeleteHandler(assignmentRepository, prompt)));
    assignmentMenu.add(new MenuItem("목록", new AssignmentListHandler(assignmentRepository)));
    mainMenu.add(assignmentMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new MenuItem("등록", new BoardAddHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("조회", new BoardViewHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("변경", new BoardModifyHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteHandler(boardRepository, prompt)));
    boardMenu.add(new MenuItem("목록", new BoardListHandler(boardRepository)));
    mainMenu.add(boardMenu);

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new MenuItem("등록", new MemberAddHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("조회", new MemberViewHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("변경", new MemberModifyHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("삭제", new MemberDeleteHandler(memberRepository, prompt)));
    memberMenu.add(new MenuItem("목록", new MemberListHandler(memberRepository)));
    mainMenu.add(memberMenu);

    MenuGroup greetingMenu = new MenuGroup("가입인사");
    greetingMenu.add(new MenuItem("등록", new BoardAddHandler(greetingRepository, prompt)));
    greetingMenu.add(new MenuItem("조회", new BoardViewHandler(greetingRepository, prompt)));
    greetingMenu.add(new MenuItem("변경", new BoardModifyHandler(greetingRepository, prompt)));
    greetingMenu.add(new MenuItem("삭제", new BoardDeleteHandler(greetingRepository, prompt)));
    greetingMenu.add(new MenuItem("목록", new BoardListHandler(greetingRepository)));
    mainMenu.add(greetingMenu);

    mainMenu.add(new MenuItem("도움말", new HelpHandler()));

    mainMenu.execute(prompt);

    prompt.close();
  }
}
