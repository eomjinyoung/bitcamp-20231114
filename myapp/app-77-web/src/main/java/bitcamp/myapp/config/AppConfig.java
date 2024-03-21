package bitcamp.myapp.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(value = {
    "bitcamp.myapp.controller"
})
public class AppConfig {

  private final Log log = LogFactory.getLog(this.getClass());

  public AppConfig() {
    log.debug("생성자 호출됨!");
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Bean
  public ViewResolver viewResolver() {
    return new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
  }
}
