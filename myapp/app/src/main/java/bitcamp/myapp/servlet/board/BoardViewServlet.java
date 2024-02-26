package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  @Override
  public void init() {
    this.boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    this.attachedFileDao = (AttachedFileDao) this.getServletContext()
        .getAttribute("attachedFileDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String title = "";

    try {
      int category = Integer.valueOf(request.getParameter("category"));
      title = category == 1 ? "게시글" : "가입인사";

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findBy(no);
      if (board == null) {
        throw new Exception("번호가 유효하지 않습니다.");
      }
      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);

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
      out.println("<form action='/board/update' method='post' enctype='multipart/form-data'>");
      out.printf("<input name='category' type='hidden' value='%d'>\n", category);
      out.println("<div>");
      out.printf("  번호: <input readonly name='no' type='text' value='%d'>\n", board.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  제목: <input name='title' type='text' value='%s'>\n", board.getTitle());
      out.println("</div>");
      out.println("<div>");
      out.printf("  내용: <textarea name='content'>%s</textarea>\n", board.getContent());
      out.println("</div>");
      out.println("<div>");
      out.printf("  작성자: <input readonly type='text' value='%s'>\n", board.getWriter().getName());
      out.println("</div>");

      if (category == 1) {
        out.println("<div>");
        out.println("  첨부파일: <input multiple name='files' type='file'>");
        out.println("  <ul>");
        for (AttachedFile file : files) {
          out.printf(
              "    <li><a href='/upload/board/%s'>%1$s</a> [<a href='/board/file/delete?category=%d&no=%d'>삭제</a>]</li>\n",
              file.getFilePath(),
              category,
              file.getNo());
        }
        out.println("  </ul>");
        out.println("</div>");
      }

      out.println("<div>");
      out.println("  <button>변경</button>");
      out.printf("  <a href='/board/delete?category=%d&no=%d'>[삭제]</a>\n", category, no);
      out.println("</div>");
      out.println("</form>");

      request.getRequestDispatcher("/footer").include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("message", String.format("%s 조회 오류!", title));
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
