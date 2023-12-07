# 실습 프로젝트

## 01. 자바 프로젝트 준비하기

- Gradle 빌드 도구를 이용하여 프로젝트 폴더를 구성하는 방법
  - `init` task 실행
- Gradle로 프로젝트를 빌드하고 실행하는 방법
  - `build`, `run` task 실행

## 02. 문자열 출력하기

- 자바에서 문자열 및 숫자를 출력하는 방법
- 줄바꿈을 다루는 방법
- 이스케이스 문자를 다루는 방법
- 포맷 문자열을 출력하는 방법

```
----------------------------------------------
[과제관리 시스템]

1. 과제
2. 게시글
3. 도움말
4. 종료
----------------------------------------------
```

## 03. ANSI 이스케이프 코드를 사용하여 출력 문자열 꾸미기

- "[과제관리시스템]"을 볼드체, 빨강색으로 출력하는 방법

## 04. 변수를 사용하여 데이터를 출력하기

- 변수를 사용하는 방법

## 05. 표준 입력 스트림 다루기

- 표준 입력 스트림(예:키보드)에서 값을 입력 받는 방법
- 조건문, 반복문을 사용하는 방법

```
----------------------------------------------
[과제관리 시스템]

1. 과제
2. 게시글
3. 도움말
4. 종료
> 1
과제입니다.
> 2
게시글입니다.
> 3
도움말입니다.
> 4
종료합니다. <=== 프로그램 종료!
> menu
[과제관리 시스템]

1. 과제
2. 게시글
3. 도움말
4. 종료
----------------------------------------------
```

## 06. 배열을 활용하여 메뉴 목록을 다루기

- 배열 활용
- for 반복문 활용
- 작업할 내용
  - 메뉴를 배열에 저장한다.
  - 메뉴 목록을 출력할 때 배열에서 꺼내 출력한다.

## 07. 기능 단위로 명령문 묶기 : 메서드 사용법

- 메서드를 활용하여 중복 코드를 제거하는 방법
- static 메서드를 정의하고 호출하는 방법
- static 필드를 정의하고 사용하는 방법

## 08. 기능 단위로 명령문 묶기 : 메서드 사용법 II

- 조건문 사용법 익히기
- 메서드 사용법 익히기
- 작업할 내용
  - 각 메뉴에 대해 서브 메뉴를 출력하라.

```
----------------------------------------------
[과제관리 시스템]

1. 과제
2. 게시글
3. 도움말
4. 종료
메인> 1
[과제]
1. 등록
2. 조회
3. 변경
4. 삭제
0. 이전
메인/과제> 1
과제 등록입니다.
메인/과제> 2
과제 조회입니다.
메인/과제> 3
과제 변경입니다.
메인/과제> 4
과제 삭제입니다.
메인/과제> menu
[과제]
1. 등록
2. 조회
3. 변경
4. 삭제
0. 이전
메인/과제> 0
메인>
----------------------------------------------
```

## 09. 메서드를 역할에 따라 분류하기 : 클래스 사용법

- 클래스 사용법
- 쌍방향 관계(bidirectional association)의 문제점과 해결 방법
- GRASP 패턴: Low Coupling, High Cohesion, Information Expert


## 10. 과제 및 게시글의 데이터에 대해 CRUD(create, retrieve/read, update, delete)를 구현하기

- 과제 및 게시글 데이터의 입력, 조회, 변경, 삭제하는 기능 구현하는 방법
  - 과제 입출력 항목: 과제명, 내용, 제출 마감일
  - 게시글 입출력 항목: 제목, 내용, 작성자, 작성일


## 11. 사용자 정의 데이터 타입 만들기

- 클래스 문법을 이용해서 과제 및 게시글 데이터를 담을 타입을 정의하고 사용하는 방법


## 12. 배열을 이용하여 여러 개의 데이터를 다루기

- 배열을 이용하여 인스턴스 목록을 다루는 방법


## 13. 회원 메뉴를 추가하고 CRUD를 구현하기

- 메뉴 메뉴를 다음과 같이 변경
```
----------------------------------------------
[과제관리 시스템]

1. 과제
2. 게시글
3. 회원
4. 도움말
0. 종료
----------------------------------------------
```

- 회원 데이터의 입력, 조회, 변경, 삭제하는 기능 구현하는 방법
  - 회원 입출력 항목: 이메일, 이름, 암호, 가입일


## 14. 스태틱 필드의 한계 확인

