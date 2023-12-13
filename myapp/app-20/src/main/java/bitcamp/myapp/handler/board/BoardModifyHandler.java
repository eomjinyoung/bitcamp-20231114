package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.AnsiEscape;
import bitcamp.util.ObjectRepository;
import bitcamp.util.Prompt;

// 게시글의 '등록' 메뉴를 선택했을 때 작업을 수행하는 클래스
// - 반드시 MenuHandler 규칙에 따라 클래스를 작성해야 한다.
//
public class BoardModifyHandler implements MenuHandler {

  ObjectRepository<Board> objectRepository;
  Prompt prompt;

  public BoardModifyHandler(ObjectRepository<Board> objectRepository, Prompt prompt) {
    this.objectRepository = objectRepository;
    this.prompt = prompt;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    int index = this.prompt.inputInt("번호? ");
    Board oldBoard = this.objectRepository.get(index);
    if (oldBoard == null) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }

    Board board = new Board();
    board.title = this.prompt.input("제목(%s)? ", oldBoard.title);
    board.content = this.prompt.input("내용(%s)? ", oldBoard.content);
    board.writer = this.prompt.input("작성자(%s)? ", oldBoard.writer);
    board.createdDate = this.prompt.input("작성일(%s)? ", oldBoard.createdDate);

    this.objectRepository.set(index, board);
  }
}
