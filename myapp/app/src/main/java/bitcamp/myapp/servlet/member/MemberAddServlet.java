package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
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

    out.println("<h1>과제 관리 시스템</h1>");

    out.println("<h2>회원</h2>");

    out.println("<form action='/member/add' method='post'>");
    out.println("  <div>");
    out.println("        이메일: <input name='email' type='text'>");
    out.println("  </div>");
    out.println("  <div>");
    out.println("        이름: <input name='name' type='text'>");
    out.println("  </div>");
    out.println("  <div>");
    out.println("        암호: <input name='password' type='password'>");
    out.println("  </div>");
    out.println("  <div>");
    out.println("    <button>등록</button>");
    out.println("  </div>");
    out.println("</form>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member member = new Member();
      member.setEmail(request.getParameter("email"));
      member.setName(request.getParameter("name"));
      member.setPassword(request.getParameter("password"));

      memberDao.add(member);
      response.sendRedirect("list");

    } catch (Exception e) {
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

      out.println("<h1>회원</h1>");

      out.println("<p>회원등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");

      request.getRequestDispatcher("/footer").include(request, response);

      out.println("</body>");
      out.println("</html>");
    }
  }
}
