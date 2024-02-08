package bitcamp.util;

import java.io.InputStream;
import java.sql.Date;
import java.util.Scanner;

public class Prompt implements AutoCloseable {

  Scanner keyIn;

  public Prompt(InputStream in) {
    keyIn = new Scanner(in);
  }

  public String input(String str, Object... args) {
    return keyIn.nextLine();
  }

  public int inputInt(String str, Object... args) {
    return Integer.parseInt(this.input(str, args));
  }

  public float inputFloat(String str, Object... args) {
    return Float.parseFloat(this.input(str, args));
  }

  public boolean inputBoolean(String str, Object... args) {
    return Boolean.parseBoolean(this.input(str, args));
  }

  public Date inputDate(String str, Object... args) {
    return Date.valueOf(this.input(str, args));
  }

  public void close() {

  }
}
