package bitcamp.myapp.controller.assignment;

import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.AssignmentDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignmentListController {

  private AssignmentDao assignmentDao;

  public AssignmentListController(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", assignmentDao.findAll());
    return "/assignment/list.jsp";
  }
}
