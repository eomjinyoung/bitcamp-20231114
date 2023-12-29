package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Iterator;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class AssignmentListHandler extends AbstractMenuHandler {

  private List<Assignment> objectRepository;

  public AssignmentListHandler(List<Assignment> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    System.out.printf("%-20s\t%s\n", "과제", "제출마감일");

    Iterator<Assignment> iterator = this.objectRepository.iterator();

    while (iterator.hasNext()) {
      Assignment assignment = iterator.next();
      System.out.printf("%-20s\t%s\n", assignment.getTitle(), assignment.getDeadline());
    }
  }
}
