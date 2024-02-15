package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.mysql.AssignmentDaoImpl;
import bitcamp.myapp.dao.mysql.AttachedFileDaoImpl;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.MemberDaoImpl;
import bitcamp.myapp.handler.AboutHandler;
import bitcamp.myapp.handler.HelpHandler;
import bitcamp.myapp.handler.assignment.AssignmentAddHandler;
import bitcamp.myapp.handler.assignment.AssignmentDeleteHandler;
import bitcamp.myapp.handler.assignment.AssignmentListHandler;
import bitcamp.myapp.handler.assignment.AssignmentModifyHandler;
import bitcamp.myapp.handler.assignment.AssignmentViewHandler;
import bitcamp.myapp.handler.auth.LoginHandler;
import bitcamp.myapp.handler.auth.LogoutHandler;
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
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import bitcamp.util.TransactionManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {

  ExecutorService executorService = Executors.newCachedThreadPool();

  TransactionManager txManager;
  DBConnectionPool connectionPool;

  BoardDao boardDao;
  BoardDao greetingDao;
  AssignmentDao assignmentDao;
  MemberDao memberDao;
  AttachedFileDao attachedFileDao;

  MenuGroup mainMenu;

  App() {
    prepareDatabase();
    prepareMenu();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("과제관리 시스템 서버 실행!");

    // 톰캣 서버를 구동시키는 객체 준비
    Tomcat tomcat = new Tomcat();

    // 서버의 포트 번호 설정
    tomcat.setPort(8888);

    // 톰캣 서버를 실행하는 동안 사용할 임시 폴더 지정
    tomcat.setBaseDir("./temp");

    // 톰캣 서버의 연결 정보를 설정
    Connector connector = tomcat.getConnector();
    connector.setURIEncoding("UTF-8");

    // 톰캣 서버에 배포할 웹 애플리케이션의 환경 정보 준비
    StandardContext ctx = (StandardContext) tomcat.addWebapp(
        "/", // 컨텍스트 경로(웹 애플리케이션 경로)
        new File("src/main/webapp").getAbsolutePath() // 웹 애플리케이션 파일이 있는 실제 경로
    );
    ctx.setReloadable(true);

    // 웹 애플리케이션 기타 정보 설정
    WebResourceRoot resources = new StandardRoot(ctx);

    // 웹 애플리케이션의 서블릿 클래스 등록
    resources.addPreResources(new DirResourceSet(
        resources, // 루트 웹 애플리케이션 정보
        "/WEB-INF/classes", // 서블릿 클래스 파일의 위치 정보
        new File("build/classes/java/main").getAbsolutePath(), // 서블릿 클래스 파일이 있는 실제 경로
        "/" // 웹 애플리케이션 내부 경로
    ));

    // 웹 애플리케이션 설정 정보를 웹 애플리케이션 환경 정보에 등록
    ctx.setResources(resources);

    // 톰캣 서버 구동
    tomcat.start();

    // 톰캣 서버를 구동한 후 종료될 때까지 JVM을 끝내지 말고 기다린다.
    tomcat.getServer().await();

    System.out.println("서버 종료!");
  }

  void prepareDatabase() {
    try {
//      Connection con = DriverManager.getConnection(
//
      //"jdbc:mysql://db-ld27b-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123");

      connectionPool = new DBConnectionPool(
          "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");
      txManager = new TransactionManager(connectionPool);

      boardDao = new BoardDaoImpl(connectionPool, 1);
      greetingDao = new BoardDaoImpl(connectionPool, 2);
      assignmentDao = new AssignmentDaoImpl(connectionPool);
      memberDao = new MemberDaoImpl(connectionPool);
      attachedFileDao = new AttachedFileDaoImpl(connectionPool);

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    mainMenu.addItem("로그인", new LoginHandler(memberDao));
    mainMenu.addItem("로그아웃", new LogoutHandler());

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignmentAddHandler(txManager, assignmentDao));
    assignmentMenu.addItem("조회", new AssignmentViewHandler(assignmentDao));
    assignmentMenu.addItem("변경", new AssignmentModifyHandler(assignmentDao));
    assignmentMenu.addItem("삭제", new AssignmentDeleteHandler(assignmentDao));
    assignmentMenu.addItem("목록", new AssignmentListHandler(assignmentDao));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(txManager, boardDao, attachedFileDao));
    boardMenu.addItem("조회", new BoardViewHandler(boardDao, attachedFileDao));
    boardMenu.addItem("변경", new BoardModifyHandler(boardDao, attachedFileDao));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao, attachedFileDao));
    boardMenu.addItem("목록", new BoardListHandler(boardDao));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberDao));
    memberMenu.addItem("조회", new MemberViewHandler(memberDao));
    memberMenu.addItem("변경", new MemberModifyHandler(memberDao));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberDao));
    memberMenu.addItem("목록", new MemberListHandler(memberDao));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(txManager, greetingDao, attachedFileDao));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, attachedFileDao));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao, attachedFileDao));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao, attachedFileDao));
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

    } finally {
      connectionPool.closeAll();
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

    } finally {
      //threadConnection.remove();
    }
  }

}
