package bitcamp.util;

public interface Pooling<E> {

  E get();

  void revert(E e);
}
