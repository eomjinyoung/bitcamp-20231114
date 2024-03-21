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
- 향후 확장성을 고려하여 AssignmentMenu, MemberMenu, MainMenu 클래스에도 인스턴스 필드와 인스턴스 메서드, 생성자를 적용
  - 그래서 실무에서는 대부분의 클래스가 인스턴스 필드와 인스턴스 메서드로 구성된다.
- 향후 확장성을 고려하여 Prompt 크래스에도 인스턴스 필드와 인스턴스 메서드를 적용
  - 생성자 도입: Scanner 사용할 입력 도구를 지정할 수 있게 한다.
  - Prompt에 inputInt(), inputFloat(), inputBoolean() 메서드 추가
    - 사용자 입력한 문자열을 int, float, boolean으로 변경해주는 일을 한다.
- 의존 객체 주입의 개념과 구현
  - 생성자를 통해 Prompt 객체를 XxxMenu에 주입
- 리팩토링
  - ANSI Escape Sequence 값을 별도의 클래스로 분리
  - 역할에 따라 클래스를 패키지로 나눠서 분류 


## 16. 인터페이스를 이용한 객체 사용 규칙 정의

- 인터페이스 문법으로 메뉴를 다루는 객체의 실행 규칙 정의
- 인터페이스에 정의한 대로 메뉴를 다루는 객체를 구현
- 인터페이스에 정의한 대로 메뉴를 다루는 객체를 실행


## 17. 인터페이스와 GoF의 Composite 패턴을 이용하여 메뉴를 구현하기

- GoF의 Composite 디자인 패턴 적용
  - 메뉴 간의 연결을 느슨하게 조정하기
  - 현황:
    - MainMenu와 나머지 Menu 객체들 사이에 coupling이 강결합되어 있다.
    - 메뉴 클래스를 추가하면 MainMenu 클래스를 또 변경해야 한다.
  - 목표:
    - 메뉴를 추가하더라도 기존 클래스를 변경하지 않을 방법이 필요하다.
  - 객체지향 설계 원칙 및 OOP 개념:
    - SOLID의 'OCP(Open/Closed Priciple)' 준수
    - GRASP의 'Low Coupling' 책임 할당 원칙 준수

## 18. 인스턴스 목록 제어 기능을 별도의 클래스로 캡슐화: 재사용성 높임

- 핸들러 객체에서 수행하는 배열 다루는 일을 실제 배열을 가지고 있는 Repository 객체로 이관하기
  - 현황:
    - Repository 클래스의 배열 레퍼런스를 Handler에서 다루고 있다.
  - 목표:
    - 인스턴스를 다루는 기능을 다른 프로젝트에서도 재사용할 수 있게 만들고 싶다.
  - 객체지향 설계 원칙 및 OOP 개념:
    - GRASP의 Information Expert 책임 할당 원칙 준수
    - GRASP의 High Cohesion 책임 할당 원칙 준수
    - OOP의 Encapsulation(캡슐화)
  - 효과: 
    - 배열을 다루는 코드를 별도의 객체로 분리하면 코드 재사용이 쉬워진다.
    - 객체의 역할을 전문화함으로써 코드를 관리하기가 쉬워진다. 
    - 목록을 다루는 방식을 감추고 대신 외부에 공개된 도구(메서드)를 통해 목록을 사용하도록 유도하면,
      목록을 다루는 방식을 변경하더라도 외부에 영향을 주지 않는다.
      즉 기능을 변경하더라도 다른 클래스에 미치는 영향을 최소화시킬 수 있다.

## 19. 다형성을 이용하여 범용으로 사용할 수 있는 Repository 클래스 만들기

- 목록 관리 범용 클래스 ObjectRepository 정의
  - 다형성의 polymorphic variable 문법 활용
- 기존의 Repository 클래스를 ObjectRepository로 대체

## 20. 제네릭을 사용하여 타입을 파라미터로 다루기

- ObjectRepository에 제네릭 적용하기
  - 범용 클래스를 특정 타입을 다루는 클래스로 제한할 수 있다.
  - 파라미터 값에 대해 특정 타입으로 한정할 수 있다.
  - 반환값의 타입을 형변환 할 필요가 없다.
- T[] toArray(T[]) 메서드 추가하기
  - System.arraycopy() 메서드 사용하기
  - Arrays.copyOf() 메서드 사용하기

## 21. 자바 Collection API 사용하기 - ArrayList 적용

- 목록을 다루는 기존 Repository 클래스를 자바 컬렉션 API 로 교체

