package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/add")
public class AssignmentAddServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/assignment/form.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Assignment assignment = new Assignment();
      assignment.setTitle(request.getParameter("title"));
      assignment.setContent(request.getParameter("content"));
      assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

      assignmentDao.add(assignment);
      response.sendRedirect("/assignment/list");

    } catch (Exception e) {
      request.setAttribute("message", "과제 등록 오류!");
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
