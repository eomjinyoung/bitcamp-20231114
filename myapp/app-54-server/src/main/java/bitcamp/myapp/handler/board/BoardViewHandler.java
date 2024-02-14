package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardViewHandler extends AbstractMenuHandler {

  private BoardDao boardDao;

  public BoardViewHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) {
    try {
      int no = prompt.inputInt("번호? ");

      Board board = boardDao.findBy(no);
      if (board == null) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
        return;
      }

      prompt.printf("번호: %d\n", board.getNo());
      prompt.printf("제목: %s\n", board.getTitle());
      prompt.printf("내용: %s\n", board.getContent());
      prompt.printf("작성자: %s\n", board.getWriter());
      prompt.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());

    } catch (Exception e) {
      prompt.println("조회 오류!");
    }
  }
}
