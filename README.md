# 네이버 데브옵스 개발자 교육 과정(5기)

* 기간: 2023-11-14 ~ 2024-5-22
* 네이버 웨일온: 전용회의실번호(210 866 3081)

## 강의

### 1일(2023-11-14,화)

- 강의 내용
  - 교육센터 오리엔테이션(2)
  - DevOps 교육 과정 소개(1)
  - 소프트웨어 유형별 특징(1)
  - 교육생 '자기 소개'(2)
  - 웹 애플리케이션 아키텍처와 구동 원리 소개(2)
- 학습 점검 목록
  - 소프트웨어 유형 별 특징을 설명할 수 있는가? []
  - 웹 애플리케이션 아키텍처와 구동 원리를 간단히 설명할 수 있는가? []

### 2일(2023-11-15,수)

- 강의 내용
  - CPU와 기계어(1)
  - OS와 실행파일 포맷(1)
    - Windows OS(PE), macOS(Mach-O), Linux(ELF)
  - 애플리케이션 만들기: 기계어, 어셈블리 언어, C 언어와 컴파일러(1)
  - 자바 애플리케이션 만들기(1.5)
    - 바이트코드와 JVM
    - JVM, JRE, JDK의 관계
    - JavaSE, JavaEE, JavaME의 관계
  - JDK 설치 및 설정(1)
    - PATH, JAVA_HOME 환경 변수 설정(Windows & macOS)
  - VSCode 설치 및 설정(0.5)
    - 플러그인, codelens, inlay hints 등
  - 자바 애플리케이션 컴파일 및 실행 테스트(1)
  - github.com 계정 생성 및 저장소 생성(1)
- 학습 점검 목록
  - CPU와 기계어(instruction set)의 관계를 설명할 수 있는가? []
  - 동일한 CPU에 OS가 다를 때 프로그램이 호환되지 않는 이유를 설명할 수 있는가? []
  - 동일한 OS에 CPU 아키텍처가 다를 때 프로그램이 호환되지 않는 이유를 설명할 수 있는가? []
  - 어셈블리와 C 언어를 사용해 애플리케이션 만드는 과정을 설명할 수 있는가? []
  - Low-level 언어와 High-level 언어의 의미를 설명할 수 있는가? []
  - 자바 애플리케이션을 만드는 방법을 설명할 수 있는가? []
  - REPL과 jShell 을 설명할 수 있는가? []
  - JVM, JRE, JDK, JavaSE, JavaEE, JavaME를 설명할 수 있는가? []
  - JDK를 설치하고 설정할 수 있는가? []
  - JAVA_HOME, PATH 환경 변수를 설정하는 이유를 알고 있는가? []
  - VSCode를 설치하고 자바 애플리케이션 작성에 필요한 환경을 설정할 수 있는가? []
  - github.com 에 저장소를 생성할 수 있는가? []

### 3일(2023-11-16,목)

- 강의 내용
  - 소프트웨어 형상관리 개념 및 주요 도구 소개(2)
    - 버전 관리 시스템의 유형 별 특징
    - CVS, SVN, Git 버전 관리 도구 소개
  - git 클라이언트 설치 및 설정, 사용법(6)
    - Windows에서 설치
    - 저장소 디렉토리 구조: .git/ 폴더와 작업 폴더(working directory)
    - config, status, clone, add, commit, push, pull, log, checkout 등 깃 명령 사용법
- 학습 점검 목록
  - 버전 관리 시스템의 유형 별 특징을 설명할 수 있는가? []
  - git client를 사용하여 git server로부터 저장소를 복제할 수 있는가? []
  - 깃 저장소의 .git 폴더와 working directory의 관계를 설명할 수 있는가? []
  - 깃 저장소의 working directory에 있는 파일의 변경 상태를 알아내는 명령을 실행할 수 있는가? []
  - 깃 저장소의 working directory에 있는 파일의 변경 상태 세가지를 설명할 수 있는가? []
  - 깃 저장소의 staging area에 대해 설명할 수 있는가? []
  - 깃 명령 add, commit, push, pull에 대해 설명할 수 있는가? []
  - 깃 저장소에 커밋된 기록을 조회할 수 있는가? []
  - 깃 저장소에서 다른 커밋 버전의 파일들을 working directory에 꺼낼 수 있는가? []
  - 로컬 깃 사용자의 이름과 이메일 정보를 조회하고 설정할 수 있는가? []
  - github.com에서 원격 깃 저장소에 접근할 때 사용할 토큰을 발급할 수 있는가? []

### 4일(2023-11-17,금)

