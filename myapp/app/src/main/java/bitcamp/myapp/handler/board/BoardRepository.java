package bitcamp.myapp.handler.board;

import bitcamp.myapp.vo.Board;

// 게시글 데이터를 보관하는 일을 한다.
//
public class BoardRepository {

  // 목록에 관련된 코드를 외부에서 볼 수 없게 감춘다.
  private Board[] boards = new Board[3];
  private int length = 0;

  // 대신 목록에 값을 추가하거나, 꺼내거나 삭제하려면
  // 메서드를 통해 수행하도록 유도한다.
  // => 캡슐화

  public void add(Board board) {
    if (this.length == this.boards.length) {
      int oldSize = this.boards.length;
      int newSize = oldSize + (oldSize >> 1);

      Board[] arr = new Board[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = this.boards[i];
      }

      this.boards = arr;
    }

    this.boards[this.length++] = board;
  }
}
