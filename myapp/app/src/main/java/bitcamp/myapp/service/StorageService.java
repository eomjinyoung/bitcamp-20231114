package bitcamp.myapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  String upload(MultipartFile multipartFile) throws Exception;
}
