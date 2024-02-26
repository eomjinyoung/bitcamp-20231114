package bitcamp.myapp.servlet.assignment;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/list")
public class AssignmentListServlet extends HttpServlet {

  private AssignmentDao assignmentDao;

  @Override
  public void init() {
    assignmentDao = (AssignmentDao) this.getServletContext().getAttribute("assignmentDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>과제</h1>");

    out.println("<a href='/assignment/add'>새 과제</a>");

    try {
      out.println("<table border='1'>");
      out.println("    <thead>");
      out.println("    <tr> <th>번호</th> <th>과제</th> <th>제출마감일</th> </tr>");
      out.println("    </thead>");
      out.println("    <tbody>");

      List<Assignment> list = assignmentDao.findAll();

      for (Assignment assignment : list) {
        out.printf(
            "<tr> <td>%d</td> <td><a href='/assignment/view?no=%1$d'>%s</a></td> <td>%s</td> </tr>\n",
            assignment.getNo(),
            assignment.getTitle(),
            assignment.getDeadline());
      }

      out.println("    </tbody>");
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>목록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}
