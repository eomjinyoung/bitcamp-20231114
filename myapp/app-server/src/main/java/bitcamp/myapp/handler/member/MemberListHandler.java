package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.List;

public class MemberListHandler extends AbstractMenuHandler {

  private MemberDao memberDao;

  public MemberListHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {
    prompt.printf("%-4s\t%-10s\t%30s\t%s\n", "번호", "이름", "이메일", "가입일");

    List<Member> list = memberDao.findAll();

    for (Member member : list) {
      prompt.printf("%-4d\t%-10s\t%30s\t%4$tY-%4$tm-%4$td\n",
          member.getNo(),
          member.getName(),
          member.getEmail(),
          member.getCreatedDate());
    }
  }
}
