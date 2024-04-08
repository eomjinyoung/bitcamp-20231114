package bitcamp.myapp.controller;

import bitcamp.myapp.service.Board2Service;
import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board2")
public class Board2Controller {

  private static final Log log = LogFactory.getLog(Board2Controller.class);
  private final Board2Service boardService;
  private final StorageService storageService;
  private String uploadDir = "board/";

  @Value("${ncp.ss.bucketname}")
  private String bucketName;

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add")
  public String add(
      Board board,
      String filenames,
      HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }
    board.setWriter(loginUser);

    ArrayList<AttachedFile> files = new ArrayList<>();
    for (String filename : filenames.split(",")) {
      if (board.getContent().indexOf(filename) != -1) {
        files.add(AttachedFile.builder().filePath(filename).build());
        continue;
      }
      // Object Storage에 업로드 한 파일 중에서 게시글 콘텐트에 포함되지 않은 것은 삭제한다.
      storageService.delete(this.bucketName, this.uploadDir, filename);
      log.debug(String.format("%s 파일 삭제!", filename));
    }
    if (files.size() > 0) {
      board.setFileList(files);
    }

    boardService.add(board);

    return "redirect:list";
  }

  @GetMapping("list")
  public void list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "3") int pageSize,
      Model model) throws Exception {

    if (pageSize < 3 || pageSize > 20) {
      pageSize = 3;
    }

    if (pageNo < 1) {
      pageNo = 1;
    }

    int numOfRecord = boardService.countAll();
    int numOfPage = numOfRecord / pageSize + ((numOfRecord % pageSize) > 0 ? 1 : 0);

    if (pageNo > numOfPage) {
      pageNo = numOfPage;
    }

    model.addAttribute("list", boardService.list(pageNo, pageSize));
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("numOfPage", numOfPage);
  }

  @GetMapping("view")
  public void view(int no, Model model) throws Exception {
    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("번호가 유효하지 않습니다.");
    }

    model.addAttribute("board", board);
  }

  @PostMapping("update")
  public String update(
      Board board,
      String filenames,
      HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    Board old = boardService.get(board.getNo());
    if (old == null) {
      throw new Exception("번호가 유효하지 않습니다.");

    } else if (old.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }

    ArrayList<String> filenameList = new ArrayList<>();
    for (String filename : filenames.split(",")) {
      filenameList.add(filename);
    }
    for (AttachedFile attachedFile : board.getFileList()) {
      filenameList.add(attachedFile.getFilePath());
    }


    ArrayList<AttachedFile> files = new ArrayList<>();
    for (String filename : filenameList) {
      if (board.getContent().indexOf(filename) != -1) {
        files.add(AttachedFile.builder().filePath(filename).build());
        continue;
      }
      // Object Storage에 업로드 한 파일 중에서 게시글 콘텐트에 포함되지 않은 것은 삭제한다.
      storageService.delete(this.bucketName, this.uploadDir, filename);
      log.debug(String.format("%s 파일 삭제!", filename));
    }
    if (files.size() > 0) {
      board.setFileList(files);
    }

    boardService.update(board);

    return "redirect:list";

  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("번호가 유효하지 않습니다.");

    } else if (board.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }

    List<AttachedFile> files = boardService.getAttachedFiles(no);

    boardService.delete(no);

    for (AttachedFile file : files) {
      storageService.delete(this.bucketName, this.uploadDir, file.getFilePath());
    }

    return "redirect:list";
  }

  @GetMapping("file/delete")
  public String fileDelete(int no, HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }

    AttachedFile file = boardService.getAttachedFile(no);
    if (file == null) {
      throw new Exception("첨부파일 번호가 유효하지 않습니다.");
    }

    Member writer = boardService.get(file.getBoardNo()).getWriter();
    if (writer.getNo() != loginUser.getNo()) {
      throw new Exception("권한이 없습니다.");
    }

    boardService.deleteAttachedFile(no);

    storageService.delete(this.bucketName, this.uploadDir, file.getFilePath());

    return "redirect:../view?no=" + file.getBoardNo();
  }

  @PostMapping("file/upload")
  @ResponseBody
  public Object fileUpload(MultipartFile[] files, HttpSession session) throws Exception {

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return attachedFiles;
    }

    for (MultipartFile file : files) {
      if (file.getSize() == 0) {
        continue;
      }
      String filename = storageService.upload(this.bucketName, this.uploadDir, file);
      attachedFiles.add(AttachedFile.builder().filePath(filename).build());
    }

    return attachedFiles;
  }
}
