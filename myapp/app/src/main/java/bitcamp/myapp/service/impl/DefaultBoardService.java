package bitcamp.myapp.service.impl;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultBoardService implements BoardService {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  public DefaultBoardService(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Transactional
  @Override
  public void add(Board board) {
    boardDao.add(board);
    if (board.getFiles() != null) {
      for (AttachedFile attachedFile : board.getFiles()) {
        attachedFile.setBoardNo(board.getNo());
      }
      attachedFileDao.addAll(board.getFiles());
    }
  }

  @Override
  public List<Board> list(int category) {
    return boardDao.findAll(category);
  }

  @Override
  public Board get(int no) {
    return boardDao.findBy(no);
  }

  @Transactional
  @Override
  public int update(Board board) {
    int count = boardDao.update(board);
    if (board.getFiles() != null) {
      for (AttachedFile attachedFile : board.getFiles()) {
        attachedFile.setBoardNo(board.getNo());
      }
      attachedFileDao.addAll(board.getFiles());
    }
    return count;
  }

  @Transactional
  @Override
  public int delete(int no) {
    attachedFileDao.deleteAll(no);
    return boardDao.delete(no);
  }

  @Override
  public List<AttachedFile> getAttachedFiles(int no) {
    return attachedFileDao.findAllByBoardNo(no);
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) {
    return attachedFileDao.findByNo(fileNo);
  }

  @Override
  public int deleteAttachedFile(int fileNo) {
    return attachedFileDao.delete(fileNo);
  }
}
