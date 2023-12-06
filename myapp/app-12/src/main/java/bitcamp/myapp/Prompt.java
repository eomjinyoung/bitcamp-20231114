package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {

  static Scanner keyIn = new Scanner(System.in);

  static String input(String title, Object... args) {
    System.out.print(String.format(title, args));
    return keyIn.nextLine();
  }

  static void close() {
    keyIn.close();
  }
}
