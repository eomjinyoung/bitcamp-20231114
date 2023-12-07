package bitcamp.myapp;

public class GreetingBoardMenu {

  static Board[] boards = new Board[3];
  static int length = 0;

  static void printMenu() {
    System.out.println("[가입인사]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("5. 목록");
    System.out.println("0. 이전");
  }

  static void execute() {
    printMenu();
    while (true) {
      String input = Prompt.input("메인/가입인사> ");

      switch (input) {
        case "1":
          add();
          break;
        case "2":
          view();
          break;
        case "3":
          modify();
          break;
        case "4":
          delete();
          break;
        case "5":
          list();
          break;
        case "0":
          return;
        case "menu":
          printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  static void add() {
    System.out.println("가입인사 등록:");

    if (length == boards.length) {
      int oldSize = boards.length;
      int newSize = oldSize + (oldSize >> 1);

      Board[] arr = new Board[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = boards[i];
      }

      boards = arr;
    }

    Board board = new Board();
    board.title = Prompt.input("제목? ");
    board.content = Prompt.input("내용? ");
    board.writer = Prompt.input("작성자? ");
    board.createdDate = Prompt.input("작성일? ");

    boards[length++] = board;
  }

  static void list() {
    System.out.println("가입인사 목록:");
    System.out.printf("%-20s\t%10s\t%s\n", "Title", "Writer", "Date");

    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      System.out.printf("%-20s\t%10s\t%s\n", board.title, board.writer, board.createdDate);
    }
  }

  static void view() {
    System.out.println("가입인사 조회:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("가입인사 번호가 유효하지 않습니다.");
      return;
    }

    Board board = boards[index];
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("작성일: %s\n", board.createdDate);
  }

  static void modify() {
    System.out.println("가입인사 변경:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("가입인사 번호가 유효하지 않습니다.");
      return;
    }

    Board board = boards[index];
    board.title = Prompt.input("제목(%s)? ", board.title);
    board.content = Prompt.input("내용(%s)? ", board.content);
    board.writer = Prompt.input("작성자(%s)? ", board.writer);
    board.createdDate = Prompt.input("작성일(%s)? ", board.createdDate);
  }

  static void delete() {
    System.out.println("가입인사 삭제:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("가입인사 번호가 유효하지 않습니다.");
      return;
    }

    for (int i = index; i < (length - 1); i++) {
      boards[i] = boards[i + 1];
    }
    boards[--length] = null;
  }
}
