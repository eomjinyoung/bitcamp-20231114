package bitcamp.myapp.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"bitcamp.myapp.dao", "bitcamp.myapp.service"})
@PropertySource({
    "classpath:config/ncp.properties",
    "classpath:config/ncp-secret.properties"
})
public class RootConfig {

  private final Log log = LogFactory.getLog(this.getClass());

  public RootConfig() {
    log.debug("생성자 호출됨!");
  }


}
