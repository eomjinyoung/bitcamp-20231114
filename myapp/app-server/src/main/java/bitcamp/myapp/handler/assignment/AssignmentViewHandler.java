package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignmentViewHandler extends AbstractMenuHandler {

  private AssignmentDao assignmentDao;

  public AssignmentViewHandler(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) {
    try {
      int no = prompt.inputInt("번호? ");
      Assignment assignment = assignmentDao.findBy(no);
      if (assignment == null) {
        prompt.println("과제 번호가 유효하지 않습니다!");
        return;
      }

      prompt.printf("번호: %s\n", assignment.getNo());
      prompt.printf("과제명: %s\n", assignment.getTitle());
      prompt.printf("내용: %s\n", assignment.getContent());
      prompt.printf("제출 마감일: %s\n", assignment.getDeadline());

    } catch (Exception e) {
      prompt.println("조회 오류!");

    }
  }

}
