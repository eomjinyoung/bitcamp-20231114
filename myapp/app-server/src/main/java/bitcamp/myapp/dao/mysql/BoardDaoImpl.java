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
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into boards(title,content,writer,category) values(?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, board.getTitle());
      pstmt.setString(2, board.getContent());
      pstmt.setString(3, board.getWriter());
      pstmt.setInt(4, category);

      pstmt.executeUpdate();

      // 자동 생성된 PK 값을 가져와서 Board 객체에 저장한다.
      try (ResultSet keyRs = pstmt.getGeneratedKeys()) {
        keyRs.next();
        board.setNo(keyRs.getInt(1));
      }


    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from boards where board_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Board> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select\n"
                + "  b.board_no,\n"
                + "  b.title,\n"
                + "  b.writer,\n"
                + "  b.created_date,\n"
                + "  count(file_no) file_count\n"
                + "from\n"
                + "  boards b left outer join board_files bf on b.board_no=bf.board_no\n"
                + "where\n"
                + "  b.category=?\n"
                + "group by\n"
                + "  board_no\n"
                + "order by\n"
                + "  board_no desc")) {

      pstmt.setInt(1, category);

      try (ResultSet rs = pstmt.executeQuery()) {

        ArrayList<Board> list = new ArrayList<>();

        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_no"));
          board.setTitle(rs.getString("title"));
          board.setWriter(rs.getString("writer"));
          board.setCreatedDate(rs.getDate("created_date"));
          board.setFileCount(rs.getInt("file_count"));

          list.add(board);
        }
        return list;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Board findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
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
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Board board) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update boards set title=?, content=?, writer=? where board_no=?")) {

      pstmt.setString(1, board.getTitle());
      pstmt.setString(2, board.getContent());
      pstmt.setString(3, board.getWriter());
      pstmt.setInt(4, board.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
