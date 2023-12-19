package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class MemberModifyHandler extends AbstractMenuHandler {

  private ArrayList<Member> objectRepository;

  public MemberModifyHandler(ArrayList<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    int index = this.prompt.inputInt("번호? ");
    Member old = this.objectRepository.get(index);
    if (old == null) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    Member member = new Member();
    member.setEmail(this.prompt.input("이메일(%s)? ", old.getEmail()));
    member.setName(this.prompt.input("이름(%s)? ", old.getName()));
    member.setPassword(this.prompt.input("새 암호? "));
    member.setCreatedDate(this.prompt.input("가입일(%s)? ", old.getCreatedDate()));

    this.objectRepository.set(index, member);
  }
}
