package algorithm.test;

public class Test1 {

  public static void main(String[] args) throws Exception {

    String str1 = "\n" + 
        "    *\n" + 
        "   ***\n" + 
        "  *****\n" + 
        " *******\n" + 
        "*********\n" + 
        "    *\n" + 
        "    *\n" +
        "  *****\n";

    String str2 = "\n" + 
        "    *\n" + 
        "   &**\n" + 
        "  ***$*\n" + 
        " **#****\n" + 
        "****@**#*\n" + 
        "    *\n" + 
        "    *\n" +
        "  *****\n";

    String str3 = "\n" + 
        "    #\n" + 
        "   *@*\n" + 
        "  *$***\n" + 
        " *****$*\n" + 
        "*@**#****\n" + 
        "    *\n" + 
        "    *\n" +
        "  *****\n";

    String[] arr = {str1, str2, str3};

    System.out.println(arr[0]);

    int i = 1;
    while (true) {
      if (i == arr.length) {
        i = 0;
      }
      System.out.println("\u001b[10A" + arr[i++]);
      Thread.sleep(1000);
    }
  }



}
