package bitcamp.myapp.handler.assignment;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class AssignmentDeleteHandler extends AbstractMenuHandler {

  private Prompt prompt;
  private ArrayList<Assignment> objectRepository;


  public AssignmentDeleteHandler(ArrayList<Assignment> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action() {
    int index = this.prompt.inputInt("번호? ");
    if (this.objectRepository.remove(index) == null) {
      System.out.println("과제 번호가 유효하지 않습니다.");
    }
  }
}
