package bitcamp.myapp;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

  Scanner keyIn;

  public Prompt(InputStream in) {
    keyIn = new Scanner(in);
  }

  String input(String title, Object... args) {
    System.out.print(String.format(title, args));
    return this.keyIn.nextLine();
  }

  int inputInt(String title, Object... args) {
    String str = this.input(title, args);
    return Integer.parseInt(str);
  }

  float inputFloat(String title, Object... args) {
    String str = this.input(title, args);
    return Float.parseFloat(str);
  }

  boolean inputBoolean(String title, Object... args) {
    String str = this.input(title, args);
    return Boolean.parseBoolean(str);
  }

  void close() {
    this.keyIn.close();
  }
}
