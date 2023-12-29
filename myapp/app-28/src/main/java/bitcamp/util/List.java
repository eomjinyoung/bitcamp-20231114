package bitcamp.util;

public interface List<E> {

  void add(E object);

  E remove(int index);

  boolean remove(E value);

  Object[] toArray();

  E[] toArray(E[] arr);

  E get(int index);

  E set(int index, E object);

  int size();

  Iterator<E> iterator();
}
