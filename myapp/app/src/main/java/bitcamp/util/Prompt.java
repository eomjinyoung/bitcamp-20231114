package bitcamp.util;

import java.io.InputStream;
import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  private Scanner keyIn;

  public Prompt(InputStream in) {

    keyIn = new Scanner(in);
  }

  public String input(String title, Object... args) {
    System.out.print(String.format(title, args));
    return this.keyIn.nextLine();
  }

  public int inputInt(String title, Object... args) {
    return Integer.parseInt(this.input(title, args));
  }

  public float inputFloat(String title, Object... args) {
    return Float.parseFloat(this.input(title, args));
  }

  public boolean inputBoolean(String title, Object... args) {
    return Boolean.parseBoolean(this.input(title, args));
  }

  public Date inputDate(String title, Object... args) {
    return Date.valueOf(this.input(title, args));
  }

  public void close() {
    this.keyIn.close();
  }
}
