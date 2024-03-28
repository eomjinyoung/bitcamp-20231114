package bitcamp.myapp.service;

import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.util.List;

public interface BoardService {

  void add(Board board);

  List<Board> list(int category);

  Board get(int no);

  int update(Board board);

  int delete(int no);
  
  List<AttachedFile> getAttachedFiles(int no);

  AttachedFile getAttachedFile(int fileNo);

  int deleteAttachedFile(int fileNo);
}
