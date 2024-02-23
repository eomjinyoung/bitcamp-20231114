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

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
    out.println("<h1>회원</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member old = memberDao.findBy(no);
      if (old == null) {
        out.println("<p>회원 번호가 유효하지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      Member member = new Member();
      member.setNo(old.getNo());
      member.setEmail(request.getParameter("email"));
      member.setName(request.getParameter("name"));
      member.setPassword(request.getParameter("password"));
      member.setCreatedDate(old.getCreatedDate());

      memberDao.update(member);
      out.println("<p>회원을 변경했습니다.</p>");

    } catch (Exception e) {
      out.println("<p>회원 변경 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