## 22. 접근 제어 modifier 및 셋터, 겟터 도입하기

- 인스턴스 필드에 직접 접근하는 것을 막는 방법: private
- 인스턴스 필드에 값을 저장하고 꺼내는 방법: setter/getter

## 23. 상속(generalization)과 추상 클래스/추상 메서드, 접근 제어 활용하기

- MenuItem과 MenuGroup의 공통 코드를 추출하여 수퍼 클래스를 정의하기
- MenuHandler 구현체의 공통 분모를 추출하여 수퍼 클래스 정의하기
- 수퍼 클래스를 추상 클래스로 정의하여 직접 사용을 막기
- 추상 메서드를 선언하여 서브 클래스에게 구현을 강요하기
- 서브 클래스가 의존 객체를 사용할 수 있도록 접근 범위를 protected로 설정하기

## 24. Date 클래스를 사용하여 날짜 데이터를 다루기

- java.util.Date/java.sql.Date 클래스를 활용하기

## 25. 예외 처리하기

- 예외가 발생했을 때 시스템을 멈추기 않게 하는 방법
- try ~ catch ~ finally 사용법

## 26. LinkedList 자료구조 구현하기

- 목록 관리 범용 클래스 LinkedList 정의
  - LinkedList 구동원리 이해 및 구현
  - 중첩 클래스 활용: static 중첩 클래스 Node 정의
  - 제네릭 적용
  - List 구현체를 생성자를 통해 주입: DI(Dependency Injection) 적용
- List 객체 사용 규칙 정의
  - ArrayList, LinkedList에 List 인터페이스 적용
- AbstractList 추상 클래스 적용 
  - ArrayList, LinkedList에 AbstractList 추상 클래스 적용
- MenuGroup에 적용
- Handler에 적용

## 27. Stack, Queue 자료구조 구현하기

- Stack과 Queue의 구동원리 이해 및 구현
- Stack 적용
  - MenuGroup에 Stack을 이용하여 Breadcrumb 구현
- GoF의 "Factory Method" 디자인 패턴 적용
  - MenuGroup에 적용


## 28. Iterator 디자인 패턴을 활용하여 목록 조회 기능을 캡슐화하기

- GoF의 디자인 패턴 중 Iterator 패턴의 동작원리 이해 및 구현
- ArrayList, LinkedList, Stack, Queue에 적용
- 중접 클래스 문법을 이용하여 Iterator 구현하기
  - static/non-static nested 클래스 문법을 활용하는 방법
  - local/anonymous 클래스 문법을 활용하는 방법

## 29. 기존의 컬렉션 클래스 및 인터페이스를 자바 컬렉션 API로 교체하기

- java.util 패키지에서 제공하는 컬렉션 API로 교체

## 30. 리팩토링: App 클래스

- main() 메서드에 들어 있는 코드를 기능에 따라 묶어 여러 메서드로 분리
- 공유하는 변수는 인스턴스 필드로 전환


## 31. File I/O API를 이용하여 데이터를 바이너리 형식으로 입출력하기

- FileInputStream/FileOutputStream 사용법
- 바이너리 형식으로 데이터를 입출력하는 방법

## 32. 상속을 이용하여 primitive type과 String 출력 기능을 추가하기

- 상속을 이용하여 바이트 입출력 기능을 확장하기
  - DataInputStream = FileInputStream 클래스 + primitive type/String 값 읽기
  - DataOutputStream = FileOutputStream 클래스 + primitive type/String 값 쓰기

## 33. 입출력 성능을 높이기 위해 버퍼 기능 추가하기

- 기존의 클래스에 버퍼 기능을 추가한다.
  - BufferedDataInputStream = DataInputStream + 버퍼 기능
  - BufferedDataOutputStream = DataOutputStream + 버퍼 기능

## 34. 입출력 기능 확장에 상속 대신 Decorator 패턴을 적용하기

- 상속 vs Decorator 패턴(GoF)
  - 기존 코드를 손대지 않고 기능 확장하는 방법
  - 상속: 기능 확장 용이
  - Decorator: 기능 확장 및 기능 제거 용이
- BufferedDataInputStream 분해
  - BufferedInputStream, DataInputStream, FileInputStream
- BufferedDataOutputStream 분해
  - BufferedOutputStream, DataOutputStream, FileOutputStream
- Java I/O Stream API로 대체하기

## 35. 인스턴스를 통째로 입출력하기(객체 직렬화)

