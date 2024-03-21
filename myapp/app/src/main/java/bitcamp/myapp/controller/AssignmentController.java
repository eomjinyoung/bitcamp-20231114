package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {

  private final Log log = LogFactory.getLog(this.getClass());
  private AssignmentDao assignmentDao;

  public AssignmentController(AssignmentDao assignmentDao) {
    log.debug("AssignmentController() 호출됨!");
    this.assignmentDao = assignmentDao;
  }

  @GetMapping("form")
  public String form() throws Exception {
    return "/assignment/form.jsp";
  }

  @PostMapping("add")
  public String add(Assignment assignment) throws Exception {
    System.out.println(assignment);
    assignmentDao.add(assignment);
    return "redirect:list";
  }

  @GetMapping("list")
  public String list(Model model) throws Exception {
    model.addAttribute("list", assignmentDao.findAll());
    return "/assignment/list.jsp";
  }

  @GetMapping("view")
  public String view(int no, Model model) throws Exception {
    Assignment assignment = assignmentDao.findBy(no);
    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    model.addAttribute("assignment", assignment);
    return "/assignment/view.jsp";
  }

  @PostMapping("update")
  public String update(Assignment assignment) throws Exception {
    Assignment old = assignmentDao.findBy(assignment.getNo());
    if (old == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    assignmentDao.update(assignment);
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (assignmentDao.delete(no) == 0) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    return "redirect:list";
  }
}
