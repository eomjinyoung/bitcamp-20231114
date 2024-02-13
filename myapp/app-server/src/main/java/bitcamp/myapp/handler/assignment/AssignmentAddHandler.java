package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import bitcamp.util.ThreadConnection;
import java.sql.Connection;

public class AssignmentAddHandler extends AbstractMenuHandler {

  private ThreadConnection threadConnection;
  private AssignmentDao assignmentDao;


  public AssignmentAddHandler(ThreadConnection threadConnection, AssignmentDao assignmentDao) {
    this.threadConnection = threadConnection;
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) {
    try {
      Assignment assignment = new Assignment();
      assignment.setTitle(prompt.input("과제명? "));
      assignment.setContent(prompt.input("내용? "));
      assignment.setDeadline(prompt.inputDate("제출 마감일?(예: 2023-12-25) "));

      Connection con = threadConnection.get();
      assignmentDao.add(assignment);
      assignmentDao.add(assignment);
      con.rollback();


    } catch (Exception e) {
      prompt.println("과제 입력 중 오류 발생!");
      prompt.println("다시 시도하시기 바랍니다.");
    }
  }
}
