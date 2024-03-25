package bitcamp.myapp.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AttachedFile {

  private int no;
  private String filePath;
  private int boardNo;

}
