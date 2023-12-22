package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class MemberDeleteHandler extends AbstractMenuHandler {

  private ArrayList<Member> objectRepository;

  public MemberDeleteHandler(ArrayList<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    int index = this.prompt.inputInt("번호? ");
    if (this.objectRepository.remove(index) == null) {
      System.out.println("회원 번호가 유효하지 않습니다.");
    }
  }
}
