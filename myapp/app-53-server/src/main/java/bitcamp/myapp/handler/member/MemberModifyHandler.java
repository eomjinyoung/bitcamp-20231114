package bitcamp.myapp.handler.member;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class MemberModifyHandler extends AbstractMenuHandler {

  DBConnectionPool connectionPool;
  private MemberDao memberDao;

  public MemberModifyHandler(DBConnectionPool connectionPool, MemberDao memberDao) {
    this.connectionPool = connectionPool;
    this.memberDao = memberDao;
  }

  @Override
  protected void action(Prompt prompt) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      int no = prompt.inputInt("번호? ");

      Member old = memberDao.findBy(no);
      if (old == null) {
        System.out.println("회원 번호가 유효하지 않습니다!");
        return;
      }

      Member member = new Member();
      member.setNo(old.getNo());
      member.setEmail(prompt.input("이메일(%s)? ", old.getEmail()));
      member.setName(prompt.input("이름(%s)? ", old.getName()));
      member.setPassword(prompt.input("새 암호? "));
      member.setCreatedDate(old.getCreatedDate());

      memberDao.update(member);
      prompt.println("회원을 변경했습니다.");

    } catch (Exception e) {
      prompt.println("변경 오류!");

    } finally {
      connectionPool.returnConnection(con);
    }
  }
}
