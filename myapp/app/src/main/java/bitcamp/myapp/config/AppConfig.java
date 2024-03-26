package bitcamp.myapp.config;

import java.nio.charset.StandardCharsets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@ComponentScan({"bitcamp.myapp.controller"})
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
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/jsp/");
    //viewResolver.setSuffix(".jsp");
    viewResolver.setViewNames("*.jsp");
    viewResolver.setOrder(1);
    return viewResolver;
  }

  @Bean
  public ThymeleafViewResolver thymeleafViewResolver(ISpringTemplateEngine springTemplateEngine) {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(springTemplateEngine);
    viewResolver.setViewNames(new String[]{"*.html", "*.xhtml", "*"});
    viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    viewResolver.setOrder(2);
    return viewResolver;
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver(ApplicationContext applicationContext) {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(applicationContext);
    templateResolver.setPrefix("/WEB-INF/templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);

    // HTML 템플릿을 Thymeleaf 엔진이 사용할 수 있도록
    // 특정 형식에 맞춰 컴파일 해 둘 것인가?
    // => 컴파일? 어떤 형식의 데이터를 다른 형식의 데이터로 바꾸는 것도 컴파일이다.
    // => cacheable = true
    //    템플릿 파일을 한 번 컴파일하면 캐시에 보관해 둔다.
    //    실행할 때마다 캐시에 보관된 것을 사용하여 화면을 생성한다.
    //    실행할 때마다 매번 컴파일하지 않기 때문에 실행 속도가 빠르다.
    //    단 개발하는 동안에는 불편하다.
    //    왜? 개발하는 동안에는 템플릿 파일의 내용을 자주 바꾸기 때문이다.
    //    바꾼 템플릿 파일을 적용하려면 서버를 다시 실행해야 한다.
    // => cacheable = false
    //    템플릿 파일의 컴파일 결과를 캐시에 보관하지 않는다.
    //    매번 실행할 때마다 다시 템플릿 파일을 컴파일 한다.
    //    개발하는 동안에 필요한 설정한다.
    ///   개발을 완료하면 캐시를 사용하는 것이 성능에 좋다.
    templateResolver.setCacheable(false);
    
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    templateEngine.setEnableSpringELCompiler(true);
    return templateEngine;
  }
}
