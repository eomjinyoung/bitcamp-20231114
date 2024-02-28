package bitcamp.myapp.dao;

// 이 클래스의 목적
// - RuntimeException의 기능을 확장하는 것이 아니다.
// - 예외가 발생했을 때 클래스 이름으로 어느 작업에서 예외가 발생했는지 바로 알아챌수 있도록 하기 위함이다.
//
public class DaoException extends RuntimeException {

  public DaoException() {
  }

  public DaoException(String message) {
    super(message);
  }

  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }

  public DaoException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
