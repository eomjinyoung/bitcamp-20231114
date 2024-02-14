package bitcamp.myapp.vo;

public class AttachedFile {

  private int no;
  private String filePath;
  private int boardNo;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getBoardNo() {
    return boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }

  public AttachedFile filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }
}