- 강의 내용
  - git 클라이언트 설치 및 설정, 사용법(3)(계속)
    - 소프트웨어 형상 관리 이해
    - push 할 때 변경 사항이 충돌하는 것을 처리하는 방법
    - .gitignore 파일의 용도 및 사용법
  - 텍스트 파일과 바이너리 파일의 구분
  - Java 컴파일러 및 JVM 버전과 바이트코드의 관계
  - 애플리케이션 개발 및 실행 방식 비교: 인터프리트, 컴파일, 하이브리드(4)
    - 인터프리트 방식 소개 및 실습: nodejs 설치
    - 컴파일 방식 소개 및 실습: Windows gcc 설치
    - 하이브리드 방식 소개 및 실습: Java 컴파일 및 실행
    - JIT, AOT 컴파일 방식 비교
    - PlayStore에서 앱을 다운로드하여 설치하는 과정: AOP 실행 시점
  - 빌드과 빌드 도구(1)
    - 빌드의 개념 
    - 빌드 도구 소개 및 특징
    - 빌드 도구 별 빌드 스크립트 파일
  - 소프트웨어와 라이브러리 파일
- 학습 점검 목록
  - 소프트웨어 형상의 개념과 관리의 의미를 설명할 수 있는가? []
  - 소프트웨어 형상관리와 버전 관리 시스템의 관계를 설명할 수 있는가? []
  - git push를 수행할 때 충돌이 발생할 경우를 예를 들어 설명할 수 있는가? []
  - git push 과정에서 발생한 충돌을 처리할 수 있는가? []
  - .gitignore 파일의 용도를 설명할 수 있는가? []
  - 텍스트 파일과 바이너리 파일을 구분할 수 있는가? []
  - 바이트코드의 컴파일 버전과 JVM 버전 사이의 실행 관계를 설명할 수 있는가? []
  - 인터프리터 방식, 컴파일 방식, 하이브리드 방식을 예를 통해 비교 설명할 수 있는가? []
  - JIT 컴파일 방식과 AOT 컴파일의 특징을 설명할 수 있는가? []
  - 빌드가 무엇인지 설명할 수 있는가? []
  - 빌드 도구와 빌드 스크립트 파일의 관계를 설명할 수 있는가? []
  - Ant, Maven, Gradle 빌드 도구의 빌드 스크립트 파일을 구분할 수 있는가? [] 
  - Ant 빌드 도구 이후에 Maven과 Gradle이 등장하게 된 이유를 설명할 수 있는가? []
  - 소프트웨어 세계에서 라이브러리 파일의 무엇을 의미하는지 설명할 수 있는가? []

### 5일(2023-11-20,월)

- 강의 내용
  - 복습(1)
  - 자바 프로젝트 디렉토리 준비(1)
    - 소스 파일과 빌드 결과 파일을 폴더로 분리 
    - 컴파일과 실행
    - Maven 표준 프로젝트 디렉토리 구조
  - Gradle 빌드 도구를 사용하여 자바 프로젝트 폴더 준비(2)
    - Gradle 빌드 도구 설치
    - Gradle 빌드 도구를 이용하여 자바 프로젝트 디렉토리를 자동 구성하기
    - 자바 프로젝트의 폴더 구조 및 파일의 역할 이해
  - Gradle 빌드 스클립트 파일 구조(1)
  - Gradle 사용법(3:40)
    - tasks 명령
    - 'java' 플러그인 사용법
      - compileJava, processResources, classes
      - compileTestJava, processTestResources, testClasses
      - test, jar, build, clean
    - 'application' 플러그인 사용법
      - run 
      - 운영체제의 로케일 설정
        - 'intl.cpl' 실행 : 시스템 로케일을 UTF-8을 인식하도록 설정
  - Java 문법(0:20)
    - 클래스 블록과 .class 파일
- 학습 점검 목록
  - Maven 표준 자바 프로젝트 디렉토리 구조를 설명할 수 있는가? []
  - Gradle 빌드 도구를 사용해서 Maven 표준 자바 프로젝트 디렉토리를 구성할 수 있는가? []
  - Gradle 관련 디렉토리 및 파일의 역할을 설명할 수 있는가? []
  - Gradle 빌드 스크립트 파일 구조에 대해 설명할 수 있는가? []
  - Gradle java 플러그인이 제공하는 주요 task를 수행할 수 있는가? []
  - Gradle run 태스크를 통해 애플리케이션을 실행할 때 실행시킬 클래스를 설정할 수 있는가? []

### 6일(2023-11-21,화)

