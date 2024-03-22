package bitcamp.myapp.config;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

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

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public DataSource dataSource(
      @Value("${jdbc.url}") String url,
      @Value("${jdbc.username}") String username,
      @Value("${jdbc.password}") String password) {
    return new DriverManagerDataSource(url, username, password);
  }

  @Bean

  public SqlSessionFactory sqlSessionFactory(ApplicationContext ctx, DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setTypeAliasesPackage("bitcamp.myapp.vo");
    sqlSessionFactoryBean.setMapperLocations(ctx.getResources("classpath:mapper/*Mapper.xml"));
    sqlSessionFactoryBean.setDataSource(dataSource);

    return sqlSessionFactoryBean.getObject();
  }
}
