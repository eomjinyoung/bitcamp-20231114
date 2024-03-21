package bitcamp.myapp.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(value = {
    "bitcamp.myapp.dao",
    "bitcamp.util"
})
@PropertySource({
    "classpath:config/jdbc.properties"
})
public class RootConfig {

  private final Log log = LogFactory.getLog(this.getClass());

  public RootConfig() {
    log.debug("생성자 호출됨!");
  }
}
