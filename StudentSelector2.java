import java.util.ArrayList;
import java.util.Arrays;

public class StudentSelector2 {
  public static void main(String[] args) throws Exception {
    String[] names = {
      "권채린", "김선종", "김성모", "김승철", "김유진", "김정원", "김준희", "김현준", "박광현", "박세진",
      "박준수", "석민준", "손창우", "심현우", "안혜령", "원준연", "유성모", "유순선", "이관모", "이현우",
      "장성찬", "정희준", "조용훈", "최성원"
    };

    ArrayList<String> nameList = new ArrayList<>(Arrays.asList(names));

    while (nameList.size() > 0) {
      System.out.println(nameList.remove((int)(Math.random() * nameList.size())));
      Thread.sleep(500);
    }
  }
}
