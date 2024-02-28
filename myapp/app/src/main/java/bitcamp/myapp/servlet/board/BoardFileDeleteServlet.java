package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Member;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/file/delete")
public class BoardFileDeleteServlet extends HttpServlet {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private String uploadDir;

  @Override
  public void init() {
    this.boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    this.attachedFileDao = (AttachedFileDao) this.getServletContext()
        .getAttribute("attachedFileDao");
    uploadDir = this.getServletContext().getRealPath("/upload/board");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String boardName = "";

    try {
      int category = Integer.valueOf(request.getParameter("category"));
      boardName = category == 1 ? "게시글" : "가입인사";

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      int fileNo = Integer.parseInt(request.getParameter("no"));
      AttachedFile file = attachedFileDao.findByNo(fileNo);
      if (file == null) {
        throw new Exception("첨부파일 번호가 유효하지 않습니다.");
      }

      Member writer = boardDao.findBy(file.getBoardNo()).getWriter();
      if (writer.getNo() != loginUser.getNo()) {
        throw new Exception("권한이 없습니다.");
      }

      attachedFileDao.delete(fileNo);
      new File(this.uploadDir + "/" + file.getFilePath()).delete();

      response.sendRedirect(request.getHeader("Referer"));

    } catch (Exception e) {
      request.setAttribute("message", String.format("%s 첨부파일 삭제 오류!", boardName));
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
