package bitcamp.myapp;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.json.AssignmentDaoImpl;
import bitcamp.myapp.dao.json.BoardDaoImpl;
import bitcamp.myapp.dao.json.MemberDaoImpl;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  BoardDao boardDao = new BoardDaoImpl("board.json");
  BoardDao greetingDao = new BoardDaoImpl("greeting.json");
  AssignmentDao assignmentDao = new AssignmentDaoImpl("assignment.json");
  MemberDao memberDao = new MemberDaoImpl("member.json");

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

      String dataName = in.readUTF();
      String command = in.readUTF();
      String value = in.readUTF();
      System.out.println("클라이언트가 보낸 데이터 읽음!");

      System.out.println(dataName);
      System.out.println(command);
      System.out.println(value);

      out.writeUTF("200");

      String json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
          .toJson(boardDao.findAll());
      out.writeUTF(json);
      System.out.println("클라이언트로 데이터 전송!");

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }
}
