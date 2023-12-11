package bitcamp.myapp.menu;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardMenu implements Menu {

  // 의존 객체(Dependency Object ==> dependency);
  // - 클래스가 작업을 수행할 때 사용하는 객체
  Prompt prompt;

  String title;
  Board[] boards = new Board[3];
  int length = 0;

  // BoardMenu 인스턴스를 생성할 때 반드시 게시판 제목을 설정하도록 강요한다.
  // 생성자란(constructor)?
  // => 인스턴스를 사용하기 전에 유효한 상태로 설정하는 작업을 수행하는 메서드
  public BoardMenu(String title, Prompt prompt) {
    this.title = title;
    this.prompt = prompt;
  }

  void printMenu() {
    System.out.printf("[%s]\n", this.title);
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("5. 목록");
    System.out.println("0. 이전");
  }

  public void execute() {
    this.printMenu();
    while (true) {
      String input = this.prompt.input("메인/%s> ", this.title);

      switch (input) {
        case "1":
          this.add();
          break;
        case "2":
          this.view();
          break;
        case "3":
          this.modify();
          break;
        case "4":
          this.delete();
          break;
        case "5":
          this.list();
          break;
        case "0":
          return;
        case "menu":
          this.printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  void add() {
    System.out.println("게시글 등록:");

    if (this.length == this.boards.length) {
      int oldSize = this.boards.length;
      int newSize = oldSize + (oldSize >> 1);

      Board[] arr = new Board[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = this.boards[i];
      }

      this.boards = arr;
    }

    Board board = new Board();
    board.title = this.prompt.input("제목? ");
    board.content = this.prompt.input("내용? ");
    board.writer = this.prompt.input("작성자? ");
    board.createdDate = this.prompt.input("작성일? ");

    this.boards[this.length++] = board;
  }

  void list() { // 논스태틱 메서드 == 인스턴스 메서드
    System.out.println("게시글 목록:");
    System.out.printf("%-20s\t%10s\t%s\n", "Title", "Writer", "Date");

    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      System.out.printf("%-20s\t%10s\t%s\n", board.title, board.writer, board.createdDate);
    }
  }

  void view() {
    System.out.println("게시글 조회:");

    int index = this.prompt.inputInt("번호? ");
    if (index < 0 || index >= this.length) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = this.boards[index];
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createdDate);
  }

  void modify() {
    System.out.println("게시글 변경:");

    int index = this.prompt.inputInt("번호? ");
    if (index < 0 || index >= this.length) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = this.boards[index];
    board.title = this.prompt.input("제목(%s)? ", board.title);
    board.content = this.prompt.input("내용(%s)? ", board.content);
    board.writer = this.prompt.input("작성자(%s)? ", board.writer);
    board.createdDate = this.prompt.input("작성일(%s)? ", board.createdDate);
  }

  void delete() {
    System.out.println("게시글 삭제:");

    int index = this.prompt.inputInt("번호? ");
    if (index < 0 || index >= this.length) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    for (int i = index; i < (this.length - 1); i++) {
      this.boards[i] = this.boards[i + 1];
    }
    this.boards[--this.length] = null;
  }
}
