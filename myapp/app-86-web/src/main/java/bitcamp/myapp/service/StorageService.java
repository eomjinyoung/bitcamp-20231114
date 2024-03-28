package bitcamp.myapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  String upload(String bucketName, String path, MultipartFile multipartFile) throws Exception;

  void delete(String bucketName, String path, String objectName) throws Exception;
}
