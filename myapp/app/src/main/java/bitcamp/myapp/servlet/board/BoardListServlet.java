package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.vo.Board;
import bitcamp.util.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/list")
public class BoardListServlet extends GenericServlet {

  private BoardDao boardDao;

  public BoardListServlet() {
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");
    this.boardDao = new BoardDaoImpl(connectionPool, 1);
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {

    servletResponse.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = servletResponse.getWriter();

    try {
      out.printf("%-4s\t%-20s\t%10s\t%s\t%s\n", "No", "Title", "Writer", "Date", "Files");

      List<Board> list = boardDao.findAll();

      for (Board board : list) {
        out.printf("%-4d\t%-20s\t%10s\t%4$tY-%4$tm-%4$td\t%5$d\n",
            board.getNo(),
            board.getTitle(),
            board.getWriter().getName(),
            board.getCreatedDate(),
            board.getFileCount());
      }
    } catch (Exception e) {
      out.println("목록 오류!");
    }
  }
}
