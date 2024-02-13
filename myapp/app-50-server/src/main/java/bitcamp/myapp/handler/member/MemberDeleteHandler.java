package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.Prompt;

public class MemberDeleteHandler extends AbstractMenuHandler {

  private MemberDao memberDao;

  public MemberDeleteHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {
    int no = prompt.inputInt("번호? ");
    if (memberDao.delete(no) == -1) {
      prompt.println("회원 번호가 유효하지 않습니다!");
    } else {
      prompt.println("회원을 삭제했습니다.");
    }
  }
}
