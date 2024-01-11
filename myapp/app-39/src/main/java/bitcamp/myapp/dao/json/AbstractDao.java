package bitcamp.myapp.dao.json;

import bitcamp.myapp.dao.DaoException;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public abstract class AbstractDao<T> {

  protected ArrayList<T> list;
  private String filepath;

  public AbstractDao(String filepath) {
    this.filepath = filepath;
    loadData();
  }

  protected void loadData() {
    try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {

      // 파일에서 JSON 문자열을 모두 읽어서 버퍼에 저장한다.
      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      // 이 클래스가 다루는 데이터의 클래스 정보를 알아낸다.
      // 타입 파라미터 T가 가리키는 클래스가 무엇인지 알아낸다.
      Class<T> dataType = (Class<T>) ((ParameterizedType) this.getClass() // 이 메서드를 호출한 클래스의 정보를 알아낸다.
          .getGenericSuperclass() // AbstractDao 클래스의 정보를 알아낸다.
      ).getActualTypeArguments()[0]; // AbstractDao 에 전달한 제네릭 타입의 클래스 정보를 알아낸다.

      // 버퍼에 저장된 JSON 문자열을 가지고 컬렉션 객체를 생성한다.
      list = (ArrayList<T>) new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(
          strBuilder.toString(),
          TypeToken.getParameterized(ArrayList.class, dataType));

    } catch (Exception e) {
      list = new ArrayList<>();
      throw new DaoException("데이터 로딩 오류!", e);
    }

  }

  protected void saveData() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(filepath))) {
      out.write(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(list));
    } catch (Exception e) {
      throw new DaoException("데이터 저장 오류!", e);
    }
  }
}
