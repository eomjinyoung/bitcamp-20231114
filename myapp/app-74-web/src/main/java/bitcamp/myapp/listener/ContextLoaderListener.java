package bitcamp.myapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  // 웹애플리케이션이 사용할 자원을 준비시키고 해제시키는 역할

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("웹애플리케이션 자원 준비!");

    try {
      ApplicationContext ctx = new ClassPathXmlApplicationContext("config/application-context.xml");

      ServletContext 웹애플리케이션저장소 = sce.getServletContext();
      웹애플리케이션저장소.setAttribute("applicationContext", ctx);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
