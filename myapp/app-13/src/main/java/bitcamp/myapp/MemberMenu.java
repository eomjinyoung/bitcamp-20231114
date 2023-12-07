package bitcamp.myapp;

public class MemberMenu {

  static Member[] members = new Member[3];
  static int length = 0;

  static void printMenu() {
    System.out.println("[회원]");
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
      String input = Prompt.input("메인/회원> ");

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
    System.out.println("회원 등록:");

    if (length == members.length) {
      int oldSize = members.length;
      int newSize = oldSize + (oldSize >> 1);

      Member[] arr = new Member[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = members[i];
      }

      members = arr;
    }

    Member member = new Member();
    member.email = Prompt.input("이메일? ");
    member.name = Prompt.input("이름? ");
    member.password = Prompt.input("암호? ");
    member.createdDate = Prompt.input("가입일? ");

    members[length++] = member;
  }

  static void list() {
    System.out.println("회원 목록:");
    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    for (int i = 0; i < length; i++) {
      Member member = members[i];
      System.out.printf("%-10s\t%30s\t%s\n", member.name, member.email, member.createdDate);
    }
  }

  static void view() {
    System.out.println("회원 조회:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    Member member = members[index];
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("가입일: %s\n", member.createdDate);
  }

  static void modify() {
    System.out.println("회원 변경:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    Member member = members[index];
    member.email = Prompt.input("이메일(%s)? ", member.email);
    member.name = Prompt.input("이름(%s)? ", member.name);
    member.password = Prompt.input("새 암호? ");
    member.createdDate = Prompt.input("가입일(%s)? ", member.createdDate);
  }

  static void delete() {
    System.out.println("회원 삭제:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    for (int i = index; i < (length - 1); i++) {
      members[i] = members[i + 1];
    }
    members[--length] = null;
  }
}
