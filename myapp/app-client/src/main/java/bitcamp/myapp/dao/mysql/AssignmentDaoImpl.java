package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Assignment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

  Connection con;

  public AssignmentDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Assignment assignment) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into assignments(title,content,deadline) values('%s','%s','%s')",
          assignment.getTitle(), assignment.getContent(), assignment.getDeadline()));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(
          String.format("delete from assignments where assignment_no=%d", no));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Assignment> findAll() {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from assignments");

      ArrayList<Assignment> list = new ArrayList<>();

      while (rs.next()) {
        Assignment assignment = new Assignment();
        assignment.setNo(rs.getInt("assignment_no"));
        assignment.setTitle(rs.getString("title"));
        assignment.setContent(rs.getString("content"));
        assignment.setDeadline(rs.getDate("deadline"));

        list.add(assignment);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Assignment findBy(int no) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from assignments where assignment_no=" + no);

      ArrayList<Assignment> list = new ArrayList<>();

      if (rs.next()) {
        Assignment assignment = new Assignment();
        assignment.setNo(rs.getInt("assignment_no"));
        assignment.setTitle(rs.getString("title"));
        assignment.setContent(rs.getString("content"));
        assignment.setDeadline(rs.getDate("deadline"));

        return assignment;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Assignment assignment) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update assignments set title='%s', content='%s', deadline='%s' where assignment_no=%d",
          assignment.getTitle(), assignment.getContent(), assignment.getDeadline(),
          assignment.getNo()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
