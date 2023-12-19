package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;

public class MemberAddHandler extends AbstractMenuHandler {

  private ArrayList<Member> objectRepository;

  public MemberAddHandler(ArrayList<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    Member member = new Member();
    member.setEmail(this.prompt.input("이메일? "));
    member.setName(this.prompt.input("이름? "));
    member.setPassword(this.prompt.input("암호? "));
    member.setCreatedDate(this.prompt.input("가입일? "));

    this.objectRepository.add(member);
  }
}
