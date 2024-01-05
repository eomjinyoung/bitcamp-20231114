package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.List;

public class AssignmentDeleteHandler extends AbstractMenuHandler {

  private List<Assignment> objectRepository;

  public AssignmentDeleteHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    try {
      int index = this.prompt.inputInt("번호? ");
      this.objectRepository.remove(index);

    } catch (Exception e) {
      System.out.println("삭제 오류!");
    }
  }
}
