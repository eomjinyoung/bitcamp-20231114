package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable/*, CsvString*/ {

  private static final long servialVersionUID = 100L;
  
  private String title;
  private String content;
  private String writer;
  private Date createdDate;

//  @Override
//  public String toCsvString() {
//    return String.format("%s,%s,%s,%d", this.title, this.content, this.writer,
//        this.createdDate.getTime());
//  }

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
