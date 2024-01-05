package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable, CsvString {

  private static final long serialVersionUID = 100L;

  private String title;
  private String content;
  private String writer;
  private Date createdDate;

  // 팩토리 메서드
  public static Board createFromCsv(String csv) {
    String[] values = csv.split(",");
    Board obj = new Board();
    obj.setTitle(values[0]);
    obj.setContent(values[1]);
    obj.setWriter(values[2]);
    obj.setCreatedDate(new Date(Long.valueOf(values[3])));
    return obj;
  }

  @Override
  public String toCsvString() {
    return String.format("%s,%s,%s,%d", this.title, this.content, this.writer,
        this.createdDate.getTime());
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

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
