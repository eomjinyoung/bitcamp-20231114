package bitcamp.myapp.config;

import java.io.File;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

public class AppWebApplicationInitializer3 /*extends AbstractDispatcherServletInitializer*/ {

  ServletContext servletContext;
  AnnotationConfigWebApplicationContext rootContext;

  protected WebApplicationContext createRootApplicationContext() {
    rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(RootConfig.class);
    rootContext.refresh();
    return rootContext;
  }

  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AppConfig.class);
    appContext.setParent(this.rootContext);
    appContext.setServletContext(this.servletContext);
    appContext.refresh();
    return appContext;
  }

  protected String[] getServletMappings() {
    return new String[]{"/app/*"};
  }

  protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(
        new File("./temp").getAbsolutePath(),
        //new File(System.getProperty("java.io.tmpdir")).getAbsolutePath(),
        1024 * 1024 * 10,
        1024 * 1024 * 100,
        1024 * 1024 * 1
    ));
  }

  protected Filter[] getServletFilters() {
    return new Filter[]{new CharacterEncodingFilter("UTF-8")};
  }

  public void onStartup(ServletContext servletContext) throws ServletException {
    this.servletContext = servletContext;
    //super.onStartup(servletContext);
  }
}
