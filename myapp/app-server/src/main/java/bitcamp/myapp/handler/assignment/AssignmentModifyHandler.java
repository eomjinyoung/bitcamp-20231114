package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;

public class AssignmentModifyHandler extends AbstractMenuHandler {

  private AssignmentDao assignmentDao;

  public AssignmentModifyHandler(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  protected void action(Prompt prompt) {
    try {
      int no = prompt.inputInt("번호? ");

      Assignment old = assignmentDao.findBy(no);
      if (old == null) {
        prompt.println("과제 번호가 유효하지 않습니다!");
        return;
      }

      Assignment assignment = new Assignment();
      assignment.setNo(old.getNo());
      assignment.setTitle(prompt.input("과제명(%s)? ", old.getTitle()));
      assignment.setContent(prompt.input("내용(%s)? ", old.getContent()));
      assignment.setDeadline(prompt.inputDate("제출 마감일(%s)? ", old.getDeadline()));

      assignmentDao.update(assignment);
      prompt.println("과제를 변경했습니다.");

    } catch (NumberFormatException e) {
      prompt.println("숫자를 입력하세요!");

    } catch (IllegalArgumentException e) {
      prompt.println("과제 변경 오류!");
      prompt.println("다시 시도 하세요.");

    } catch (Exception e) {
      prompt.println("실행 오류!");
      e.printStackTrace();

    }

  }
}
