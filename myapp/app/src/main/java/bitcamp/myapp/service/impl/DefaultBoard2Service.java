package bitcamp.myapp.service.impl;

import bitcamp.myapp.dao.AttachedFile2Dao;
import bitcamp.myapp.dao.Board2Dao;
import bitcamp.myapp.service.Board2Service;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DefaultBoard2Service implements Board2Service {

  private final Board2Dao boardDao;
  private final AttachedFile2Dao attachedFileDao;

  @Transactional
  @Override
  public void add(Board board) {
    boardDao.add(board);
    if (board.getFileList() != null && board.getFileList().size() > 0) {
      for (AttachedFile attachedFile : board.getFileList()) {
        attachedFile.setBoardNo(board.getNo());
      }
      attachedFileDao.addAll(board.getFileList());
    }
  }

  @Override
  public List<Board> list(int pageNo, int pageSize) {
    return boardDao.findAll(pageSize * (pageNo - 1), pageSize);
  }

  @Override
  public Board get(int no) {
    return boardDao.findBy(no);
  }

  @Transactional
  @Override
  public int update(Board board) {
    int count = boardDao.update(board);
    attachedFileDao.deleteAll(board.getNo());

    if (board.getFileList() != null && board.getFileList().size() > 0) {
      for (AttachedFile attachedFile : board.getFileList()) {
        attachedFile.setBoardNo(board.getNo());
      }
      attachedFileDao.addAll(board.getFileList());
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

  @Override
  public int countAll() {
    return boardDao.countAll();
  }
}
