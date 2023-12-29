package bitcamp.util;

import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {

  private Object[] objects = new Object[3];

  public void add(E object) {
    if (this.size == this.objects.length) {
      int oldSize = this.objects.length;
      int newSize = oldSize + (oldSize >> 1);
      this.objects = Arrays.copyOf(this.objects, newSize);
    }

    this.objects[this.size++] = object;
  }

  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }

    Object deleted = this.objects[index];

    System.arraycopy(this.objects, index + 1, this.objects, index, this.size - (index + 1));

    this.objects[--this.size] = null;

    return (E) deleted;
  }

  public boolean remove(E value) {
    for (int i = 0; i < this.size; i++) {
      if (this.objects[i].equals(value)) {
        this.remove(i);
        return true;
      }
    }
    return false;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.objects, this.size);
  }

  public E[] toArray(E[] arr) {
    if (arr.length >= this.size) {
      System.arraycopy(this.objects, 0, arr, 0, this.size);
      return arr;
    }
    return (E[]) Arrays.copyOf(this.objects, this.size, arr.getClass());
  }

  public E get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return (E) this.objects[index];
  }

  public E set(int index, E object) {
    if (index < 0 || index >= this.size) {
      return null;
    }

    Object old = this.objects[index];
    this.objects[index] = object;

    return (E) old;
  }

// 1) 외부에서 구현한 패키지 멤버 클래스를 사용한 경우
//  @Override
//  public Iterator<E> iterator() {
//    return new ArrayListIterator<>(this);
//  }

//  // 2) 스태틱 중첩 클래스를 사용한 경우
//  @Override
//  public Iterator<E> iterator() {
//    return new IteratorImpl<>(this);
//  }
//
//  private static class IteratorImpl<E> implements Iterator<E> {
//
//    ArrayList<E> list;
//    int cursor;
//
//    public IteratorImpl(ArrayList<E> list) {
//      this.list = list;
//    }
//
//    @Override
//    public boolean hasNext() {
//      return cursor >= 0 && cursor < list.size();
//    }
//
//    @Override
//    public E next() {
//      return list.get(cursor++);
//    }
//  }

  // 3) 논스태틱 중첩 클래스를 사용한 경우
  @Override
  public Iterator<E> iterator() {
    return new IteratorImpl<>();
  }

  private class IteratorImpl<E> implements Iterator<E> {

    int cursor;

    @Override
    public boolean hasNext() {
      return cursor >= 0 && cursor < ArrayList.this.size();
    }

    @Override
    public E next() {
      return (E) ArrayList.this.get(cursor++);
    }
  }
}
