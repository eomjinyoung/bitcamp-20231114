package bitcamp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ThreadConnection {

  // 개별 스레드용 DB 커넥션 저장소
  private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

  private String jdbcUrl;
  private String username;
  private String password;

  public ThreadConnection(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection get() throws Exception {
    // 현재 스레드에 보관중인 Connection 객체를 꺼낸다.
    Connection con = connectionThreadLocal.get();

    if (con == null) {
      // 없다면, 새로 만든다.
      con = DriverManager.getConnection(jdbcUrl, username, password);

      // 나중에 또 사용할 수 있도록 현재 스레드에 보관한다.
      connectionThreadLocal.set(con);

      System.out.printf("%s: DB 커넥션 생성\n", Thread.currentThread().getName());
    } else {
      System.out.printf("%s: 기존에 보관했던 DB 커넥션 사용\n", Thread.currentThread().getName());
    }

    return con;
  }

  public void remove() {
    // 현재 스레드에 보관중인 Connection 객체를 제거한다.
    Connection con = connectionThreadLocal.get();

    if (con != null) {
      try {
        con.close();
      } catch (Exception e) {
      }
      connectionThreadLocal.remove();
      System.out.printf("%s: DB 커넥션 제거\n", Thread.currentThread().getName());
    }
  }
}
