package bitcamp.util;

public class Test {

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add(new String("aaa"));
    list.add(new String("bbb"));
    list.add(new String("ccc"));
    list.add(new String("ddd"));

    Node node = list.first; // 첫번째 노드
    System.out.println(node.value);

    node = node.next;
    System.out.println(node.value);

    node = node.next;
    System.out.println(node.value);

    node = node.next;
    System.out.println(node.value);
  }
}