- ObjectInputStream/ObjectOutputStream 사용법
  - java.io.Serializable 인터페이스 사용법
  - transient modifier 사용법

## 36. 리팩토링 - 중복 코드 정리

- 데이터 읽고 쓰는 중복 코드 정리
- serialVersionUID 스태틱 필드 사용하기

## 37. character I/O stream API를 사용하여 CSV 텍스트 형식으로 입출력하기

- CSV 형식으로 데이터를 읽고 쓰는 법
- FileReader/FileWriter 사용법
- GRASP의 Information Expert 패턴 적용
  - toCsvString() 메서드 정의
  - createFromCsv() 메서드 정의
- GoF의 Factory Method 패턴 적용
  - createFromCsv() 메서드 정의
- Reflection API 사용
  - createFromCsv() 메서드 정보 알아내기 및 호출하기

## 38. JSON 형식으로 입출력하기

- JSON 형식으로 데이터를 읽고 쓰는 법
- Gson 라이브러리 사용법

## 39. 데이터의 등록, 조회, 수정, 삭제 기능을 캡슐화하기 : DAO 객체 도입

- XxxHandler에서 데이터를 조작하는 코드를 캡슐화하여 별도의 클래스로 분리
  - UI 처리 코드와 데이터 처리 코드를 분리
  - UI 처리 방식이 바뀌더라도 데이터 처리 코드는 재사용할 수 있다.
  - List나 Map처럼 특정 자료구조에 종속적인 코드를 작성할 필요가 없어진다.
- 데이터 식별 값 추가
  - 기존 방식은 인덱스 사용
    - 데이터를 삭제하면 인덱스가 변경된다.
    - 데이터 조회 시 일관성이 없다.
  - 개선 방식은 각 데이터에 고유의 식별 번호 부여
    - 데이터를 삭제하더라도 기존 데이터의 식별 번호는 그대로 유지된다.
    - 데이터 조회 시 일관성이 있다.
- 인터페이스로 DAO 객체 사용법을 정의
  - DAO 교체가 용이하다.

## 40. 네트워킹을 이용하여 데이터 공유하기 : Client/Server 아키텍처로 전환

- 네트워크 프로그래밍 방법
  - Client와 Server 개념
  - 프로토콜에 따라 애플리케이션 간에 데이터를 주고 받기
- Reflection API를 사용하는 방법
  - 서버의 DAO 메서드 호출을 자동화 하는 방법
- GoF의 "Proxy" 패턴 적용
  - 원격 객체와 동일한 인터페이스를 갖는 대행 객체(프록시) 구현하기

## 41. 공통 기능을 서브 프로젝트로 분리하기

- Gradle 빌드 도구에서 멀티 서브 프로젝트를 다루는 방법
- 클라이언트와 서버에서 공통으로 사용하는 코드를 별도의 프로젝트로 분리
  - app-common 프로젝트 생성
- 원격 서비스 객체(DAO)의 Stub을 별도의 프로젝트로 분리
  - app-api 프로젝트 생성


## 42. DAO 프록시 객체(스텁 객체)를 자동 생성하기

- java.lang.reflect.Proxy 클래스 사용법
- Reflection API를 사용하여 메서드 정보를 추출하기
- GoF의 Factory Method 패턴 활용 

## 43. 여러 클라이언트의 요청을 순차적으로 처리하기: Stateless 방식

- Stateless 방식으로 통신하는 방법
  - Connection-Oriented vs Connectionless
  - Stateful vs Stateless
- Stateless 방식의 구동 원리와 Stateful 방식 대비 이점
- Stateless 방식의 문제점

## 44. 여러 클라이언트 요청을 동시에 처리하기: Thread 적용

- 멀티태스킹의 메커니즘 이해
  - 프로세스 스케쥴링: Round Robin 방식, Priority + Aging 방식
  - 컨텍스트 스위칭 개념
  - 프로세스 복제(fork)방식과 스레드 방식 비교
  - 임계영역(Critical Region, Critical Section): 세마포어(Semaphore)와 뮤텍스(Mutex)
- 스레드의 구동원리와 사용법
  - 스레드의 라이프사이클 이해
  - Thread 클래스와 Runnable 인터페이스 사용법

## 45. 스레드 재사용하기 : 스레드풀(thread pool) 구현

- Pooling 기법을 활용하여 스레드 객체를 관리하는 방법
- 스레드를 재사용 하는 방법
- GoF의 FlyWeight 디자인 패턴(풀링 기법)을 적용하여 스레드풀을 구현하는 방법

