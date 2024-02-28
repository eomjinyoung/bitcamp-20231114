package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
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

      request.setAttribute("member", member);
      request.getRequestDispatcher("/member/view.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("message", "조회 오류!");
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
