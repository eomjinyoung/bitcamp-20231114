package bitcamp.myapp;

import java.util.Scanner;

public class AssignmentMenu {

  static void printMenu() {
    System.out.println("[과제]");
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }

  static void execute(Scanner keyIn) {
    AssignmentMenu.printMenu();

    while (true) {
      String input = App.prompt("메인/과제", keyIn);

      switch (input) {
        case "1":
          System.out.println("등록입니다.");
          break;
        case "2":
          System.out.println("조회입니다.");
          break;
        case "3":
          System.out.println("변경입니다.");
          break;
        case "4":
          System.out.println("삭제입니다.");
          break;
        case "0":
          return;
        case "menu":
          AssignmentMenu.printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }
}
