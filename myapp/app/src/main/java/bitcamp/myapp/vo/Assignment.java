package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;

public class Assignment implements Serializable, CsvString {

  private static final long serialVersionUID = 100L;

  private String title;
  private String content;
  private Date deadline;

  @Override
  public String toCsvString() {
    return String.format("%s,%s,%s", this.title, this.content, this.deadline);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }
}
