package bitcamp.myapp.handler.member;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;
import bitcamp.util.AnsiEscape;

public class MemberListHandler implements MenuHandler {

  MemberRepository memberRepository;

  public MemberListHandler(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    for (Member member : this.memberRepository.toArray()) {
      System.out.printf("%-10s\t%30s\t%s\n", member.name, member.email, member.createdDate);
    }
  }
}
