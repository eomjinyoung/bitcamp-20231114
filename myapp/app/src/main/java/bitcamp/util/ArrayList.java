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
}
