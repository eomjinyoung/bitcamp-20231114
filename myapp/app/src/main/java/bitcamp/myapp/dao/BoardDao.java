package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;

public class BoardDao extends AbstractDao<Board> {

  public void add(Board board) {
    this.list.add(board);
  }

  public int delete(int no) {
    if (no < 0 || no >= this.list.size()) {
      return 0;
    }
    this.list.remove(no);
    return 1;
  }
}