- BoardMenu 클래스를 복제하여 '가입인사 게시판'을 추가
- 클래스 코드 복제의 문제점 확인

## 15. 인스턴스 필드와 인스턴스 메서드 활용

- BoardMenu 클래스에 인스턴스 필드 및 메서드 적용, 생성자 적용
- 향후 확장성을 고려하여 AssignmentMenu, MemberMenu 클래스도 인스턴스 필드와 인스턴스 메서드, 생성자를 적용
  - 그래서 실무에서는 대부분의 클래스가 인스턴스 필드와 인스턴스 메서드로 구성된다.
- 향후 확장성을 고려하여 Prompt 크래스에도 인스턴스 필드와 인스턴스 메서드를 적용
  - 생성자 도입: Scanner 사용할 입력 도구를 지정할 수 있게 한다.
  - Prompt에 inputInt(), inputFloat(), inputBoolean() 메서드 추가
    - 사용자 입력한 문자열을 int, float, boolean으로 변경해주는 일을 한다.
- 의존 객체 주입의 개념과 구현
  - 생성자를 통해 Prompt 객체를 XxxMenu에 주입






## 12. 생성자, 셋터, 겟터 도입하기

- 인스턴스 필드를 초기화시키는 방법: 생성자
- 인스턴스 필드에 직접 접근하는 것을 막는 방법: private
- 인스턴스 필드에 값을 저장하고 꺼내는 방법: setter/getter
- 스태틱 필드 및 생성자 활용
- 스태틱 상수 필드 활용 + GRASP 패턴의 Information Expert

## 13. 복사/붙여넣기를 이용한 CRUD 구현

- 게시글 CRUD 기능 추가
- Value Object, Handler 클래스 추가
- Prompt 클래스 리팩토링

## 14. 스태틱 필드의 한계 확인

- BoardHandler 클래스를 복제하여 독서록 게시판 추가
- 클래스 코드 복제의 문제점 확인

## 15. 인스턴스 필드와 인스턴스 메서드, 생성자와 의존 객체 주입

- BoardHandler 클래스에 인스턴스 필드 및 메서드 적용
- 향후 확장성을 고려하여 MemberHandler 크래스에도 인스턴스 필드와 인스턴스 메서드를 적용
  - 그래서 실무에서는 대부분의 클래스가 인스턴스 필드와 인스턴스 메서드로 구성된다.
- 향후 확장성을 고려하여 Prompt 크래스에도 인스턴스 필드와 인스턴스 메서드를 적용
  - 생성자 도입: Scanner 사용할 입력 도구를 지정할 수 있게 한다.
- 의존 객체 주입의 개념과 구현
  - 생성자를 통해 Prompt 객체를 Handler에 주입

## 16. GRASP 패턴: Information Expert 적용

- 메뉴 기능을 각 핸들러에게 위임
  - 기능을 수행하는데 필요한 정보를 가지고 있는 객체에 역할 부여
  - CRUD 메뉴 기능은 핸들러로 이전
- App 클래스는 메인 메뉴 제공

## 17. 인터페이스를 이용한 객체 사용 규칙 정의

- 인터페이스 문법으로 핸들러의 실행 규칙 정의
- 인터페이스에 정의한 대로 핸들러 구현
- 인터페이스에 정의한 대로 핸들러 실행

## 18. 인스턴스 목록 제어 기능을 별도의 클래스로 캡슐화: 재사용성 높임

- 핸들러에서 인스턴스 목록을 다루는 기능을 별도의 클래스로 분리
  - UI가 CLI에서 윈도우 또는 웹으로 바뀌더라도 인스턴스 목록 다루는 기능은 재사용 가능
- 배열 크기 자동 증가 기능 추가

## 19. 다형성을 이용하여 범용으로 사용할 수 있는 목록 클래스 만들기

- 목록 관리 범용 클래스 ArrayList 정의
  - 다형성의 polymorphic variable 문법 활용
- equals() 메서드와 오버라이딩 활용
  - Object 클래스와 상속
  - Member와 Board 클래스에 적용
- 오버로딩을 활용하여 생성자를 추가
  - Member와 Board 클래스 적용
- MemberHandler와 BoardHandler에 적용

## 20. LinkedList 자료구조 구현하기

- 목록 관리 범용 클래스 LinkedList 정의
  - LinkedList 구동원리 이해 및 구현
  - 중첩 클래스 활용
