package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.ArrayList;

// 게시글의 '등록' 메뉴를 선택했을 때 작업을 수행하는 클래스
// - 반드시 MenuHandler 규칙에 따라 클래스를 작성해야 한다.
//
public class BoardModifyHandler extends AbstractMenuHandler {

  private ArrayList<Board> objectRepository;

  public BoardModifyHandler(ArrayList<Board> objectRepository, Prompt prompt) {
    super(prompt);
    this.objectRepository = objectRepository;
  }

  @Override
  protected void action() {
    int index = this.prompt.inputInt("번호? ");
    Board oldBoard = this.objectRepository.get(index);
    if (oldBoard == null) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = new Board();
    board.setTitle(this.prompt.input("제목(%s)? ", oldBoard.getTitle()));
    board.setContent(this.prompt.input("내용(%s)? ", oldBoard.getContent()));
    board.setWriter(this.prompt.input("작성자(%s)? ", oldBoard.getWriter()));
    board.setCreatedDate(oldBoard.getCreatedDate());

    this.objectRepository.set(index, board);
  }
}
