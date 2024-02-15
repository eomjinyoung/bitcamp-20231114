package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.List;

public class BoardListHandler extends AbstractMenuHandler {

  private BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  protected void action(Prompt prompt) {
    try {
      prompt.printf("%-4s\t%-20s\t%10s\t%s\t%s\n", "No", "Title", "Writer", "Date", "Files");

      List<Board> list = boardDao.findAll();

      for (Board board : list) {
        prompt.printf("%-4d\t%-20s\t%10s\t%4$tY-%4$tm-%4$td\t%5$d\n",
            board.getNo(),
            board.getTitle(),
            board.getWriter().getName(),
            board.getCreatedDate(),
            board.getFileCount());
      }
    } catch (Exception e) {
      prompt.println("목록 오류!");
    }
  }
}
