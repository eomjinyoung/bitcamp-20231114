package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.AnsiEscape;
import java.util.ArrayList;

public class MemberListHandler implements MenuHandler {

  private ArrayList<Member> objectRepository;

  public MemberListHandler(ArrayList<Member> objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    Member[] members = new Member[this.objectRepository.size()];
    this.objectRepository.toArray(members);

    for (Member member : members) {
      System.out.printf("%-10s\t%30s\t%s\n", member.getName(), member.getEmail(),
          member.getCreatedDate());
    }
  }
}
