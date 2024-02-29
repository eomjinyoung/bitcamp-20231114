package bitcamp.myapp.servlet;

import bitcamp.myapp.controller.HomeController;
import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.controller.assignment.AssignmentAddController;
import bitcamp.myapp.controller.assignment.AssignmentDeleteController;
import bitcamp.myapp.controller.assignment.AssignmentListController;
import bitcamp.myapp.controller.assignment.AssignmentUpdateController;
import bitcamp.myapp.controller.assignment.AssignmentViewController;
import bitcamp.myapp.controller.auth.LoginController;
import bitcamp.myapp.controller.auth.LogoutController;
import bitcamp.myapp.controller.member.MemberAddController;
import bitcamp.myapp.controller.member.MemberDeleteController;
import bitcamp.myapp.controller.member.MemberListController;
import bitcamp.myapp.controller.member.MemberUpdateController;
import bitcamp.myapp.controller.member.MemberViewController;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  private Map<String, PageController> controllerMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    ServletContext ctx = this.getServletContext();
    BoardDao boardDao = (BoardDao) ctx.getAttribute("boardDao");
    MemberDao memberDao = (MemberDao) ctx.getAttribute("memberDao");
    AssignmentDao assignmentDao = (AssignmentDao) ctx.getAttribute("assignmentDao");
    AttachedFileDao attachedFileDao = (AttachedFileDao) ctx.getAttribute("attachedFileDao");

    controllerMap.put("/home", new HomeController());

    String memberUploadDir = this.getServletContext().getRealPath("/upload");
    controllerMap.put("/member/list", new MemberListController(memberDao));
    controllerMap.put("/member/view", new MemberViewController(memberDao));
    controllerMap.put("/member/add", new MemberAddController(memberDao, memberUploadDir));
    controllerMap.put("/member/update", new MemberUpdateController(memberDao, memberUploadDir));
    controllerMap.put("/member/delete", new MemberDeleteController(memberDao, memberUploadDir));

    controllerMap.put("/assignment/list", new AssignmentListController(assignmentDao));
    controllerMap.put("/assignment/view", new AssignmentViewController(assignmentDao));
    controllerMap.put("/assignment/add", new AssignmentAddController(assignmentDao));
    controllerMap.put("/assignment/update", new AssignmentUpdateController(assignmentDao));
    controllerMap.put("/assignment/delete", new AssignmentDeleteController(assignmentDao));

    controllerMap.put("/auth/login", new LoginController(memberDao));
    controllerMap.put("/auth/logout", new LogoutController());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // URL에서 요청한 페이지 컨트롤러를 실행한다.
    PageController controller = controllerMap.get(request.getPathInfo());
    if (controller == null) {
      throw new ServletException(request.getPathInfo() + " 요청 페이지를 찾을 수 없습니다.");
    }

    try {
      String viewUrl = controller.execute(request, response);

      // 페이지 컨트롤러가 알려준 JSP로 포워딩 한다.
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).forward(request, response);
      }

    } catch (Exception e) {
      // 페이지 컨트롤러에서 오류가 발생했으면 오류페이지로 포워딩한다.
      request.setAttribute("message", request.getPathInfo() + " 실행 오류!");

      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      request.setAttribute("detail", stringWriter.toString());

      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
