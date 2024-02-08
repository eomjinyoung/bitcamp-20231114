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
    prompt.printf("%-4s\t%-20s\t%10s\t%s\n", "No", "Title", "Writer", "Date");

    List<Board> list = boardDao.findAll();

    for (Board board : list) {
      prompt.printf("%-4d\t%-20s\t%10s\t%4$tY-%4$tm-%4$td\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter(),
          board.getCreatedDate());
    }
  }
}
