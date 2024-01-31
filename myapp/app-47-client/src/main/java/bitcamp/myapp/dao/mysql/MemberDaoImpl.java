package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Member member) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into members(email,name,password) values('%s','%s',sha2('%s',256))",
          member.getEmail(), member.getName(), member.getPassword()));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format("delete from members where member_no=%d", no));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Member> findAll() {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from members");

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setEmail(rs.getString("email"));
        member.setName(rs.getString("name"));
        member.setCreatedDate(rs.getDate("created_date"));

        list.add(member);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Member findBy(int no) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from members where member_no = " + no);

      if (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setEmail(rs.getString("email"));
        member.setName(rs.getString("name"));
        member.setCreatedDate(rs.getDate("created_date"));

        return member;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Member member) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update members set email='%s', name='%s', password=sha2('%s',256) where member_no=%d",
          member.getEmail(), member.getName(), member.getPassword(), member.getNo()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
