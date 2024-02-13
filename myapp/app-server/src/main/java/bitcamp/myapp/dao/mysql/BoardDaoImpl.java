package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

  DBConnectionPool connectionPool;
  int category;

  public BoardDaoImpl(DBConnectionPool connectionPool, int category) {
    this.connectionPool = connectionPool;
    this.category = category;
  }

  @Override
  public void add(Board board) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      try (PreparedStatement pstmt = con.prepareStatement(
          "insert into boards(title,content,writer,category) values(?,?,?,?)")) {

        pstmt.setString(1, board.getTitle());
        pstmt.setString(2, board.getContent());
        pstmt.setString(3, board.getWriter());
        pstmt.setInt(4, category);

        pstmt.executeUpdate();
      }

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      try (PreparedStatement pstmt = con.prepareStatement(
          "delete from boards where board_no=?")) {
        pstmt.setInt(1, no);
        return pstmt.executeUpdate();
      }

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Board> findAll() {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      try (PreparedStatement pstmt = con.prepareStatement(
          "select board_no, title, writer, created_date"
              + " from boards where category=? order by board_no desc")) {

        pstmt.setInt(1, category);

        try (ResultSet rs = pstmt.executeQuery()) {

          ArrayList<Board> list = new ArrayList<>();

          while (rs.next()) {
            Board board = new Board();
            board.setNo(rs.getInt("board_no"));
            board.setTitle(rs.getString("title"));
            board.setWriter(rs.getString("writer"));
            board.setCreatedDate(rs.getDate("created_date"));

            list.add(board);
          }
          return list;
        }
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Board findBy(int no) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      try (PreparedStatement pstmt = con.prepareStatement(
          "select * from boards where board_no=?")) {

        pstmt.setInt(1, no);

        try (ResultSet rs = pstmt.executeQuery()) {
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
        }
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Board board) {
    Connection con = null;
    try {
      con = connectionPool.getConnection();

      try (PreparedStatement pstmt = con.prepareStatement(
          "update boards set title=?, content=?, writer=? where board_no=?")) {

        pstmt.setString(1, board.getTitle());
        pstmt.setString(2, board.getContent());
        pstmt.setString(3, board.getWriter());
        pstmt.setInt(4, board.getNo());

        return pstmt.executeUpdate();
      }

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
