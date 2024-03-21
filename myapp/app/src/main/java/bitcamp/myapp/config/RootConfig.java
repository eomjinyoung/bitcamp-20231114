package bitcamp.myapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(value = {
    "bitcamp.util.*"//,
    // "bitcamp.myapp.dao.*"
})
@PropertySource({
    "classpath:config/jdbc.properties"
})
public class RootConfig {

  public RootConfig() {
    System.out.println("RootConfig()... 호출됨!");
  }
}
