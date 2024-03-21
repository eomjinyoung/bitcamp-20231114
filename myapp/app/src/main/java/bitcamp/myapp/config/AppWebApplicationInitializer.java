package bitcamp.myapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AppWebApplicationInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(RootConfig.class);
    rootContext.refresh();
    servletContext.addListener(new ContextLoaderListener(rootContext));
/*
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AppConfig.class);
    appContext.setParent(rootContext);
    appContext.refresh();
    Dynamic registration = servletContext.addServlet("app", new DispatcherServlet(appContext));
    registration.addMapping("/app/*");
    registration.setLoadOnStartup(1);
    registration.setMultipartConfig(new MultipartConfigElement(
        new File("./temp").getAbsolutePath(),
        //new File(System.getProperty("java.io.tmpdir")).getAbsolutePath(),
        1024 * 1024 * 10,
        1024 * 1024 * 100,
        1024 * 1024 * 1
    ));

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
    javax.servlet.FilterRegistration.Dynamic filterRegistration =
        servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
    filterRegistration.addMappingForServletNames(
        EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),
        false,
        new String[]{"app"}
    );
*/
  }
}
