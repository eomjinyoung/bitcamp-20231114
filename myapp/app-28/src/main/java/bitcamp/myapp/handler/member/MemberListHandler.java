package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Iterator;
import bitcamp.util.List;
import bitcamp.util.Prompt;

public class MemberListHandler extends AbstractMenuHandler {

  private List<Member> objectRepository;

  public MemberListHandler(List<Member> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    Iterator<Member> iterator = this.objectRepository.iterator();

    while (iterator.hasNext()) {
      Member member = iterator.next();
      System.out.printf("%-10s\t%30s\t%3$tY-%3$tm-%3$td\n", member.getName(), member.getEmail(),
          member.getCreatedDate());
    }
  }
}
