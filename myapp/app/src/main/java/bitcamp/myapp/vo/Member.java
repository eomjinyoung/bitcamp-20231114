package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable/*, CsvString*/ {

  private static final long servialVersionUID = 100L;
  
  private String email;
  private String name;
  private String password;
  private Date createdDate;

//  @Override
//  public String toCsvString() {
//    return String.format("%s,%s,%s,%d", this.email, this.name, this.password,
//        this.createdDate.getTime());
//  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
