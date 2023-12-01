package bitcamp.myapp;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    MainMenu.printMenu();

    java.util.Scanner keyIn = new java.util.Scanner(System.in);

    loop:
    while (true) {
      String input = prompt("메인", keyIn);

      switch (input) {
        case "1":
          onAssignment(keyIn);
          break;
        case "2":
          BoardMenu.execute(keyIn);
          break;
        case "3":
          System.out.println("도움말입니다.");
          break;
        case "4":
          System.out.println("종료합니다.");
          break loop;
        case "menu":
          MainMenu.printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }
    }

    keyIn.close();
  }


  static String prompt(String title, Scanner keyIn) {
    System.out.printf("%s> ", title);
    return keyIn.nextLine();
  }

  static void onAssignment(Scanner keyIn) {
    AssignmentMenu.printMenu();

    while (true) {
      String input = prompt("메인/과제", keyIn);

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
