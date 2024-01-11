package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignmentViewHandler extends AbstractMenuHandler {

  private AssignmentDao assignmentDao;

  public AssignmentViewHandler(AssignmentDao assignmentDao, Prompt prompt) {
    super(prompt);
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action() {
    try {
      int no = this.prompt.inputInt("번호? ");
      Assignment assignment = assignmentDao.findBy(no);
      if (assignment == null) {
        System.out.println("과제 번호가 유효하지 않습니다!");
        return;
      }

      System.out.printf("번호: %s\n", assignment.getNo());
      System.out.printf("과제명: %s\n", assignment.getTitle());
      System.out.printf("내용: %s\n", assignment.getContent());
      System.out.printf("제출 마감일: %s\n", assignment.getDeadline());

    } catch (Exception e) {
      System.out.println("조회 오류!");
    }
  }

}