- MemberHandler와 BoardHandler에 적용

## 21. 인터페이스를 이용하여 List 사용 규칙 정의하기

- 목록 관리 객체의 사용 규칙을 인터페이스 정의
  - List 인터페이스 정의
  - ArrayList, LinkedList에 List 인터페이스 적용
- MemberHandler와 BoardHandler에 적용
  - List 구현체를 생성자를 통해 주입: DI(Dependency Injection) 적용

## 22. Stack, Queue 자료구조 구현하기

- Stack과 Queue의 구동원리 이해 및 구현
- Stack 적용
  - Prompt 클래스의 서브 클래스 MenuPrompt 정의
  - MenuPrompt에서 Stack을 이용하여 프롬프트 제목에 breadcrumb 기능을 적용
- Queue 적용
  - MenuPrompt 클래스에 메뉴 출력 기능을 추가
    - App, BoardHandler, MemberHandler 변경
  - MenuPrompt 클래스에 입력한 명령어의 history 기능을 추가

## 23. Composite, Command, Observer 디자인 패턴, 추상 클래스/메서드 활용하기

- Composite 패턴을 활용하여 메뉴 구현하기
  - BreadcrumbPrompt에 적용
  - Menu, MenuGroup 클래스 정의
- Observer 패턴을 활용하여 메뉴 명령 처리하기
  - ActionListener 인터페이스 정의
  - Menu와 리스너 객체 연결
- Command 패턴을 활용하여 메뉴 기능 구현하기
  - BoardHandler, MemberHandler에 적용
  - ActionListener 인터페이스 활용
  - BoardXxxListener, MemberXxxListener 클래스로 분해
- Generalization(상속) 수행
  - AbstractBoardListener 추상 클래스 정의
    - 추상 메서드 도입

## 24. 제네릭을 사용하여 타입을 파라미터로 다루기

- ArrayList, LinkedList, Stack, Queue에 제네릭 적용하기
- T[] toArray(T[]) 메서드 추가하기

## 25. Iterator 디자인 패턴을 활용하여 목록 조회 기능을 캡슐화하기

- GoF의 디자인 패턴 중 Iterator 패턴의 동작원리 이해 및 구현
- ArrayList, LinkedList, Stack, Queue에 적용
- 중접 클래스 문법을 이용하여 Iterator 구현하기
  - static/non-static nested 클래스 문법을 활용하는 방법
  - local/anonymous 클래스 문법을 활용하는 방법

## 26. 자바 Collection API 사용하기

- 목록을 다루는 기존 클래스를 자바 컬렉션 API 로 교체

## 27. File I/O API를 이용하여 데이터를 바이너리 형식으로 입출력하기

- FileInputStream/FileOutputStream 사용법
- 바이너리 형식으로 데이터를 입출력하는 방법

## 28. 상속을 이용하여 primitive type과 String 출력 기능을 추가하기

- 상속을 이용하여 바이트 입출력 기능을 확장하기
  - DataInputStream = FileInputStream 클래스 + primitive type/String 값 읽기
  - DataOutputStream = FileOutputStream 클래스 + primitive type/String 값 쓰기

## 29. 입출력 성능을 높이기 위해 버퍼 기능 추가하기

- 기존의 클래스에 버퍼 기능을 추가한다.
  - BufferedDataInputStream = DataInputStream + 버퍼 기능
  - BufferedDataOutputStream = DataOutputStream + 버퍼 기능

## 30. 입출력 기능 확장에 상속 대신 Decorator 패턴을 적용하기

- 상속 vs Decorator 패턴(GoF)
  - 기존 코드를 손대지 않고 기능 확장하는 방법
  - 상속: 기능 확장 용이
  - Decorator: 기능 확장 및 기능 제거 용이
- BufferedDataInputStream 분해
  - BufferedInputStream, DataInputStream, FileInputStream
- BufferedDataOutputStream 분해
  - BufferedOutputStream, DataOutputStream, FileOutputStream

## 31. Java Stream API 로 교체하기

- 입출력 관련 클래스를 자바 스트림 클래스로 교체
  - java.io.\* 패키지의 클래스 사용

## 32. 인스턴스를 통째로 입출력하기(객체 직렬화)

- ObjectInputStream/ObjectOutputStream 사용법
  - java.io.Serializable 인터페이스 사용법
  - transient modifier 사용법

## 33. character stream API를 사용하여 CSV 텍스트 형식으로 입출력하기

