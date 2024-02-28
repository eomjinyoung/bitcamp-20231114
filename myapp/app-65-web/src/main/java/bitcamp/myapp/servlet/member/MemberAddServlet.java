package bitcamp.myapp.servlet.member;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private MemberDao memberDao;
  private String uploadDir;

  @Override
  public void init() {
    this.memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/member/form.jsp").forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member member = new Member();
      member.setEmail(request.getParameter("email"));
      member.setName(request.getParameter("name"));
      member.setPassword(request.getParameter("password"));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        member.setPhoto(filename);
        photoPart.write(this.uploadDir + "/" + filename);
      }

      memberDao.add(member);
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("message", "등록 오류!");
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
