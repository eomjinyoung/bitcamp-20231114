package bitcamp.myapp.service.impl;

import bitcamp.myapp.service.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NcpStorageService implements StorageService, InitializingBean {

  private static Log log = LogFactory.getLog(NcpStorageService.class);

  final String endPoint;
  final String regionName;
  final String accessKey;
  final String secretKey;

  public NcpStorageService(
      @Value("${ncp.ss.endpoint}") String endPoint,
      @Value("${ncp.ss.regionname}") String regionName,
      @Value("${ncp.accesskey}") String accessKey,
      @Value("${ncp.secretkey}") String secretKey) {
    this.endPoint = endPoint;
    this.regionName = regionName;
    this.accessKey = accessKey;
    this.secretKey = secretKey;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug(String.format("endPoint: %s", this.endPoint));
    log.debug(String.format("regionName: %s", this.regionName));
    log.debug(String.format("accessKey: %s", this.accessKey));
    log.debug(String.format("secretKey: %s", this.secretKey));
  }

  @Override
  public String upload(MultipartFile multipartFile) throws Exception {
    return null;
  }
}
