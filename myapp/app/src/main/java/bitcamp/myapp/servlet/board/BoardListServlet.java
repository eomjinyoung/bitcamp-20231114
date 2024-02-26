package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {

  private BoardDao boardDao;

  @Override
  public void init() {
    this.boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int category = Integer.valueOf(request.getParameter("category"));
    String title = category == 1 ? "게시글" : "가입인사";

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    out.printf("<h1>%s</h1>\n", title);

    out.printf("<a href='/board/add?category=%d'>새 글</a>\n", category);

    try {
      out.println("<table border='1'>");
      out.println("    <thead>");
      out.println("    <tr> <th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> <th>첨부파일</th> </tr>");
      out.println("    </thead>");
      out.println("    <tbody>");

      List<Board> list = boardDao.findAll(category);

      for (Board board : list) {
        out.printf(
            "<tr> <td>%d</td> <td><a href='/board/view?category=%d&no=%1$d'>%s</a></td> <td>%s</td> <td>%s</td> <td>%d</td> </tr>\n",
            board.getNo(),
            category,
            board.getTitle(),
            board.getWriter().getName(),
            board.getCreatedDate(),
            board.getFileCount());
      }

      out.println("    </tbody>");
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>목록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}
