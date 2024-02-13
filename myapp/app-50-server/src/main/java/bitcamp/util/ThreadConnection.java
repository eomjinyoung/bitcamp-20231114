package bitcamp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ThreadConnection {

  String jdbcUrl;
  String username;
  String password;

  // 개별 스레드용 DB 커넥션 저장소
  ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

  public ThreadConnection(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection get() throws Exception {
    Connection con = connectionThreadLocal.get();
    if (con == null) {
      con = DriverManager.getConnection(jdbcUrl, username, password);
    }
    return con;
  }
}
