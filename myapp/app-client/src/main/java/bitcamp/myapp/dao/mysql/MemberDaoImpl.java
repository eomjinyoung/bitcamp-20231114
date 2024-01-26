package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.sql.Connection;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Member member) {

  }

  @Override
  public int delete(int no) {
    return 0;
  }

  @Override
  public List<Member> findAll() {
    return null;
  }

  @Override
  public Member findBy(int no) {
    return null;
  }

  @Override
  public int update(Member member) {
    return 0;
  }
}
