package bitcamp.myapp;

import bitcamp.RequestException;
import bitcamp.myapp.dao.json.AssignmentDaoImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("서버 실행!");

      while (true) {
        service(serverSocket.accept());
      }

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }

  void service(Socket socket) {

    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      System.out.println("클라이언트와 연결됨!");

      processRequest(in, out);

      System.out.println("클라이언트 연결 종료!");

    } catch (Exception e) {
      System.out.println("클라이언트 연결 오류!");
    }
  }

  void processRequest(DataInputStream in, DataOutputStream out) throws IOException {

    System.out.println("[클라이언트 요청]");
    String dataName = in.readUTF();
    String command = in.readUTF();
    String value = in.readUTF();

    try {
      Object dao = daoMap.get(dataName);
      if (dao == null) {
        throw new RequestException("요청 데이터가 없습니다!");
      }
      System.out.printf("데이터: %s\n", dataName);

      Method commandHandler = findMethod(dao.getClass(), command);
      System.out.printf("메서드: %s\n", commandHandler.getName());

      Object[] args = getArguments(commandHandler, value);

//      Class<?> returnType = commandHandler.getReturnType();
//      System.out.printf("리턴: %s\n", returnType.getName());

      // 메서드를 호출한다.
      Object returnValue = commandHandler.invoke(dao, args);

      out.writeUTF("200");
      out.writeUTF(gson.toJson(returnValue));
      System.out.println("클라이언트에게 응답 완료!");

    } catch (RequestException e) {
      out.writeUTF("400");
      out.writeUTF(gson.toJson(e.getMessage()));

    } catch (Exception e) {
      out.writeUTF("500");
      out.writeUTF(gson.toJson(e.getMessage()));
    }
  }

  Method findMethod(Class<?> clazz, String name) {
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      if (m.getName().equals(name)) {
        return m;
      }
    }
    throw new RequestException("요청 메서드가 없습니다!");
  }

  Object[] getArguments(Method m, String json) {
    Parameter[] params = m.getParameters();
    System.out.printf("파라미터 개수: %d\n", params.length);

    Object[] args = new Object[params.length];

    if (params.length > 0) {
      Class<?> paramType = params[0].getType();
      Object paramValue = gson.fromJson(json, paramType);
      args[0] = paramValue;
    }

    return args;
  }
}