- CSV 형식으로 데이터를 읽고 쓰는 법
- FileReader/FileWriter 사용법

## 34. 리팩토링: Factory Method 패턴(GoF), Information Expert 패턴(GRASP)

- CSV 데이터 생성을 Board 클래스에 맡기기
  - Information Expert 패턴 적용
- CSV 데이터를 가지로 Board 클래스 생성하기
  - Factory Method 패턴 적용
  - Reflection API 사용법
    - Class, Method 사용법

## 35. JSON 형식으로 입출력하기

- JSON 형식으로 데이터를 읽고 쓰는 법
- Gson 라이브러리 사용법

## 36. 데이터의 등록, 조회, 수정, 삭제 기능을 캡슐화하기 : DAO 객체 도입

- XxxListener에서 데이터를 조작하는 코드를 캡슐화하여 별도의 클래스로 분리
- 인터페이스로 DAO 객체 사용법을 정의

## 37. 네트워킹을 이용하여 데이터 공유하기 : Client/Server 아키텍처로 전환

- 네트워크 프로그래밍 방법
  - Client와 Server 개념
  - 프로토콜에 따라 애플리케이션 간에 데이터를 주고 받기
  - GoF의 프록시 패턴의 원리 이해 및 적용
  - 분산 컴퓨팅의 개념과 주요 기술 이해

## 38. DAO 프록시 객체를 자동 생성하기

- java.lang.reflect.Proxy 클래스 사용법
- 프록시 객체의 구동원리 이해

## 39. Reflection API를 활용하여 DAO 메서드 호출을 자동화하기

- Reflection API를 사용하는 방법
- 서버의 DAO 메서드 호출을 자동화 하는 방법

## 40. 예외 처리하기

- 예외가 발생했을 때 시스템을 멈추기 않게 하는 방법
- try ~ catch ~ finally 사용법

## 41. 여러 클라이언트의 요청을 순차적으로 처리하기: Stateful 방식

- 클라이언트 요청을 순차적으로 처리하는 방법
- 구동 원리와 문제점

## 42. 여러 클라이언트의 요청을 순차적으로 처리하기: Stateless 방식

- Stateless 방식으로 통신하는 방법
- 구동 원리와 Stateful 방식 대비 이점
- Stateless 방식의 문제점

## 43. 여러 클라이언트 요청을 동시에 처리하기: Thread 적용

- 멀티태스킹의 메커니즘 이해
  - 프로세스 스케쥴링: Round Robin 방식, Priority + Aging 방식
  - 컨텍스트 스위칭 개념
  - 프로세스 복제(fork)방식과 스레드 방식 비교
  - 임계영역(Critical Region, Critical Section): 세마포어(Semaphore)와 뮤텍스(Mutex)
- 스레드의 구동원리와 사용법
  - 스레드의 라이프사이클 이해
  - Thread 클래스와 Runnable 인터페이스 사용법

## 44. 스레드 재사용하기 : 스레드풀(thread pool) 구현

- Pooling 기법을 활용하여 스레드를 재사용하는 방법
- GoF의 FlyWeight 디자인 패턴(풀링 기법)을 적용하여 스레드풀을 구현하는 방법

## 45. 스레드 재사용하기 : 자바에서 제공하는 스레드풀(thread pool) 사용

- Excutors/ExcutorService 사용법

## 46. DBMS 도입하기

- DBMS 설치 및 사용법
- SQL 및 JDBC Driver 개념과 사용법
- 회원 및 게시판 테이블 정의 및 예제 데이터 입력 SQL 준비
- DBMS와 연동하여 작업하는 DAO 구현

## 47. SQL 삽입 공격 차단하기

- SQL 삽입 공격의 원리 이해
- PreparedStatement 사용법
- 테이블에 암호를 저장하는 방법: SHA1() 함수 사용법

## 48. 외부키(Foreign Key) 사용하기

- 외부키를 설정하고 사용하는 법
- 조인을 이용하여 외부 테이블의 값을 가져오는 방법

## 49. 로그인 적용하기

- 로그인을 구현하는 방법
- 로그인 정보를 가지고 관련 데이터를 다루는 방법

## 50. Application Server 아키텍터로 전환하기

- 애플리케이션 서버 아키텍처의 특징과 구현
- Executor를 이용하여 스레드를 풀링하기

## 51. DB 커넥션 풀을 이용한 Connection 재사용하기

