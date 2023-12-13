package bitcamp.myapp.handler.assignment;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.AnsiEscape;
import bitcamp.util.ObjectRepository;

public class AssignmentListHandler implements MenuHandler {

  ObjectRepository<Assignment> objectRepository;


  public AssignmentListHandler(ObjectRepository<Assignment> objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());
    System.out.printf("%-20s\t%s\n", "과제", "제출마감일");

    Assignment[] assignments = new Assignment[this.objectRepository.size()];
    this.objectRepository.toArray(assignments);

    for (Assignment assignment : assignments) {
      System.out.printf("%-20s\t%s\n", assignment.title, assignment.deadline);
    }
  }
}
