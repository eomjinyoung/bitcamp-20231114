package bitcamp.myapp.handler.board;

import bitcamp.menu.AbstractMenuHandler;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;
import java.util.ArrayList;
import java.util.List;

public class BoardModifyHandler extends AbstractMenuHandler {

  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;

  public BoardModifyHandler(BoardDao boardDao, AttachedFileDao attachedFileDao) {
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @Override
  protected void action(Prompt prompt) {

    Member loginUser = (Member) prompt.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      prompt.println("로그인하시기 바랍니다!");
      return;
    }

    try {
      int no = prompt.inputInt("번호? ");

      Board oldBoard = boardDao.findBy(no);
      if (oldBoard == null) {
        prompt.println("게시글 번호가 유효하지 않습니다.");
        return;
      } else if (oldBoard.getWriter().getNo() != loginUser.getNo()) {
        prompt.println("게시글 변경 권한이 없습니다!");
        return;
      }

      Board board = new Board();
      board.setNo(oldBoard.getNo()); // 기존 게시글의 번호를 그대로 설정한다.
      board.setTitle(prompt.input("제목(%s)? ", oldBoard.getTitle()));
      board.setContent(prompt.input("내용(%s)? ", oldBoard.getContent()));
      board.setCreatedDate(oldBoard.getCreatedDate());

      prompt.println("첨부파일:");

      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);
      for (AttachedFile file : files) {
        if (prompt.input("  %s 삭제하시겠습니까? (y/N)", file.getFilePath()).equalsIgnoreCase("y")) {
          attachedFileDao.delete(file.getNo());
        }
      }

      List<AttachedFile> newFiles = new ArrayList<>();
      while (true) {
        String filepath = prompt.input("추가할 파일?(종료: 그냥 엔터) ");
        if (filepath.length() == 0) {
          break;
        }
        newFiles.add(new AttachedFile().filePath(filepath).boardNo(no));
      }

      boardDao.update(board);
      attachedFileDao.addAll(newFiles);

      prompt.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      prompt.println("변경 오류!");
    }
  }
}
