package bitcamp.myapp.service.impl;

import bitcamp.myapp.service.StorageService;
import org.springframework.web.multipart.MultipartFile;

public class NcpStorageService implements StorageService {

  final String endPoint = "https://kr.object.ncloudstorage.com";
  final String regionName = "kr-standard";
  final String accessKey = "ACCESS_KEY";
  final String secretKey = "SECRET_KEY";

  @Override
  public String upload(MultipartFile multipartFile) throws Exception {
    return null;
  }
}
