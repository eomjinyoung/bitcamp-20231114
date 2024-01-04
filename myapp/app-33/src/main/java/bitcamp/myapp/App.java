package bitcamp.myapp;

import bitcamp.io.BufferedDataInputStream;
import bitcamp.io.BufferedDataOutputStream;
import bitcamp.menu.MenuGroup;
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
import bitcamp.myapp.vo.Assignment;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {

  Prompt prompt = new Prompt(System.in);

  List<Board> boardRepository = new LinkedList<>();
  List<Assignment> assignmentRepository = new LinkedList<>();
  List<Member> memberRepository = new ArrayList<>();
  List<Board> greetingRepository = new ArrayList<>();

  MenuGroup mainMenu;

  App() {
    prepareMenu();
    loadAssignment();
    loadMember();
    loadBoard();
    loadGreeting();
  }

  public static void main(String[] args) throws Exception {
    new App().run();
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("과제");
    assignmentMenu.addItem("등록", new AssignmentAddHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("조회", new AssignmentViewHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("변경", new AssignmentModifyHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("삭제", new AssignmentDeleteHandler(assignmentRepository, prompt));
    assignmentMenu.addItem("목록", new AssignmentListHandler(assignmentRepository, prompt));

    MenuGroup boardMenu = mainMenu.addGroup("게시글");
    boardMenu.addItem("등록", new BoardAddHandler(boardRepository, prompt));
    boardMenu.addItem("조회", new BoardViewHandler(boardRepository, prompt));
    boardMenu.addItem("변경", new BoardModifyHandler(boardRepository, prompt));
    boardMenu.addItem("삭제", new BoardDeleteHandler(boardRepository, prompt));
    boardMenu.addItem("목록", new BoardListHandler(boardRepository, prompt));

    MenuGroup memberMenu = mainMenu.addGroup("회원");
    memberMenu.addItem("등록", new MemberAddHandler(memberRepository, prompt));
    memberMenu.addItem("조회", new MemberViewHandler(memberRepository, prompt));
    memberMenu.addItem("변경", new MemberModifyHandler(memberRepository, prompt));
    memberMenu.addItem("삭제", new MemberDeleteHandler(memberRepository, prompt));
    memberMenu.addItem("목록", new MemberListHandler(memberRepository, prompt));

    MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
    greetingMenu.addItem("등록", new BoardAddHandler(greetingRepository, prompt));
    greetingMenu.addItem("조회", new BoardViewHandler(greetingRepository, prompt));
    greetingMenu.addItem("변경", new BoardModifyHandler(greetingRepository, prompt));
    greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingRepository, prompt));
    greetingMenu.addItem("목록", new BoardListHandler(greetingRepository, prompt));

    mainMenu.addItem("도움말", new HelpHandler(prompt));
  }

  void run() {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.out.println("예외 발생!");
      }
    }
    saveAssignment();
    saveMember();
    saveBoard();
    saveGreeting();
  }

  void loadAssignment() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("assignment.data")) {

//      long start = System.currentTimeMillis();
      int size = in.readInt();

      for (int i = 0; i < size; i++) {
        Assignment assignment = new Assignment();
        assignment.setTitle(in.readUTF());
        assignment.setContent(in.readUTF());
        assignment.setDeadline(Date.valueOf(in.readUTF()));
        assignmentRepository.add(assignment);
      }
//      long end = System.currentTimeMillis();
//      System.out.printf("걸린 시간: %d\n", end - start);

    } catch (Exception e) {
      System.out.println("과제 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveAssignment() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("assignment.data")) {
//      long start = System.currentTimeMillis();
      out.writeInt(assignmentRepository.size());

      for (Assignment assignment : assignmentRepository) {
        out.writeUTF(assignment.getTitle());
        out.writeUTF(assignment.getContent());
        out.writeUTF(assignment.getDeadline().toString());
      }

//      long end = System.currentTimeMillis();
//      System.out.printf("걸린 시간: %d\n", end - start);

    } catch (Exception e) {
      System.out.println("과제 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadMember() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("member.data")) {
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setCreatedDate(new java.util.Date(in.readLong()));
        memberRepository.add(member);
      }
    } catch (Exception e) {
      System.out.println("회원 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveMember() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("member.data")) {

      out.writeShort(memberRepository.size());

      for (Member member : memberRepository) {
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeLong(member.getCreatedDate().getTime());
      }

    } catch (Exception e) {
      System.out.println("회원 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadBoard() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("board.data")) {
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setCreatedDate(new java.util.Date(in.readLong()));
        boardRepository.add(board);
      }
    } catch (Exception e) {
      System.out.println("게시글 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveBoard() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("board.data")) {

      out.writeShort(boardRepository.size());

      for (Board board : boardRepository) {
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeLong(board.getCreatedDate().getTime());
      }

    } catch (Exception e) {
      System.out.println("게시글 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadGreeting() {
    try (BufferedDataInputStream in = new BufferedDataInputStream("greeting.data")) {
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setCreatedDate(new java.util.Date(in.readLong()));
        greetingRepository.add(board);
      }
    } catch (Exception e) {
      System.out.println("가입인사 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveGreeting() {
    try (BufferedDataOutputStream out = new BufferedDataOutputStream("greeting.data")) {

      out.writeShort(greetingRepository.size());

      for (Board board : greetingRepository) {
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeLong(board.getCreatedDate().getTime());
      }

    } catch (Exception e) {
      System.out.println("가입인사 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }


}
