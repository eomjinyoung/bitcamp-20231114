package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BoardController {

  private TransactionManager txManager;
  private BoardDao boardDao;
  private AttachedFileDao attachedFileDao;
  private String uploadDir;

  public BoardController(TransactionManager txManager, BoardDao boardDao,
      AttachedFileDao attachedFileDao, String uploadDir) {
    this.txManager = txManager;
    this.boardDao = boardDao;
    this.attachedFileDao = attachedFileDao;
    this.uploadDir = uploadDir;
  }

  @RequestMapping("/board/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.valueOf(request.getParameter("category"));
    request.setAttribute("boardName", category == 1 ? "게시글" : "가입인사");
    request.setAttribute("category", category);

    if (request.getMethod().equals("GET")) {
      return "/board/form.jsp";
    }

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      Board board = new Board();
      board.setCategory(category);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setWriter(loginUser);

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      if (category == 1) {
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
          if (!part.getName().equals("files") || part.getSize() == 0) {
            continue;
          }
          String filename = UUID.randomUUID().toString();
          part.write(this.uploadDir + "/" + filename);
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
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int category = Integer.valueOf(request.getParameter("category"));
    request.setAttribute("boardName", category == 1 ? "게시글" : "가입인사");
    request.setAttribute("list", boardDao.findAll(category));
    request.setAttribute("category", category);
    return "/board/list.jsp";
  }

  @RequestMapping("/board/view")
  public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.valueOf(request.getParameter("category"));
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDao.findBy(no);
    if (board == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }

    request.setAttribute("boardName", category == 1 ? "게시글" : "가입인사");
    request.setAttribute("category", category);
    request.setAttribute("board", board);
    if (category == 1) {
      request.setAttribute("files", attachedFileDao.findAllByBoardNo(no));
    }
    return "/board/view.jsp";
  }

  @RequestMapping("/board/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.valueOf(request.getParameter("category"));
    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findBy(no);
      if (board == null) {
        throw new Exception("번호가 유효하지 않습니다.");

      } else if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("권한이 없습니다.");
      }

      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      if (category == 1) {
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
          if (!part.getName().equals("files") || part.getSize() == 0) {
            continue;
          }
          String filename = UUID.randomUUID().toString();
          part.write(this.uploadDir + "/" + filename);
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
      return "redirect:list?category=" + category;

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e2) {
      }
      throw e;
    }
  }

  @RequestMapping("/board/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.valueOf(request.getParameter("category"));

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하시기 바랍니다!");
      }

      int no = Integer.parseInt(request.getParameter("no"));
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
  public String fileDelete(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    int category = Integer.valueOf(request.getParameter("category"));

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    int fileNo = Integer.parseInt(request.getParameter("no"));
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
