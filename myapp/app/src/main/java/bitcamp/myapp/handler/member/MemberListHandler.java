package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Member;
import java.util.ArrayList;

public class MemberListHandler extends AbstractMenuHandler {

  private ArrayList<Member> objectRepository;

  public MemberListHandler(ArrayList<Member> objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action() {
    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    Member[] members = new Member[this.objectRepository.size()];
    this.objectRepository.toArray(members);

    for (Member member : members) {
      System.out.printf("%-10s\t%30s\t%s\n", member.getName(), member.getEmail(),
          member.getCreatedDate());
    }
  }
}