- 여러 스레드에서 DB 커넥션 객체를 공유할 때의 문제점 이해
- ThreadLocal을 이용하여 스레드 전용 객체를 보관하고 꺼내는 방법
- 풀링 기법을 이용하여 DB 커넥션을 재사용하는 방법

## 52. Mybatis SQL-mapper 프레임워크 사용하기

- mybatis 프레임워크의 구동 원리 및 사용법
- GoF의 Proxy 패턴을 이용하여 SqlSessionFactory 객체의 기능을 변경하는 방법

## 53. 리스너 실행에 Facade 패턴 적용하기

- GoF의 Facade 패턴의 동작원리 이해와 적용하는 방법
- ActionListener 실행에 Facade 객체를 사용

## 54. IoC 컨테이너 적용하기

- IoC 컨테이너의 구동 원리와 구현
- 리스너 객체를 IoC 컨테이너로 관리하기

## 55. 웹 애플리케이션 서버 구조로 전환하기 - 웹 기술 도입

- 웹 기술을 도입하여 애플리케이션 서버 / 클라이언트를 구축하는 방법
- Netty, Reactor 라이브러리를 사용하여 웹서버를 구축하는 방법
- 웹브라우저를 이용하여 클라이언트를 구축하는 방법
- 쿠키와 세션을 다루는 방법

## 56. 웹 애플리케이션 자바 표준 기술 JavaEE 도입

- JavaEE 기술 소개
- JavaEE 버전 별 하위 기술 버전
- JavaEE 구현 서버 및 Servlet 컨테이너 관계
- Servlet API 사용법

## 57. 파일 업로드 다루기 - multipart/form-data POST 요청 파라미터 인코딩

- apache.org의 라이브러리를 이용하여 multipart/form-data 파라미터를 다루는 방법
- 네이버클라우드 mysql DBMS 사용하기

## 58. 파일 업로드 다루기 II - multipart/form-data POST 요청 파라미터 인코딩

- Servlet API를 이용하여 multipart/form-data 파라미터를 다루는 방법

## 59. 서블릿 컨테이너 삽입하기

- 웹 애플리케이션에 서블릿 컨테이너를 포함시키는 방법

## 60. 첨부파일을 네이버 클라우드의 스토리지 서비스에 저장하기

- 네이버 스토리지 서비스 사용법
- 첨부파일을 스토리지 서비스에 업로드하는 방법

## 61. 네이버 클라우드의 Image Optimizer를 이용하여 썸네일 이미지 다루기

- 네이버 클라우드의 Image Optimizer 사용법

## 62. forward/include/refresh/redirect 다루기

- 오류 메시지 출력에 forward 및 refresh 사용하기
- 상단 메뉴 및 하단 정보 출력에 include 사용하기
- 등록 완료 후 목록 페이지로 이동할 때 redirect 사용하기

## 63. 쿠키와 ServletContext 보관소 활용하기

- 쿠키를 이용하여 로그인 할 때 입력한 이메일을 보관하기
- 서블릿들이 공통으로 사용하는 객체를 ServletContext 보관소에 보관하기

## 64. JSP를 이용하여 MVC 모델1 구조로 변경하기

- MVC 모델1 특징 이해
- JSP 구동 원리 이해 및 사용법
- EL 사용법
- JSTL 사용법

## 65. 서블릿을 결합하여 MVC 모델2 구조로 변경하기

- MVC 모델2 특징 이해
- 서블릿과 JSP의 역할 및 구동 원리

## 66. Front Controller 디자인 패턴 도입하기

- Front Controller 디자인 패턴의 효과 및 적용 방법
- 프론트 컨트롤러와 페이지 컨트롤러의 역할 이해

## 67. 페이지 컨트롤러를 POJO로 전환하기

- 페이지 컨트롤러를 POJO 클래스로 변경
  POJO? Plain Old Java Object (그냥 일반 자바 문법으로 만든 클래스)
- 의존 객체 주입하기

## 68. Spring IoC 컨테이너를 이용하여 페이지 컨트롤러를 관리하기

- Spring IoC 컨테이너를 이용하여 객체를 자동 생성하는 방법
- 의존 객체를 자동으로 주입하는 방법
- Spring IoC 컨테이너에 들어 있는 객체를 꺼내 사용하는 방법

## 69. Mybatis + Spring IoC 컨테이너 = DAO 구현체 자동 생성

- Mybatis의 Spring 연동 플러그인을 사용하여 DAO를 자동 생성하는 방법
- Mybatis의 Log4j 활성화 하기

