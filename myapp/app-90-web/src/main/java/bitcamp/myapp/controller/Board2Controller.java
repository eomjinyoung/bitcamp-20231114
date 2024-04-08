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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board2")
@SessionAttributes("attachedFiles")
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
      HttpSession session,
      SessionStatus sessionStatus) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }
    board.setWriter(loginUser);

    // 게시글 등록할 때 삽입한 이미지 목록을 세션에서 가져온다.
    List<AttachedFile> attachedFiles = (List<AttachedFile>) session.getAttribute("attachedFiles");

    for (int i = attachedFiles.size() - 1; i >= 0; i--) {
      AttachedFile attachedFile = attachedFiles.get(i);
      if (board.getContent().indexOf(attachedFile.getFilePath()) == -1) {
        // Object Storage에 업로드 한 파일 중에서 게시글 콘텐트에 포함되지 않은 것은 삭제한다.
        storageService.delete(this.bucketName, this.uploadDir, attachedFile.getFilePath());
        log.debug(String.format("%s 파일 삭제!", attachedFile.getFilePath()));
        attachedFiles.remove(i);
      }
    }
    if (attachedFiles.size() > 0) {
      board.setFileList(attachedFiles);
    }

    boardService.add(board);

    // 게시글을 등록하는 과정에서 세션에 임시 보관한 첨부파일 목록 정보를 제거한다.
    sessionStatus.setComplete();

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
      HttpSession session,
      SessionStatus sessionStatus) throws Exception {

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

    // 게시글 변경할 때 삽입한 이미지 목록을 세션에서 가져온다.
    List<AttachedFile> attachedFiles = (List<AttachedFile>) session.getAttribute("attachedFiles");

    if (old.getFileList().size() > 0) {
      // 기존 게시글에 등록된 이미지 목록과 합친다.
      attachedFiles.addAll(old.getFileList());
    }

    for (int i = attachedFiles.size() - 1; i >= 0; i--) {
      AttachedFile attachedFile = attachedFiles.get(i);
      if (board.getContent().indexOf(attachedFile.getFilePath()) == -1) {
        // Object Storage에 업로드 한 파일 중에서 게시글 콘텐트에 포함되지 않은 것은 삭제한다.
        storageService.delete(this.bucketName, this.uploadDir, attachedFile.getFilePath());
        log.debug(String.format("%s 파일 삭제!", attachedFile.getFilePath()));
        attachedFiles.remove(i);
      }
    }

    if (attachedFiles.size() > 0) {
      board.setFileList(attachedFiles);
    }

    boardService.update(board);

    // 게시글을 변경하는 과정에서 세션에 임시 보관한 첨부파일 목록 정보를 제거한다.
    sessionStatus.setComplete();

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
  public Object fileUpload(MultipartFile[] files, HttpSession session, Model model) throws Exception {
    // NCP Object Storage에 저장한 파일의 이미지 이름을 보관할 컬렉션을 준비한다.
    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    // 로그인 여부를 검사한다.
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      // 로그인 하지 않았으면 빈 목록을 보낸다.
      return attachedFiles;
    }

    // 클라이언트가 보낸 멀티파트 파일을 NCP Object Storage에 업로드한다.
    for (MultipartFile file : files) {
      if (file.getSize() == 0) {
        continue;
      }
      String filename = storageService.upload(this.bucketName, this.uploadDir, file);
      attachedFiles.add(AttachedFile.builder().filePath(filename).build());
    }

    // 업로드한 파일 목록을 세션에 보관한다.
    model.addAttribute("attachedFiles", attachedFiles);

    // 클라이언트에서 이미지 이름을 가지고 <img> 태그를 생성할 수 있도록
    // 업로드한 파일의 이미지 정보를 보낸다.
    return attachedFiles;
  }

}
