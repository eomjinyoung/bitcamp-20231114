package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberViewHandler extends AbstractMenuHandler {

  private MemberDao memberDao;

  public MemberViewHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {
    int no = prompt.inputInt("번호? ");

    Member member = memberDao.findBy(no);
    if (member == null) {
      prompt.println("회원 번호가 유효하지 않습니다!");
      return;
    }

    prompt.printf("번호: %d\n", member.getNo());
    prompt.printf("이메일: %s\n", member.getEmail());
    prompt.printf("이름: %s\n", member.getName());
    prompt.printf("가입일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", member.getCreatedDate());
  }
}
