package bitcamp.util;

public class Queue<E> extends LinkedList<E> {

  public boolean offer(E value) {
    this.add(value);
    return true;
  }

  public E poll() {
    if (this.size == 0) {
      return null;
    }
    return this.remove(0);
  }

  public E peek() {
    if (this.size == 0) {
      return null;
    }
    return this.get(0);
  }
}
