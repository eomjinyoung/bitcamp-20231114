package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class BoardDeleteHandler extends AbstractMenuHandler {

  DBConnectionPool connectionPool;
  private BoardDao boardDao;

  public BoardDeleteHandler(DBConnectionPool connectionPool, BoardDao boardDao) {
    this.connectionPool = connectionPool;
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      int no = prompt.inputInt("번호? ");
      if (boardDao.delete(no) == 0) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
      } else {
        prompt.println("삭제했습니다!");
      }
    } catch (Exception e) {
      prompt.println("삭제 오류!");

    } finally {
      connectionPool.returnConnection(con);
    }
  }
}
