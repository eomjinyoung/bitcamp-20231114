package bitcamp.myapp.controller.assignment;

import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.AssignmentDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignmentDeleteController {

  private AssignmentDao assignmentDao;

  public AssignmentDeleteController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    if (assignmentDao.delete(no) == 0) {
      throw new Exception("과제 번호가 유효하지 않습니다.");
    }
    return "redirect:list";
  }
}
