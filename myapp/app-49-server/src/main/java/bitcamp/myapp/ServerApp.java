package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.handler.AboutHandler;
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
import bitcamp.util.Prompt;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {

  ExecutorService executorService = Executors.newCachedThreadPool();

  BoardDao boardDao;
  BoardDao greetingDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;

  MenuGroup mainMenu;

  ServerApp() {
    prepareDatabase();
    prepareMenu();
  }

  public static void main(String[] args) {
    System.out.println("과제관리 시스템 서버 실행!");
    new ServerApp().run();
  }

  void prepareDatabase() {
    try {
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");
      //"jdbc:mysql://db-ld27b-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123");

      boardDao = new BoardDaoImpl(con, 1);
      greetingDao = new BoardDaoImpl(con, 2);
      assignmentDao = new AssignmentDaoImpl(con);
      memberDao = new MemberDaoImpl(con);

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignmentAddHandler(assignmentDao));
    assignmentMenu.addItem("조회", new AssignmentViewHandler(assignmentDao));
    assignmentMenu.addItem("변경", new AssignmentModifyHandler(assignmentDao));
    assignmentMenu.addItem("삭제", new AssignmentDeleteHandler(assignmentDao));
    assignmentMenu.addItem("목록", new AssignmentListHandler(assignmentDao));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(boardDao));
    boardMenu.addItem("조회", new BoardViewHandler(boardDao));
    boardMenu.addItem("변경", new BoardModifyHandler(boardDao));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao));
    boardMenu.addItem("목록", new BoardListHandler(boardDao));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberDao));
    memberMenu.addItem("조회", new MemberViewHandler(memberDao));
    memberMenu.addItem("변경", new MemberModifyHandler(memberDao));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao));
    memberMenu.addItem("목록", new MemberListHandler(memberDao));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(greetingDao));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao));
    greetingMenu.addItem("목록", new BoardListHandler(greetingDao));

    mainMenu.addItem("도움말", new HelpHandler());
    mainMenu.addItem("...대하여", new AboutHandler());
  }

  void run() {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      while (true) {
        Socket socket = serverSocket.accept();
        executorService.execute(() -> processRequest(socket));
      }

    } catch (Exception e) {
      System.out.println("서버 소켓 생성 오류!");
      e.printStackTrace();
    }
  }

  void processRequest(Socket socket) {
    try (Socket s = socket;
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        Prompt prompt = new Prompt(in, out)) {

      while (true) {
        try {
          mainMenu.execute(prompt);
          prompt.print("[[quit!]]");
          prompt.end();
          break;
        } catch (Exception e) {
          System.out.println("예외 발생!");
          e.printStackTrace();
        }
      }

    } catch (Exception e) {
      System.out.println("클라이언 통신 오류!");
      e.printStackTrace();
    }
  }

}
