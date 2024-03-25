package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Assignment implements Serializable {

  private static final long serialVersionUID = 100L;

  private int no;
  private String title;
  private String content;
  private Date deadline;

}
