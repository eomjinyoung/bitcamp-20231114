package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class AssignmentDeleteHandler extends AbstractMenuHandler {

  private DBConnectionPool connectionPool;
  private AssignmentDao assignmentDao;

  public AssignmentDeleteHandler(DBConnectionPool connectionPool, AssignmentDao assignmentDao) {
    this.connectionPool = connectionPool;
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();
      int no = prompt.inputInt("번호? ");
      if (assignmentDao.delete(no) == 0) {
        prompt.println("과제 번호가 유효하지 않습니다!");
      } else {
        prompt.println("과제를 삭제했습니다.");
      }

    } catch (Exception e) {
      prompt.println("삭제 오류!");

    } finally {
      connectionPool.returnConnection(con);
    }
  }
}
