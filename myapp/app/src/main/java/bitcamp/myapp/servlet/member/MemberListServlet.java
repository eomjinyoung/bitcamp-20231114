package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      List<Member> list = memberDao.findAll();

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
      out.println("<a href='/member/add'>새 회원</a>");
      out.println("<table border='1'>");
      out.println("    <thead>");
      out.println("    <tr> <th>번호</th> <th>이름</th> <th>이메일</th> <th>가입일</th> </tr>");
      out.println("    </thead>");
      out.println("    <tbody>");
      for (Member member : list) {
        out.printf(
            "<tr> <td>%d</td> <td><img src='%s' height='20px'> <a href='/member/view?no=%1$d'>%s</a></td> <td>%s</td> <td>%s</td> </tr>\n",
            member.getNo(),
            member.getPhoto() != null ? "/upload/" + member.getPhoto() : "/img/default-photo.jpeg",
            member.getName(),
            member.getEmail(),
            member.getCreatedDate());
      }
      out.println("    </tbody>");
      out.println("</table>");

      request.getRequestDispatcher("/footer").include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("message", "목록 오류!");
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
