package bitcamp.util;

import java.util.Arrays;

public class LinkedList<E> extends AbstractList<E> {

  private Node<E> first;
  private Node<E> last;

  public void add(E value) {
    Node<E> node = new Node<>();
    node.value = value;

    if (last == null) {
      // 노드 객체가 없을 때,
      first = last = node;
    } else {
      // 기존에 노드 객체가 있을 때,
      // 마지막 노드의 다음 노드로 새로 만든 노드를 가리키게 한다.
      last.next = node;
      last = node;
    }
    size++;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];
    int index = 0;
    Node<E> node = first;
    while (node != null) {
      arr[index++] = node.value;
      node = node.next;
    }
    return arr;
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    int cursor = 0;
    Node<E> node = first;
    while (++cursor <= index) {
      node = node.next;
    }

    return node.value;
  }

  public E set(int index, E value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    int cursor = 0;
    Node<E> node = first;
    while (++cursor <= index) {
      node = node.next;
    }

    E old = node.value;
    node.value = value;
    return old;
  }

  public void add(int index, E value) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    Node<E> node = new Node<>();
    node.value = value;

    if (first == null) {
      first = last = node;

    } else if (index == 0) {
      node.next = first;
      first = node;

    } else if (index == size) {
      last.next = node;
      last = node;

    } else {
      int cursor = 0;
      Node<E> currNode = first;
      while (++cursor < index) {
        currNode = currNode.next;
      }
      node.next = currNode.next;
      currNode.next = node;
    }
    size++;
  }

  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("무효한 인덱스입니다.");
    }

    Node<E> deleted = null;

    if (size == 1) {
      deleted = first; // 삭제할 노드 보관
      first = last = null;

    } else if (index == 0) {
      deleted = first; // 삭제할 노드 보관
      first = first.next;

    } else {
      int cursor = 0;
      Node<E> currNode = first;
      while (++cursor < index) {
        currNode = currNode.next;
      }
      deleted = currNode.next; // 삭제할 노드 보관
      currNode.next = currNode.next.next;

      if (index == (size - 1)) {
        last = currNode;
      }
    }

    size--;

    E old = deleted.value;
    deleted.value = null; // 가비지가 되기 전에 다른 객체를 참조하던 것을 제거한다.
    deleted.next = null; // 가비지가 되기 전에 다른 객체를 참조하던 것을 제거한다.
    return old;
  }

  public boolean remove(E value) {
    Node prevNode = null;
    Node node = first;

    while (node != null) {
      if (node.value.equals(value)) {
        break;
      }
      prevNode = node;
      node = node.next;
    }

    if (node == null) {
      return false;
    }

    if (node == first) {
      first = first.next;
      if (first == null) {
        last = null;
      }

    } else {
      prevNode.next = node.next;
    }

    size--;
    return true;
  }

  public E[] toArray(final E[] arr) {
    E[] values = arr;
    if (values.length < size) {
      values = Arrays.copyOf(arr, size);
    }

    int i = 0;
    Node<E> node = first;

    while (node != null) {
      values[i++] = node.value;
      node = node.next;
    }

    return values;
  }

//  // 1) 패키지 멤버 클래스로 Iterator 구현하기
//  @Override
//  public Iterator<E> iterator() {
//    return new LinkedListIterator<>(this);
//  }

//  // 2) 스태틱 중첩 클래스로 Iterator 구현하기
//  @Override
//  public Iterator<E> iterator() {
//    return new IteratorImpl<>(this);
//  }
//
//  private static class IteratorImpl<E> implements Iterator<E> {
//
//    LinkedList<E> list;
//    int cursor;
//
//    public IteratorImpl(LinkedList<E> list) {
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

//  // 3) 논스태틱 중첩 클래스로 Iterator 구현하기
//  @Override
//  public Iterator<E> iterator() {
//    return new IteratorImpl<>();
//  }
//
//  private class IteratorImpl<E> implements Iterator<E> {
//
//    Node<E> cursor = (Node<E>) LinkedList.this.first;
//
//    @Override
//    public boolean hasNext() {
//      return cursor != null;
//    }
//
//    @Override
//    public E next() {
//      E value = cursor.value;
//      cursor = cursor.next;
//      return value;
//    }
//  }

//  // 4) 로컬 클래스로 Iterator 구현하기
//  @Override
//  public Iterator<E> iterator() {
//
//    class IteratorImpl<E> implements Iterator<E> {
//
//      Node<E> cursor = (Node<E>) LinkedList.this.first;
//
//      @Override
//      public boolean hasNext() {
//        return cursor != null;
//      }
//
//      @Override
//      public E next() {
//        E value = cursor.value;
//        cursor = cursor.next;
//        return value;
//      }
//    }
//
//    return new IteratorImpl<>();
//  }

//  // 5) 익명 클래스로 Iterator 구현하기
//  @Override
//  public Iterator<E> iterator() {
//
//    Iterator<E> obj = new Iterator<E>() {
//
//      Node<E> cursor = (Node<E>) LinkedList.this.first;
//
//      @Override
//      public boolean hasNext() {
//        return cursor != null;
//      }
//
//      @Override
//      public E next() {
//        E value = cursor.value;
//        cursor = cursor.next;
//        return value;
//      }
//    };
//
//    return obj;
//  }

  // 6) 익명 클래스로 Iterator 구현하기 - 더 간결하게 표현하기
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      Node<E> cursor = (Node<E>) LinkedList.this.first;

      @Override
      public boolean hasNext() {
        return cursor != null;
      }

      @Override
      public E next() {
        E value = cursor.value;
        cursor = cursor.next;
        return value;
      }
    };
  }

  private static class Node<E> {

    E value;
    Node<E> next;
  }


}
