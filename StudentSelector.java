public class StudentSelector {
  public static void main(String[] args) throws Exception {
    String[] names = {
        "권채린", "김선종", "김성모", "김승철", "김유진", "김정원", "김준희", "김현준", "박광현", "박세진",
        "박준수", "석민준", "손창우", "심현우", "안혜령", "원준연", "유성모", "유순선", "이관모", "이현우",
        "장성찬", "정희준", "조용훈", "최성원"
    };

    int count = (int) (Math.random() * 100 + 1);
    for (int i = 0; i < count; i++) {
      Math.random();
      System.out.print(".");
      Thread.sleep(50);
    }
    System.out.println();

    int selectedIndex = (int)(Math.random() * names.length);
    System.out.println("축 당첨!");
    Thread.sleep(3000);
    System.out.println(names[selectedIndex]);


  }
}
