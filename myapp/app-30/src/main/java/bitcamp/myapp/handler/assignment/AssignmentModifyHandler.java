package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.List;

public class AssignmentModifyHandler extends AbstractMenuHandler {

  private List<Assignment> objectRepository;

  public AssignmentModifyHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    try {
      int index = this.prompt.inputInt("번호? ");
      Assignment old = this.objectRepository.get(index);
      Assignment assignment = new Assignment();
      assignment.setTitle(this.prompt.input("과제명(%s)? ", old.getTitle()));
      assignment.setContent(this.prompt.input("내용(%s)? ", old.getContent()));
      assignment.setDeadline(this.prompt.inputDate("제출 마감일(%s)? ", old.getDeadline()));

      this.objectRepository.set(index, assignment);

    } catch (NumberFormatException e) {
      System.out.println("숫자를 입력하세요!");

    } catch (IndexOutOfBoundsException e) {
      System.out.println("과제 번호가 유효하지 않습니다.");

    } catch (IllegalArgumentException e) {
      System.out.println("과제 변경 오류!");
      System.out.println("다시 시도 하세요.");

    } catch (Exception e) {
      System.out.println("실행 오류!");
    }

  }
}
