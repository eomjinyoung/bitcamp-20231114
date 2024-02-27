package com.eomcs.web.ex12;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener01 implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    ServletContext sc = sce.getServletContext();

    Servlet03 s = new Servlet03();
    Dynamic 서블릿설정정보 = sc.addServlet("ex12.Servlet03", s); // <servlet>...</servlet>
    서블릿설정정보.addMapping("/ex12/s03"); // <servlet-mapping>...</servlet-mapping>
  }
}