## 70. Controller에서 비즈니스 로직 분리하기: 서비스 컴포넌트 도입

- Controller에서 비즈니스 로직을 분리하는 이유
- 서비스 컴포넌트의 역할 이해

## 71. 트랜잭션 제어 코드를 캡슐화하기: Spring의 TransactionTemplate 모방 구현

- 트랜잭션 제어 코드를 캡슐화하여 반복적인 코드 작업을 줄이는 방법
- Spring의 TransactionTemplate 클래스를 모방하여 내부 메커니즘을 이해하기

## 72. Spring의 TransactionTemplate 으로 교체하기

- 직접 만든 TransactionTemplate 대신 Spring에서 제공하는 클래스 사용하기

## 73. 애노테이션을 사용하여 트랜잭션 제어하기

- 프록시 패턴 기술을 사용하여 트랜잭션 코드를 삽입하기

## 74. Spring의 @Transactional 로 교체하기

- 직접 제작한 트랜잭션 프록시 객체 대신 Spring에서 제공하는 클래스 및 애노테이션 사용하기

## 75. 애노테이션으로 request handler 표시하기

- PageController 인터페이스를 사용하는 대신에 애노테이션으로 호출될 메서드를 표시하는 방법

## 76. CRUD 기능을 한 개의 페이지 컨트롤러로 합치기

- CRUD 관련 메서드를 한 개의 클래스로 합치는 방법
- 요청 별로 메서드를 구분하여 호출하는 방법

## 77. 요청 파라미터를 자동 주입하기

- 클라이언트가 보낸 요청 파라미터를 요청 핸들러에 자동으로 주입하는 방법

## 78. Spring WebMVC 프레임워크 도입하기

- Spring WebMVC 프레임워크 사용법
- DispatcherServlet을 등록하는 다양한 방법
- WebApplicationInitializer 동작 메커니즘 이해

## 79. Spring IoC 컨테이너를 Root Context와 Servlet Context로 분리하기

- ContextLoaderListener의 IoC 컨테이너 역할 이해
- DispatcherServlet의 IoC 컨테이너의 역할 이해
- 설정하는 방법

## 80. Spring WebMVC 활용하기

- Spring WebMVC 사용법

## 81. SpringBoot(2.7.x) 적용하기

- SpringBoot 사용법

## 82. 뷰 템플릿 기술을 Thymeleaf 로 교체하기

- Thymeleaf 사용법

## 83. Lombok 적용하기

- Lombok 사용법

## 84. SpringSecurity 적용하기

- SpringSecurity 사용법

## 85. AJAX 기술을 이용하여 Backend와 Frontend 분리하기 - XMLHttpRequest 활용

- XMLHttpRequest를 이용한 AJAX 활용법
- REST API를 구현하는 방법

## 86. Backend와 Frontend를 서로 다른 서버로 분리하기

- Node.js 와 express를 이용하여 프론트엔드 서버 구축하는 방법
- CORS를 다루는 방법
- CSRF를 다루는 방법

## 87. AJAX 기술 다루기 - fetch() 활용

- fetch() 를 사용하여 AJAX 요청을 처리하는 방법
- Promise 문법을 활용하는 방법

## 88. AJAX 기술 다루기 - jQuery 활용

- jQuery 를 사용하여 AJAX 요청을 처리하는 방법
- jQuery 를 이용하여 DOM 트리를 다루는 방법
- npm을 이용하여 외부 자바스크립트 라이브러리를 사용하는 방법

## 89. AJAX 기술 다루기 - axios 활용

- axios 를 사용하여 AJAX 요청을 처리하는 방법

## 90. JavaScript 템플릿 라이브러리 적용하기 - Handlebars 활용

- Handlerbars 를 사용하여 HTML 템플릿에 데이터를 삽입하여 HTML 코드를 생성하는 방법

## 91. JWT 사용자 인증 적용하기

- JWT 를 활용하여 사용자를 인증하는 방법

## 92. WYSIWYG 자바스클립트 편집기 사용하기 - summernote 활용

- summernote 활용법
- 게시판에 적용하기

## 93. SNS 로그인 적용하기 - Facebook 로그인

- 페이스북 Login API 사용법
- 소셜 로그인 구동원리 이해

## 94. OpenFeign REST Client 사용하기 - RestTemplate 대체

- Spring Cloud OpenFeign 설정과 사용법