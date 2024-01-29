package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

  int category;
  Connection con;

  public BoardDaoImpl(Connection con, int category) {
    this.con = con;
    this.category = category;
  }

  @Override
  public void add(Board board) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into boards(title,content,writer,category) values('%s','%s','%s',%d)",
          board.getTitle(), board.getContent(), board.getWriter(), this.category));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format("delete from boards where board_no=%d", no));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Board> findAll() {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from boards where category=" + this.category);

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setWriter(rs.getString("writer"));
        board.setCreatedDate(rs.getDate("created_date"));

        list.add(board);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Board findBy(int no) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from boards where board_no = " + no);

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setWriter(rs.getString("writer"));
        board.setCreatedDate(rs.getDate("created_date"));

        return board;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Board board) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update boards set title='%s', content='%s', writer='%s' where board_no=%d",
          board.getTitle(), board.getContent(), board.getWriter(), board.getNo()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
