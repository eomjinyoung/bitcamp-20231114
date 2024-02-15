package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class BoardDeleteHandler extends AbstractMenuHandler {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  public BoardDeleteHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Override
  protected void action(Prompt prompt) {

    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      prompt.println("로그인하시기 바랍니다!");
      return;
    }

    try {
      int no = prompt.inputInt("번호? ");

      Board board = boardDao.findBy(no);
      if (board == null) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
        return;
      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        prompt.println("게시글 삭제 권한이 없습니다!");
        return;
      }

      attachedFileDao.deleteAll(no);
      boardDao.delete(no);
      prompt.println("삭제했습니다!");

    } catch (Exception e) {
      prompt.println("삭제 오류!");
    }
  }
}
