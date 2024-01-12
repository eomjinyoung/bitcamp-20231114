package bitcamp.myapp;

import bitcamp.myapp.dao.json.AssignmentDaoImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerApp {

  HashMap<String, Object> daoMap = new HashMap<>();
  Gson gson;

  public ServerApp() {
    daoMap.put("board", new BoardDaoImpl("board.json"));
    daoMap.put("greeting", new BoardDaoImpl("greeting.json"));
    daoMap.put("assignment", new AssignmentDaoImpl("assignment.json"));
    daoMap.put("member", new MemberDaoImpl("member.json"));

    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  }

  public static void main(String[] args) {
    new ServerApp().run();
  }

  void run() {
    System.out.println("[과제관리 서버시스템]");

    try {
      // 1) 랜카드 연결 정보를 준비한다.
      // => 랜카드와 연결하는 것은 실제 OS가 수행한다.
      // => JVM은 OS가 작업한 결과를 가져온다.
      // new ServerSocket(포트번호)
      // => 포트번호: 랜카드로 들어온 데이터를 받을 때 사용할 식별 번호. 중복 불가.
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("서버 실행!");

      // 2) 클라이언트의 연결을 기다림
      // => 클라이언트 대기 목록에서 먼저 연결된 순서대로 클라이언트 연결 정보를 꺼낸다.
      // => 클라이언트 대기 목록에 아무것도 없다면 연결이 될 때까지 리턴하지 않고 기다린다.
      System.out.println("클라이언트 연결을 기다리는 중...");
      Socket socket = serverSocket.accept();
      System.out.println("대기 목록에서 클라이언트 연결 정보를 꺼냈음!");

      // 3) 클라이언트와 통신
      DataInputStream in = new DataInputStream(socket.getInputStream());
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      System.out.println("입출력 준비 완료!");

      while (true) {
        System.out.println("----------------------------");
        
        String dataName = in.readUTF();
        String command = in.readUTF();
        String value = in.readUTF();
        System.out.println("클라이언트 요청:");

        // dataName으로 DAO를 찾는다.
        Object dao = daoMap.get(dataName);
        System.out.printf("데이터: %s\n", dataName);

        // command 이름으로 메서드를 찾는다.
        Method[] methods = dao.getClass().getDeclaredMethods();
        Method commandHandler = null;
        for (Method m : methods) {
          if (m.getName().equals(command)) {
            commandHandler = m;
            break;
          }
        }
        System.out.printf("메서드: %s\n", commandHandler.getName());

        // 메서드의 파라미터 정보를 알아낸다.
        Parameter[] params = commandHandler.getParameters();
        System.out.printf("파라미터 개수: %d\n", params.length);

        // 메서드를 호출할 때 파라미터에 넘겨 줄 데이터를 담을 배열을 준비한다.
        Object[] args = new Object[params.length];

        // 아규먼트 값 준비하기
        // => 현재 모든 DAO의 메서드는 파라미터가 최대 1개만 있다.
        // => 1개만 있다는 가정하에서 아규먼트 값을 준비한다.
        if (params.length > 0) {
          // 파라미터 타입을 알아낸다.
          Class<?> paramType = params[0].getType();

          // 클라이언트가 보낸 JSON 문자열을 해당 파라미터 타입 객체로 변환한다.
          Object paramValue = gson.fromJson(value, paramType);

          // 아규먼트 배열에 저장한다.
          args[0] = paramValue;
        }

        // 메서드의 리턴 타입을 알아낸다.
        Class<?> returnType = commandHandler.getReturnType();
        System.out.printf("리턴: %s\n", returnType.getName());

        // 메서드를 호출한다.
        Object returnValue = commandHandler.invoke(dao, args);

        out.writeUTF("200");
        out.writeUTF(gson.toJson(returnValue));
        System.out.println("클라이언트에게 응답 완료!");
      }

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }
}
