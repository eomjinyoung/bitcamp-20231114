package bitcamp.myapp.handler.member;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Member;

public class MemberListHandler implements MenuHandler {

  MemberRepository memberRepository;

  public MemberListHandler(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void action() {
    System.out.println("회원 목록:");
    System.out.printf("%-10s\t%30s\t%s\n", "이름", "이메일", "가입일");

    for (int i = 0; i < this.memberRepository.length; i++) {
      Member member = this.memberRepository.members[i];
      System.out.printf("%-10s\t%30s\t%s\n", member.name, member.email, member.createdDate);
    }
  }
}
