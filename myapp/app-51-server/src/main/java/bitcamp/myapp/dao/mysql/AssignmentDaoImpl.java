package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Assignment;
import bitcamp.util.ThreadConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

  ThreadConnection threadConnection;

  public AssignmentDaoImpl(ThreadConnection threadConnection) {
    this.threadConnection = threadConnection;
  }

  @Override
  public void add(Assignment assignment) {
    Connection con = null;
    try {
      con = threadConnection.get(); // 현재 스레드에 보관된 Connection 객체를 꺼낸다. 없으면 만들어준다.

      con.setAutoCommit(false);
      try (PreparedStatement pstmt = con.prepareStatement(
          "insert into assignments(title,content,deadline) values(?,?,?)")) {

        pstmt.setString(1, assignment.getTitle());
        pstmt.setString(2, assignment.getContent());
        pstmt.setDate(3, assignment.getDeadline());

        pstmt.executeUpdate();
        pstmt.executeUpdate();
      }
      con.rollback();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    Connection con = null;
    try {
      con = threadConnection.get(); // 현재 스레드에 보관된 Connection 객체를 꺼낸다. 없으면 만들어준다.

      try (PreparedStatement pstmt = con.prepareStatement(
          "delete from assignments where assignment_no=?")) {
        pstmt.setInt(1, no);

        return pstmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Assignment> findAll() {
    Connection con = null;
    try {
      con = threadConnection.get(); // 현재 스레드에 보관된 Connection 객체를 꺼낸다. 없으면 만들어준다.

      try (PreparedStatement pstmt = con.prepareStatement(
          "select assignment_no, title, deadline from assignments order by assignment_no desc");
          ResultSet rs = pstmt.executeQuery()) {

        ArrayList<Assignment> list = new ArrayList<>();

        while (rs.next()) {
          Assignment assignment = new Assignment();
          assignment.setNo(rs.getInt("assignment_no"));
          assignment.setTitle(rs.getString("title"));
          assignment.setDeadline(rs.getDate("deadline"));

          list.add(assignment);
        }
        return list;
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Assignment findBy(int no) {
    Connection con = null;
    try {
      con = threadConnection.get(); // 현재 스레드에 보관된 Connection 객체를 꺼낸다. 없으면 만들어준다.

      try (PreparedStatement pstmt = con.prepareStatement(
          "select * from assignments where assignment_no=?")) {

        pstmt.setInt(1, no);

        try (ResultSet rs = pstmt.executeQuery()) {

          if (rs.next()) {
            Assignment assignment = new Assignment();
            assignment.setNo(rs.getInt("assignment_no"));
            assignment.setTitle(rs.getString("title"));
            assignment.setContent(rs.getString("content"));
            assignment.setDeadline(rs.getDate("deadline"));
            return assignment;
          }
          return null;
        }
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Assignment assignment) {
    Connection con = null;
    try {
      con = threadConnection.get(); // 현재 스레드에 보관된 Connection 객체를 꺼낸다. 없으면 만들어준다.

      try (PreparedStatement pstmt = con.prepareStatement(
          "update assignments set title=?, content=?, deadline=? where assignment_no=?")) {

        pstmt.setString(1, assignment.getTitle());
        pstmt.setString(2, assignment.getContent());
        pstmt.setDate(3, assignment.getDeadline());
        pstmt.setInt(4, assignment.getNo());

        return pstmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
