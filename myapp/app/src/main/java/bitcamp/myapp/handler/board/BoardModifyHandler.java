package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardModifyHandler extends AbstractMenuHandler {

  private BoardDao boardDao;

  public BoardModifyHandler(BoardDao boardDao, Prompt prompt) {
    super(prompt);
    this.boardDao = boardDao;
  }

  @Override
  protected void action() {
    int index = this.prompt.inputInt("번호? ");

    Board oldBoard = boardDao.findBy(index);
    if (oldBoard == null) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = new Board();
    board.setTitle(this.prompt.input("제목(%s)? ", oldBoard.getTitle()));
    board.setContent(this.prompt.input("내용(%s)? ", oldBoard.getContent()));
    board.setWriter(this.prompt.input("작성자(%s)? ", oldBoard.getWriter()));
    board.setCreatedDate(oldBoard.getCreatedDate());

    boardDao.update(index, board);
    System.out.println("게시글을 변경했습니다.");
  }
}
