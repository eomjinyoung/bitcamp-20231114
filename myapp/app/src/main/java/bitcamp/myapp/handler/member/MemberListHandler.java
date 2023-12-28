package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
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

    Member[] members = new Member[this.objectRepository.size()];
    this.objectRepository.toArray(members);

    for (Member member : members) {
      System.out.printf("%-10s\t%30s\t%3$tY-%3$tm-%3$td\n", member.getName(), member.getEmail(),
          member.getCreatedDate());
    }
  }
}
