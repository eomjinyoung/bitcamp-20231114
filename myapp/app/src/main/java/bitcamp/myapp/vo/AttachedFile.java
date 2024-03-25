package bitcamp.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AttachedFile {

  private int no;
  private String filePath;
  private int boardNo;

}
