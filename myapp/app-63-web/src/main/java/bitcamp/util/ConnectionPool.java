package bitcamp.util;

import java.sql.Connection;

public interface ConnectionPool {

  Connection getConnection() throws Exception;

  void returnConnection(Connection con);
}
