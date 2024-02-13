package bitcamp.util;

import java.sql.Connection;

public class ThreadConnection {

  String jdbcUrl;
  String username;
  String password;

  public ThreadConnection(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection get() {
    
  }
}
