package bitcamp.myapp;

public class AssignmentMenu {

  static String title;
  static String content;
  static String deadline;

  static void printMenu() {
    System.out.println("[과제]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }

  static void execute() {
    printMenu();

    while (true) {
      String input = Prompt.input("메인/과제> ");

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
    System.out.println("과제 등록:");
    title = Prompt.input("과제명? ");
    content = Prompt.input("내용? ");
    deadline = Prompt.input("제출 마감일? ");

  }

  static void view() {
    System.out.println("과제 조회:");
    System.out.printf("과제명: %s\n", title);
    System.out.printf("내용: %s\n", content);
    System.out.printf("제출 마감일: %s\n", deadline);

  }

  static void modify() {
    System.out.println("과제 변경:");
    title = Prompt.input("과제명(%s)? ", title);
    content = Prompt.input("내용(%s)? ", content);
    deadline = Prompt.input("제출 마감일(%s)? ", deadline);

  }

  static void delete() {
    System.out.println("과제 삭제:");
    title = "";
    content = "";
    deadline = "";
  }
}
