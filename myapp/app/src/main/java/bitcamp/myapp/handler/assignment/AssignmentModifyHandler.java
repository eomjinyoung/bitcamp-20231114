package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class AssignmentModifyHandler extends AbstractMenuHandler {

  private Prompt prompt;
  private ArrayList<Assignment> objectRepository;


  public AssignmentModifyHandler(ArrayList<Assignment> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action() {
    int index = this.prompt.inputInt("번호? ");
    Assignment old = this.objectRepository.get(index);
    if (old == null) {
      System.out.println("과제 번호가 유효하지 않습니다.");
      return;
    }

    Assignment assignment = new Assignment();
    assignment.setTitle(this.prompt.input("과제명(%s)? ", old.getTitle()));
    assignment.setContent(this.prompt.input("내용(%s)? ", old.getContent()));
    assignment.setDeadline(this.prompt.input("제출 마감일(%s)? ", old.getDeadline()));

    this.objectRepository.set(index, assignment);
  }
}
