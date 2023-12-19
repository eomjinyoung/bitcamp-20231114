package bitcamp.myapp.handler.board;

import bitcamp.menu.Menu;
import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;
import bitcamp.util.AnsiEscape;
import java.util.ArrayList;

// 게시글의 '목록' 메뉴를 선택했을 때 작업을 수행하는 클래스
// - 반드시 MenuHandler 규칙에 따라 클래스를 작성해야 한다.
//
public class BoardListHandler implements MenuHandler {

  private ArrayList<Board> objectRepository;

  public BoardListHandler(ArrayList<Board> objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public void action(Menu menu) {
    System.out.printf(AnsiEscape.ANSI_BOLD + "[%s]\n" + AnsiEscape.ANSI_CLEAR, menu.getTitle());

    System.out.printf("%-20s\t%10s\t%s\n", "Title", "Writer", "Date");

    Board[] boards = this.objectRepository.toArray(new Board[0]);

    for (Board board : boards) {
      System.out.printf("%-20s\t%10s\t%s\n",
          board.getTitle(),
          board.getWriter(),
          board.getCreatedDate());
    }
  }
}
