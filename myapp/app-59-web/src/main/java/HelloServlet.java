import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/hello")
public class HelloServlet implements Servlet {

  public HelloServlet() {
    System.out.println("HelloServlet: 생성됨!");
  }

  @Override
  public void init(ServletConfig servletConfig) throws ServletException {
    System.out.println("HelloServlet: init() 호출됨!");
  }

  @Override
  public ServletConfig getServletConfig() {
    System.out.println("HelloServlet: getServletConfig() 호출됨!");
    return null;
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {
    System.out.println("HelloServlet: service() 호출됨!");

    servletResponse.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = servletResponse.getWriter();
    out.println("안녕하세요!!!");
  }

  @Override
  public String getServletInfo() {
    System.out.println("HelloServlet: getServletInfo() 호출됨!");
    return null;
  }

  @Override
  public void destroy() {
    System.out.println("HelloServlet: destroy() 호출됨!");
  }
}
