package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.util.List;

public class BoardDao extends AbstractDao<Board> {

  private int lastKey;

  public BoardDao(String filepath) {
    super(filepath);

    // 마지막 게시글의 식별 번호를 알아낸다.
    lastKey = list.getLast().getNo();
  }

  public void add(Board board) {
    board.setNo(++lastKey);
    this.list.add(board);
    saveData();
  }

  public int delete(int no) {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    return 1;
  }

  public List<Board> findAll() {
    return this.list.subList(0, list.size());
  }

  public Board findBy(int no) {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Board board) {
    int index = indexOf(board.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, board);
    saveData();
    return 1;
  }

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
