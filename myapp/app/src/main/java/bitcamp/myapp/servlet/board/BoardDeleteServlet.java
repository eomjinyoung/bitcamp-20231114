package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

  private TransactionManager txManager;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private String uploadDir;

  @Override
  public void init() {
    this.txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
    this.boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    this.attachedFileDao = (AttachedFileDao) this.getServletContext()
        .getAttribute("attachedFileDao");
    this.uploadDir = this.getServletContext().getRealPath("/upload/board");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String title = "";
    try {
      int category = Integer.valueOf(request.getParameter("category"));
      title = category == 1 ? "게시글" : "가입인사";

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findBy(no);
      if (board == null) {
        throw new Exception("번호가 유효하지 않습니다.");

      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("권한이 없습니다.");
      }

      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);

      txManager.startTransaction();
      attachedFileDao.deleteAll(no);
      boardDao.delete(no);
      txManager.commit();

      for (AttachedFile file : files) {
        new File(this.uploadDir + "/" + file.getFilePath()).delete();
      }

      response.sendRedirect("/board/list?category=" + category);

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e2) {
      }
      request.setAttribute("message", String.format("%s 삭제 오류!", title));
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
