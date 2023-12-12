package bitcamp.myapp.handler.member;

import bitcamp.menu.MenuHandler;
import bitcamp.util.Prompt;

public class MemberDeleteHandler implements MenuHandler {

  Prompt prompt;
  MemberRepository memberRepository;

  public MemberDeleteHandler(MemberRepository memberRepository, Prompt prompt) {
    this.memberRepository = memberRepository;
    this.prompt = prompt;
  }

  @Override
  public void action() {
    System.out.println("회원 삭제:");

    int index = this.prompt.inputInt("번호? ");
    if (index < 0 || index >= this.memberRepository.length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    for (int i = index; i < (this.memberRepository.length - 1); i++) {
      this.memberRepository.members[i] = this.memberRepository.members[i + 1];
    }
    this.memberRepository.members[--this.memberRepository.length] = null;
  }
}