## 46. 스레드 재사용하기 : 자바에서 제공하는 스레드풀(thread pool) 사용

- Excutors/ExcutorService 사용법

## 47. DBMS 도입하기

- DBMS 설치 및 사용법
- SQL 및 JDBC Driver 개념과 사용법
- 테이블 정의 및 예제 데이터 입력 SQL 준비
- DBMS와 연동하여 작업하는 DAO 구현

## 48. SQL 삽입 공격 차단하기

- SQL 삽입 공격의 원리 이해
- PreparedStatement 사용법

## 49. Application Server 아키텍처로 전환하기

- 애플리케이션 서버 아키텍처의 특징과 구현
- Executor를 이용하여 스레드를 풀링하기

## 50. 여러 스레드가 DB 커넥션을 공유할 때의 문제점과 해결책 I

- 여러 스레드에서 DB 커넥션 객체를 공유할 때의 문제점 이해
- SQL 실행할 때 마다 Connection 생성하기
  - 이점: 다른 스레드의 commit/rollback 작업에 영향을 받지 않는다.
  - 단점: 여러 개의 데이터 변경(insert,update,delete) 작업을 하나의 트랜잭션으로 묶을 수 없다.


## 51. 여러 스레드가 DB 커넥션을 공유할 때의 문제점과 해결책 II

- 스레드 당 한 개의 DB 커넥션 사용하기
  - 다른 스레드의 commit/rollback 작업에 영향을 받지 않는다.
  - 트랜잭션을 사용할 수 있다.
- ThreadLocal을 사용하여 스레드 별로 Connection 객체를 유지시킨다.

## 52. 트랜잭션을 제어하는 객체: 비즈니스 로직을 수행하는 객체

- DAO에서 트랜잭션을 제어할 때 문제점
- 비즈니스 로직을 수행하는 객체에서 트랜잭션을 제어
  - 예: XxxHandler 


## 53. DB 커넥션 풀을 이용한 Connection 재사용하기

- 스레드 당 한 개의 DB 커넥션을 유지할 때 문제점
  - DB 커넥션의 낭비가 심하다.
  - DB 커넥션을 효율적으로 사용하지 못한다.
- 풀링 기법을 이용하여 DB 커넥션을 재사용하는 방법

## 54. 트랜잭션 제어 기능을 분리하기

- 트랜잭션 제어 기능을 별도의 클래스로 캡슐화하기
- 컨넥션 객체 사용 후 커넥션풀에 자동 반납하는 방법
  - Proxy 패턴을 이용하여 Connection 객체의 close() 기능 변경

## 55. 외부키(Foreign Key) 사용하기

- 자동 생성된 PK 를 알아내는 방법
- 외부키를 설정하고 사용하는 방법
- 조인을 이용하여 외부 테이블의 값을 가져오는 방법

## 56. 로그인/로그아웃 적용하기

- 로그인/로그아웃 구현하는 방법
- HttpSession 객체를 사용하는 방법
- 로그인 정보를 가지고 관련 데이터를 다루는 방법
  - 게시글 입력/변경/삭제할 때 로그인 정보 사용

## 57. 웹 애플리케이션 서버 구조로 전환하기 - 웹 기술 도입

- 임베디드 톰캣 서버를 이용하여 웹 애플리케이션 서버를 구축하는 방법
- 웹브라우저를 이용하여 클라이언트를 구축하는 방법
- 웹 기술을 도입하여 애플리케이션을 만드는 방법
- 세션을 이용해 로그인을 다루는 방법

## 58. 리스너 및 웹 애플리케이션 저장소 활용하기

- ServletContextListener 활용법
  - 웹애플리케이션을 시작하거나 종료할 때 작업을 수행시키는 방법
  - 예) 모든 서블릿이 공유하는 자원을 준비하기에 적절한 위치다.
- ServletContext 활용법
  - 웹애플리케이션당 1개가 생성되는 객체 저장소
  - 웹애플리케이션에서 공유할 객체를 보관하기에 적절하다.
  - 예) DB 커넥션, DAO, 트랜잭션 관리자 등

## 59. GET/POST 요청을 구분하기

- HttpServlet의 doGet(), doPost를 이용하여 GET 요청과 POST 요청을 구분하여 처리하기
- 로그인, 게시글 등록 및 변경, 회원 등록 및 변경, 과제 등록 및 변경에 적용

## 60. refresh/redirect 다루기

- 로그인 후 refresh 하기
- 로그아웃 후 redirect 하기
- 데이터 등록/변경/삭제 후 목록 페이지로 이동할 때 redirect 하기

