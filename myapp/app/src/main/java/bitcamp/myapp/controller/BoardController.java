package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.stereotype.Component;

@Component
public class BoardController {

  private TransactionManager txManager;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private String uploadDir = System.getProperty("board.upload.dir");

  public BoardController(
      TransactionManager txManager,
      BoardDao boardDao,
      AttachedFileDao attachedFileDao) {
    System.out.println("BoardController() 호출됨!");
    this.txManager = txManager;
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
  }

  @RequestMapping("/board/form")
  public String form(
      @RequestParam("category") int category,
      Map<String, Object> map) throws Exception {

    map.put("boardName", category == 1 ? "게시글" : "가입인사");
    map.put("category", category);
    return "/board/form.jsp";
  }

  @RequestMapping("/board/add")
  public String add(
      Board board,
      @RequestParam("files") Part[] files,
      HttpSession session,
      Map<String, Object> map) throws Exception {

    int category = board.getCategory();
    map.put("boardName", category == 1 ? "게시글" : "가입인사");
    map.put("category", category);

    try {
      Member loginUser = (Member) session.getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }
      board.setWriter(loginUser);

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      if (category == 1) {
        for (Part file : files) {
          if (file.getSize() == 0) {
            continue;
          }
          String filename = UUID.randomUUID().toString();
          file.write(this.uploadDir + "/" + filename);
          attachedFiles.add(new AttachedFile().filePath(filename));
        }
      }

      txManager.startTransaction();

      boardDao.add(board);
      if (attachedFiles.size() > 0) {
        for (AttachedFile attachedFile : attachedFiles) {
          attachedFile.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(attachedFiles);
      }

      txManager.commit();
      return "redirect:list?category=" + category;

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e2) {
      }
      throw e;
    }
  }

  @RequestMapping("/board/list")
  public String list(
      @RequestParam("category") int category,
      Map<String, Object> map) throws Exception {

    map.put("boardName", category == 1 ? "게시글" : "가입인사");
    map.put("category", category);
    map.put("list", boardDao.findAll(category));
    return "/board/list.jsp";
  }

  @RequestMapping("/board/view")
  public String view(
      @RequestParam("category") int category,
      @RequestParam("no") int no,
      Map<String, Object> map) throws Exception {

    Board board = boardDao.findBy(no);
    if (board == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }

    map.put("boardName", category == 1 ? "게시글" : "가입인사");
    map.put("category", category);
    map.put("board", board);
    if (category == 1) {
      map.put("files", attachedFileDao.findAllByBoardNo(no));
    }
    return "/board/view.jsp";
  }

  @RequestMapping("/board/update")
  public String update(
      Board board,
      @RequestParam("files") Part[] files,
      HttpSession session,
      Map<String, Object> map) throws Exception {

    try {
      Member loginUser = (Member) session.getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      Board old = boardDao.findBy(board.getNo());
      if (old == null) {
        throw new Exception("번호가 유효하지 않습니다.");

      } else if (old.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("권한이 없습니다.");
      }

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      if (board.getCategory() == 1) {
        for (Part file : files) {
          if (file.getSize() == 0) {
            continue;
          }
          String filename = UUID.randomUUID().toString();
          file.write(this.uploadDir + "/" + filename);
          attachedFiles.add(new AttachedFile().filePath(filename));
        }
      }

      txManager.startTransaction();
      boardDao.update(board);
      if (attachedFiles.size() > 0) {
        for (AttachedFile attachedFile : attachedFiles) {
          attachedFile.setBoardNo(board.getNo());
        }
        attachedFileDao.addAll(attachedFiles);
      }
      txManager.commit();
      return "redirect:list?category=" + board.getCategory();

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e2) {
      }
      throw e;
    }
  }

  @RequestMapping("/board/delete")
  public String delete(
      @RequestParam("category") int category,
      @RequestParam("no") int no,
      HttpSession session) throws Exception {

    try {
      Member loginUser = (Member) session.getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      Board board = boardDao.findBy(no);
      if (board == null) {
        throw new Exception("번호가 유효하지 않습니다.");

      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("권한이 없습니다.");
      }

      List<AttachedFile> files = attachedFileDao.findAllByBoardNo(no);

      txManager.startTransaction();
      attachedFileDao.deleteAll(no);
      boardDao.delete(no);
      txManager.commit();

      for (AttachedFile file : files) {
        new File(this.uploadDir + "/" + file.getFilePath()).delete();
      }

      return "redirect:list?category=" + category;

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e2) {
      }
      throw e;
    }
  }

  @RequestMapping("/board/file/delete")
  public String fileDelete(
      @RequestParam("category") int category,
      @RequestParam("no") int fileNo,
      HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    AttachedFile file = attachedFileDao.findByNo(fileNo);
    if (file == null) {
      throw new Exception("첨부파일 번호가 유효하지 않습니다.");
    }

    Member writer = boardDao.findBy(file.getBoardNo()).getWriter();
    if (writer.getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }

    attachedFileDao.delete(fileNo);
    new File(this.uploadDir + "/" + file.getFilePath()).delete();

    return "redirect:../view?category=" + category + "&no=" + file.getBoardNo();

  }
}
