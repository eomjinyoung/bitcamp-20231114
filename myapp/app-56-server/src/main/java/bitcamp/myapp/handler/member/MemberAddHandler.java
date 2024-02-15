package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.Date;

public class MemberAddHandler extends AbstractMenuHandler {

  private MemberDao memberDao;

  public MemberAddHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {
    try {
      Member member = new Member();
      member.setEmail(prompt.input("이메일? "));
      member.setName(prompt.input("이름? "));
      member.setPassword(prompt.input("암호? "));
      member.setCreatedDate(new Date());

      memberDao.add(member);

    } catch (Exception e) {
      prompt.println("등록 오류!");
    }
  }
}
