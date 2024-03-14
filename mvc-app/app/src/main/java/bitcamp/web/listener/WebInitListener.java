package bitcamp.web.listener;

import bitcamp.config.AdminConfig;
import bitcamp.config.AppConfig;
import bitcamp.config.RootConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@WebListener
public class WebInitListener implements ServletContextListener {

  private static Log log = LogFactory.getLog(WebInitListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.debug("contextInitialized() 호출됨!");

    ServletContext sc = sce.getServletContext();

    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(RootConfig.class);
    rootContext.refresh();

    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.setParent(rootContext);
    appContext.register(AppConfig.class);
    appContext.refresh();

    Dynamic 서블릿설정 = sc.addServlet("app", new DispatcherServlet(appContext));
    서블릿설정.addMapping("/app/*");
    서블릿설정.setLoadOnStartup(1);

    AnnotationConfigWebApplicationContext adminContext = new AnnotationConfigWebApplicationContext();
    adminContext.setParent(rootContext);
    adminContext.register(AdminConfig.class);
    adminContext.refresh();

    Dynamic 서블릿설정2 = sc.addServlet("admin", new DispatcherServlet(adminContext));
    서블릿설정2.addMapping("/admin/*");
    서블릿설정2.setLoadOnStartup(1);

  }
}
