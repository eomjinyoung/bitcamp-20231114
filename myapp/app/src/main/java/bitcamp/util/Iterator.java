package bitcamp.util;

// 데이터를 조회하는 일을 할 객체의 사용법을 정의한다.
//
public interface Iterator<E> {

  // 꺼낼 값이 있는지 묻는다.
  boolean hasNext();

  // 값을 꺼낸다.
  E next();
}
