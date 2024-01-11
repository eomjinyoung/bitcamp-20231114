package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.List;

public class BoardListHandler extends AbstractMenuHandler {

  private BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-20s\t%10s\t%s\n", "Title", "Writer", "Date");

    List<Board> list = boardDao.findAll();

    for (Board board : list) {
      System.out.printf("%-20s\t%10s\t%3$tY-%3$tm-%3$td\n",
          board.getTitle(),
          board.getWriter(),
          board.getCreatedDate());
    }
  }
}