- 강의 내용
  - 복습(1)
  - Java 문법 (계속)
    - 패키지 문법(3)
      - 패키지와 디렉토리, 패키지와 클래스의 관계
      - 패키지 클래스의 컴파일과 실행
      - 패키지 이름과 도메인 명
      - 공개 클래스와 패키지 프라이빗 클래스, 소스파일명의 관계
      - 패키지 소속 클래스를 사용하는 방법
        - 클래스 이름 지정: 패키지명.클래스명
        - 컴파일 수행: -classpath 옵션 사용
    - 수업 실습 소스 준비(1)
      - 수업 진행 방식 소개
    - 주석 사용법: comments/javadoc comments/annotation(1)
    - Eclipse IDE 설치 및 설정(1)
    - 자바 프로젝트를 Eclipse IDE로 가져오기(1)
- 학습 점검 목록
  - 패키지 멤버 클래스를 만들고 컴파일, 실행할 수 있는가? []
  - 패키지 프라이빗 클래스와 공개 클래스를 설명할 수 있는가? []
  - 패키지 멤버 클래스를 사용하는 방법을 아는가? []
  - 자바 코드에 주석을 지정하는 세가지 방법과 용도를 설명할 수 있는가? []
  - 자바 프로그래밍 환경에 맞게 Eclipse IDE를 설치하고 설정할 수 있는가? []

### 7일(2023-11-22,수)

- 강의 내용
  - Gradle 자바 프로젝트를 Eclipse IDE로 임포트 하는 방법(2)
    - Gradle 'eclipse' 플러그인 추가 및 설정 방법
    - 실습1: 'test-app' Gradle 자바 프로젝트를 생성한 후 Eclipse IDE로 임포트하시오!
    - 실습2: 'myapp' Gradle 자바 프로젝트를 생성한 후 Eclipse IDE로 임포트하시오!
      - 패키지명: bitcamp.myapp
  - IntelliJ IDE 도구 설치 및 설정(2)
    - 실습1: 'myapp'을 IntelliJ로 임포트하시오!
  - 프로그래밍 기초(4)
    - 정수를 표현하는 방식: 10진수, 8진수, 16진수, 2진수
    - 컴퓨터에서 데이터를 저장하는 원리: RAM, HDD(마그네틱)
    - 숫자, 문자, 색상 등의 데이터를 메모리에 저장하는 방법
    - 정수를 2진수로 변환하는 4가지 방법: Sign-Magnitude, 1's Complement, 2's Complement, Excess-K
    - 부동소수점을 2진수로 변환하는 방법: IEEE-754 명세
    - 메모리의 입출력 기본 단위
    - 자바에서 정수와 부동소수점을 저장하는 메모리 크기와 값의 범위
- 학습 점검 목록
  - Gradle 자바 프로젝트를 IntelliJ IDE로 가져오는 방법을 아는가? []
  - 정수를 10진수, 8진수, 16진수, 2진수로 표현할 수 있는가? []
  - RAM, HDD에 데이터를 저장하는 원리를 설명할 수 있는가? []
  - 숫자, 문자, 색상 등의 데이터를 메모리에 저장하는 방법을 설명할 수 있는가? []
  - 정수를 2진수로 바꾸는 4가지 방법을 설명할 수 있는가? []
  - 부동소수점을 2진수로 바꾸는 방법을 설명할 수 있는가? []
  - 메모리의 기본 입출력 단위를 아는가? []
  - 자바에서 정수와 부동소수점을 저장하기 위해 사용하는 메모리 크기 및 저장할 수 있는 값의 범위를 아는가? []

### 8일(2023-11-23,목)

- 강의 내용
  - 복습(1)
  - 프로그래밍 기초(4)
    - 문자를 메모리에 저장하는 방법: ASCII, ISO-8859-1, EUC-KR, 조합형, MS949, Unicode, UTF-8
  - 기초 문법 소개: 리터럴, 변수, 연산자, 조건문, 반복문
    - 리터럴: 정수, 부동소수점, 문자, 문자열, 논리값
- 학습 점검 목록
  - 문자를 메모리에 저장하기 위해 2진수로 인코딩하는 방법을 설명할 수 있는가? []
  - 줄바꿈 코드를 설명할 수 있는가? []
  - 문자 리터럴을 작성할 수 있는가? []
  - 이스케이프 문자를 사용할 수 있는가? []
  - 정수, 부동소수점, 문자열, 논리값을 메모리 크기에 따라 리터럴로 작성할 수 있는가? []

### 9일(2023-11-24,금)

- 강의 내용
  - 복습(1)
  - 자바 기본 출력 다루기(com.eomcs.lang.ex99)
    - 예제 학습 및 프로젝트 적용
  - 실습 프로젝트
    - 01. 자바 프로젝트 준비하기
    - 02. 문자열 출력하기
- 학습 점검 목록
  - 