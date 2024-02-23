package bitcamp.myapp.servlet;

import bitcamp.myapp.vo.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<header>");
    out.println(
        "  <img src='https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png'>");
    out.println("  <a href='/assignment/list'>과제</a>");
    out.println("  <a href='/board/list?category=1'>게시글</a>");
    out.println("  <a href='/member/list'>회원</a>");
    out.println("  <a href='/board/list?category=2'>가입인사</a>");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("  <a href='/auth/login'>로그인</a>");
    } else {
      out.printf("  <span>%s</span>\n", loginUser.getName());
      out.println("  <a href='/auth/logout'>로그아웃</a>");
    }

    out.println("</header>");

  }
}