## 61. forward/include 다루기

- 상단 메뉴 및 하단 정보 출력에 include 사용하기
- 오류 메시지 출력에 forward 사용하기

## 62. 파일 업로드 다루기 - multipart/form-data POST 요청 파라미터 인코딩

- Servlet API를 이용하여 multipart/form-data 파라미터를 다루는 방법
- 회원 사진 추가
  - DDL 변경

## 63. 쿠키 활용하기

- 쿠키를 이용하여 로그인 할 때 입력한 이메일을 보관하기

## 64. 필터 활용하기

- 필터를 활용하는 방법

## 65. JSP를 이용하여 MVC 모델2 구조로 변경하기

- MVC 모델1/모델2 특징 이해
- JSP 구동 원리 이해 및 사용법
- 서블릿과 JSP의 역할 및 구동 원리

## 66. EL 및 JSTL 활용하기

- EL 사용법
- JSTL 사용법

## 67. Front Controller 디자인 패턴 도입하기

- Front Controller 디자인 패턴의 효과 및 적용 방법
- 프론트 컨트롤러와 페이지 컨트롤러의 역할 이해

## 68. 페이지 컨트롤러를 POJO로 전환하기

- 페이지 컨트롤러를 POJO 클래스로 변경
  POJO? Plain Old Java Object (그냥 일반 자바 문법으로 만든 클래스)
- 의존 객체 주입하기

## 69. 애노테이션으로 요청 핸들러 지정하기

- 인터페이스 대신 애노테이션을 사용하여 요청 핸들러를 지정하는 방법
- 특정 애노테이션이 붙은 메서드를 찾아 호출하는 방법

## 70. CRUD 페이지 컨트롤러를 한 개의 페이지 컨트롤러로 합치기

- 한 개의 페이지 컨트롤러에 CRUD 요청 핸들러를 합치는 방법
- 애노테이션을 이용하여 요청 URL에 따라 핸들러를 구분하는 방법

## 71. 요청 핸들러의 파라미터를 자동으로 인식하기

- 요청 핸들러에 선언된 파라미터에 따라 값을 전달하는 방법

## 72. 페이지 컨트롤러를 자동 생성하기

- 애노테이션과 리플렉션 API를 사용하여 페이지 컨트롤러를 자동 생성하는 방법

## 73. IoC 컨테이너 만들기

- IoC 컨테이너의 구동 원리 이해와 구현하기
- 페이지 컨트롤러 및 의존 객체를 IoC 컨테이너로 관리하기

## 74. Spring IoC 컨테이너 도입하기

- Spring IoC 컨테이너를 이용하여 객체를 자동 생성하는 방법
- 의존 객체를 자동으로 주입하는 방법
- Spring IoC 컨테이너에 들어 있는 객체를 꺼내 사용하는 방법

## 75. Spring WebMVC 프레임워크 도입하기

- Spring WebMVC 프레임워크 사용법
  - CharacterEncodingFilter 사용법
  - ContextLoaderListener 사용법
  - DispatcherServlet 사용법

## 76. Spring WebMVC를 Java Config로 설정하기

- Java Config를 이용하여 Spring WebMVC를 설정하는 방법
- Log4j2 적용하는 방법
- @GetMapping, @PostMapping, Model, MultipartFile 적용하는 방법
- @ControllerAdvice, @InitBinder 사용하여 파라미터의 PropertyEditor를 등록하는 방법
- 예외가 발생했을 때 출력될 오류 페이지를 설정하는 방법 : @ExceptionHandler

## 77. Spring WebMVC의 기본 ViewResolver를 InternalResourceViewResolver로 교체하기

- InternalResourceViewResolver를 설정하고 다루는 방법

## 78. Mybatis SQL-mapper 프레임워크 사용하기

- mybatis 프레임워크의 구동 원리 및 사용법
- Spring Framework에 적용하는 방법
- Mybatis의 Log4j 활성화 하기

## 79. DAO 구현체 자동 생성하기

- Mybatis의 Spring 연동 플러그인을 사용하여 DAO를 자동 생성하는 방법


## 60. 첨부파일을 네이버 클라우드의 스토리지 서비스에 저장하기

- 네이버 스토리지 서비스 사용법
- 첨부파일을 스토리지 서비스에 업로드하는 방법

## 61. 네이버 클라우드의 Image Optimizer를 이용하여 썸네일 이미지 다루기

- 네이버 클라우드의 Image Optimizer 사용법







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