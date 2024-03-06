package bitcamp.myapp.controller;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.sql.Date;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class AssignmentController {

  private AssignmentDao assignmentDao;

  public AssignmentController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @RequestMapping("/assignment/form")
  public String form() throws Exception {
    return "/assignment/form.jsp";
  }

  @RequestMapping("/assignment/add")
  public String add(Assignment assignment) throws Exception {
    System.out.println(assignment);
    assignmentDao.add(assignment);
    return "redirect:list";
  }

  @RequestMapping("/assignment/list")
  public String list(HttpServletRequest request) throws Exception {
    request.setAttribute("list", assignmentDao.findAll());
    return "/assignment/list.jsp";
  }

  @RequestMapping("/assignment/view")
  public String view(@RequestParam("no") int no, ServletRequest request) throws Exception {
    Assignment assignment = assignmentDao.findBy(no);
    if (assignment == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    request.setAttribute("assignment", assignment);
    return "/assignment/view.jsp";
  }

  @RequestMapping("/assignment/update")
  public String update(
      @RequestParam("no") int no,
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("deadline") Date deadline) throws Exception {

    Assignment old = assignmentDao.findBy(no);
    if (old == null) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }

    Assignment assignment = new Assignment();
    assignment.setNo(old.getNo());
    assignment.setTitle(title);
    assignment.setContent(content);
    assignment.setDeadline(deadline);

    assignmentDao.update(assignment);
    return "redirect:list";
  }

  @RequestMapping("/assignment/delete")
  public String delete(@RequestParam("no") int no) throws Exception {
    if (assignmentDao.delete(no) == 0) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    return "redirect:list";
  }
}
