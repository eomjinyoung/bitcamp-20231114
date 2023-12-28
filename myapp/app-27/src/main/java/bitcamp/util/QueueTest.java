package bitcamp.util;

public class QueueTest {

  public static void main(String[] args) {
    Queue<String> queue = new Queue<>();
    queue.offer("aaa");
    queue.offer("bbb");
    queue.offer("ccc");
    queue.offer("ddd");

    while (queue.size() > 0) {
      System.out.println(queue.poll());
    }
  }
}
