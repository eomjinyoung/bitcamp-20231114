package bitcamp.myapp;

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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {

  Prompt prompt = new Prompt(System.in);

  List<Board> boardRepository;
  List<Assignment> assignmentRepository;
  List<Member> memberRepository;
  List<Board> greetingRepository;

  MenuGroup mainMenu;

  App() {
    loadAssignment();
    loadMember();
    loadBoard();
    loadGreeting();
    prepareMenu();
  }

  public static void main(String[] args) {
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
    try (FileInputStream in0 = new FileInputStream("assignment.data");
        BufferedInputStream in1 = new BufferedInputStream(in0);
        ObjectInputStream in = new ObjectInputStream(in1)) {

      assignmentRepository = (List<Assignment>) in.readObject();

    } catch (Exception e) {
      assignmentRepository = new LinkedList<>();
      System.out.println("과제 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveAssignment() {
    try (FileOutputStream out0 = new FileOutputStream("assignment.data");
        BufferedOutputStream out1 = new BufferedOutputStream(out0);
        ObjectOutputStream out = new ObjectOutputStream(out1)) {

      out.writeObject(assignmentRepository);

    } catch (Exception e) {
      System.out.println("과제 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadMember() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("member.data")))) {

      memberRepository = (List<Member>) in.readObject();

    } catch (Exception e) {
      memberRepository = new ArrayList<>();
      System.out.println("회원 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveMember() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("member.data")))) {

      out.writeObject(memberRepository);

    } catch (Exception e) {
      System.out.println("회원 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadBoard() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("board.data")))) {

      boardRepository = (List<Board>) in.readObject();

    } catch (Exception e) {
      boardRepository = new LinkedList<>();
      System.out.println("게시글 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveBoard() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("board.data")))) {

      out.writeObject(boardRepository);

    } catch (Exception e) {
      System.out.println("게시글 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void loadGreeting() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("greeting.data")))) {

      greetingRepository = (List<Board>) in.readObject();

    } catch (Exception e) {
      greetingRepository = new LinkedList<>();
      System.out.println("가입인사 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  void saveGreeting() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("greeting.data")))) {

      out.writeObject(greetingRepository);

    } catch (Exception e) {
      System.out.println("가입인사 데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }


}
