package bitcamp.myapp.handler.auth;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class LoginHandler extends AbstractMenuHandler {

  MemberDao memberDao;

  public LoginHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {
    String email = prompt.input("이메일? ");
    String password = prompt.input("암호? ");

    Member member = memberDao.findByEmailAndPassword(email, password);
    if (member != null) {
//      prompt.setLoginUser(member);
//      prompt.setAttribute("loginUser", member);
      prompt.getSession().setAttribute("loginUser", member);
      prompt.printf("%s 님 환영합니다.\n", member.getName());
    } else {
      prompt.println("이메일 또는 암호가 맞지 않습니다.");
    }
  }
}
