package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardViewController implements PageController {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  public BoardViewController(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String boardName = "";

    int category = Integer.valueOf(request.getParameter("category"));
    boardName = category == 1 ? "게시글" : "가입인사";

    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDao.findBy(no);
    if (board == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }

    request.setAttribute("boardName", boardName);
    request.setAttribute("category", category);
    request.setAttribute("board", board);
    if (category == 1) {
      request.setAttribute("files", attachedFileDao.findAllByBoardNo(no));
    }
    return "/board/view.jsp";
  }
}
