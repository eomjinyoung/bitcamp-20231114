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

@WebServlet("/assignment/update")
public class AssignmentUpdateServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Assignment old = assignmentDao.findBy(no);
      if (old == null) {
        throw new Exception("과제 번호가 유효하지 않습니다.");
      }

      Assignment assignment = new Assignment();
      assignment.setNo(old.getNo());
      assignment.setTitle(request.getParameter("title"));
      assignment.setContent(request.getParameter("content"));
      assignment.setDeadline(Date.valueOf(request.getParameter("deadline")));

      assignmentDao.update(assignment);
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("message", "변경 오류!");
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
