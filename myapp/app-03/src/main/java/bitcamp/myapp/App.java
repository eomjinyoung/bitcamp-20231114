package bitcamp.myapp;

public class App {

  public static void main(String[] args) {
    System.out.println("\033[1;31m[과제관리 시스템]\033[0m");
    System.out.println();
    System.out.print("1. 과제\n");
    System.out.print("2." + " 게시글");
    System.out.print('\n');
    System.out.printf("%d. 도움말\n", 3);
    System.out.print(4);
    System.out.printf(". %s\n", "종료");
  }
}
