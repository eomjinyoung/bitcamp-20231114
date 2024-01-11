package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.util.List;

public class BoardDao extends AbstractDao<Board> {

  public BoardDao(String filepath) {
    super(filepath);
  }

  public void add(Board board) {
    this.list.add(board);
    saveData();
  }

  public int delete(int no) {
    if (no < 0 || no >= this.list.size()) {
      return 0;
    }
    this.list.remove(no);
    saveData();
    return 1;
  }

  public List<Board> findAll() {
    return this.list.subList(0, list.size());
  }

  public Board findBy(int no) {
    if (no < 0 || no >= list.size()) {
      return null;
    }
    return list.get(no);
  }

  public int update(int no, Board board) {
    if (no < 0 || no >= list.size()) {
      return 0;
    }
    list.set(no, board);
    saveData();
    return 1;
  }
}
