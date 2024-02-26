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

@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {

  private MemberDao memberDao;

  @Override
  public void init() {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = memberDao.findBy(no);
      if (member == null) {
        throw new Exception("회원 번호가 유효하지 않습니다.");
      }

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
      out.println("<form action='/member/update' method='post' enctype='multipart/form-data'>");
      out.println("<div>");
      out.printf(
          "  사진: <a href='%s'><img src='%1$s' height='80px'></a><br> <input name='photo' type='file'>\n",
          member.getPhoto() != null ? "/upload/" + member.getPhoto() : "/img/default-photo.jpeg");
      out.println("</div>");
      out.println("<div>");
      out.printf("  번호: <input readonly name='no' type='text' value='%d'>\n", member.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  이메일: <input name='email' type='text' value='%s'>\n", member.getEmail());
      out.println("</div>");
      out.println("<div>");
      out.printf("  이름: <input name='name' type='text' value='%s'>\n", member.getName());
      out.println("</div>");
      out.println("<div>");
      out.println("  암호: <input name='password' type='password'>");
      out.println("</div>");
      out.println("<div>");
      out.printf("  가입일: <input readonly type='text' value='%s'>\n", member.getCreatedDate());
      out.println("</div>");
      out.println("<div>");
      out.println("  <button>변경</button>");
      out.printf("  <a href='/member/delete?no=%d'>[삭제]</a>\n", no);
      out.println("</div>");
      out.println("</form>");

      request.getRequestDispatcher("/footer").include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("message", "조회 오류!");
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
