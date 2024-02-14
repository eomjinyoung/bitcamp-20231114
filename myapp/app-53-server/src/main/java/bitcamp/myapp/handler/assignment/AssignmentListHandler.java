package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;
import java.util.List;

public class AssignmentListHandler extends AbstractMenuHandler {

  private DBConnectionPool connectionPool;
  private AssignmentDao assignmentDao;

  public AssignmentListHandler(DBConnectionPool connectionPool, AssignmentDao assignmentDao) {
    this.connectionPool = connectionPool;
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();
      prompt.printf("%-4s\t%-20s\t%s\n", "번호", "과제", "제출마감일");

      List<Assignment> list = assignmentDao.findAll();

      for (Assignment assignment : list) {
        prompt.printf("%-4d\t%-20s\t%s\n",
            assignment.getNo(),
            assignment.getTitle(),
            assignment.getDeadline());
      }

    } catch (Exception e) {
      prompt.println("목록 오류!");

    } finally {
      connectionPool.returnConnection(con);
    }
  }
}
