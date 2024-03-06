package bitcamp.myapp.servlet;

import bitcamp.myapp.controller.CookieValue;
import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.controller.RequestParam;
import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.Component;
import bitcamp.util.TransactionManager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet(urlPatterns = "/app/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

  private Map<String, RequestHandler> requestHandlerMap = new HashMap<>();
  private List<Object> controllers = new ArrayList<>();

  @Override
  public void init() throws ServletException {
    try {
      System.setProperty("board.upload.dir", this.getServletContext().getRealPath("/upload/board"));
      System.setProperty("member.upload.dir", this.getServletContext().getRealPath("/upload"));

      ServletContext ctx = this.getServletContext();
      TransactionManager txManager = (TransactionManager) ctx.getAttribute("txManager");
      BoardDao boardDao = (BoardDao) ctx.getAttribute("boardDao");
      MemberDao memberDao = (MemberDao) ctx.getAttribute("memberDao");
      AssignmentDao assignmentDao = (AssignmentDao) ctx.getAttribute("assignmentDao");
      AttachedFileDao attachedFileDao = (AttachedFileDao) ctx.getAttribute("attachedFileDao");

//      controllers.add(new HomeController());
//      controllers.add(new AssignmentController(assignmentDao));
//      controllers.add(new AuthController(memberDao));
//      controllers.add(new BoardController(txManager, boardDao, attachedFileDao));
//      controllers.add(new MemberController(memberDao));

      preparePageControllers();
      prepareRequestHandlers(controllers);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // URL 요청을 처리할 request handler를 찾는다.
      RequestHandler requestHandler = requestHandlerMap.get(request.getPathInfo());
      if (requestHandler == null) {
        throw new Exception(request.getPathInfo() + " 요청 페이지를 찾을 수 없습니다.");
      }

      // 페이지 컨트롤러가 작업한 결과를 담을 보관소를 준비한다.
      Map<String, Object> map = new HashMap<>();
      Object[] args = prepareRequestHandlerArguments(requestHandler.handler, request, response,
          map);

      String viewUrl = (String) requestHandler.handler.invoke(requestHandler.controller, args);

      // 페이지 컨트롤러의 작업이 끝난 후 map 객체에 보관된 값을 JSP가 사용할 수 있도록
      // ServletRequest 보관소로 옮긴다.
      for (Entry<String, Object> entry : map.entrySet()) {
        request.setAttribute(entry.getKey(), entry.getValue());
      }

      // 페이지 컨트롤러가 알려준 JSP로 포워딩 한다.
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).forward(request, response);
      }

    } catch (Exception e) {
      // 페이지 컨트롤러에서 오류가 발생했으면 오류페이지로 포워딩한다.
      request.setAttribute("message", request.getPathInfo() + " 실행 오류!");

      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      request.setAttribute("detail", stringWriter.toString());

      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }

  private void preparePageControllers() throws Exception {
    File classpath = new File("./build/classes/java/main");
    System.out.println(classpath.getCanonicalPath());
    findComponents(classpath, "");
  }

  private void findComponents(File dir, String packageName) throws Exception {
    File[] files = dir.listFiles(file ->
        file.isDirectory() || (file.isFile()
            && !file.getName().contains("$")
            && file.getName().endsWith(".class")));

    if (packageName.length() > 0) {
      packageName += ".";
    }
    for (File file : files) {
      if (file.isFile()) {
        Class<?> clazz = Class.forName(packageName + file.getName().replace(".class", ""));
        Component compAnno = clazz.getAnnotation(Component.class);
        if (compAnno != null) {
          Constructor<?> constructor = clazz.getConstructor();
          controllers.add(constructor.newInstance());
          System.out.println(clazz.getName() + " 객체 생성!");
        }
      } else {
        findComponents(file, packageName + file.getName());
      }
    }
  }

  private void prepareRequestHandlers(List<Object> controllers) {
    for (Object controller : controllers) {
      Method[] methods = controller.getClass().getDeclaredMethods();
      for (Method m : methods) {
        RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
          requestHandlerMap.put(requestMapping.value(), new RequestHandler(controller, m));
        }
      }
    }
  }

  private Object[] prepareRequestHandlerArguments(
      Method handler,
      HttpServletRequest request,
      HttpServletResponse response,
      Map<String, Object> map) throws Exception {

    // 요청 핸들러의 파라미터 정보를 알아낸다.
    Parameter[] methodParams = handler.getParameters();

    // 파라미터에 전달할 값을 담을 배열을 준비한다.
    Object[] args = new Object[methodParams.length];

    // 파라미터를 분석하여 각 파라미터에 맞는 값을 배열에 담는다.
    for (int i = 0; i < args.length; i++) {
      Parameter methodParam = methodParams[i];
      if (methodParam.getType() == HttpServletRequest.class
          || methodParam.getType() == ServletRequest.class) {
        args[i] = request;
      } else if (methodParam.getType() == HttpServletResponse.class
          || methodParam.getType() == ServletResponse.class) {
        args[i] = response;
      } else if (methodParam.getType() == Map.class) {
        args[i] = map;
      } else if (methodParam.getType() == HttpSession.class) {
        args[i] = request.getSession();
      } else {
        CookieValue cookieValueAnno = methodParam.getAnnotation(CookieValue.class);
        if (cookieValueAnno != null) {
          String value = getCookieValue(cookieValueAnno.value(), request);
          if (value != null) {
            args[i] = valueOf(value, methodParam.getType());
          }
          continue; // 다음 파라미터로 간다.
        }

        RequestParam requestParam = methodParam.getAnnotation(RequestParam.class);
        if (requestParam != null) {
          // 클라이언트가 보낸 요청 파라미터 값을 원한다면
          // 그 값을 메서드의 파라미터 타입으로 변환한 후 저장한다.
          String requestParameterName = requestParam.value();

          if (methodParam.getType() == Part[].class) {
            Collection<Part> parts = request.getParts();
            List<Part> fileParts = new ArrayList<>();
            for (Part part : parts) {
              if (part.getName().equals(requestParameterName)) {
                fileParts.add(part);
              }
            }
            args[i] = fileParts.toArray(new Part[0]);

          } else if (methodParam.getType() == Part.class) {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
              if (part.getName().equals(requestParameterName)) {
                args[i] = part;
                break;
              }
            }
          } else {
            String requestParameterValue = request.getParameter(requestParameterName);
            args[i] = valueOf(requestParameterValue, methodParam.getType());
          }
          continue;
        }

        // 파라미터 타입이 도메인 클래스일 경우 해당 클래스의 객체를 준비하여
        // 그 객체에 요청 파라미터 값들을 담은 다음에 저장한다..
        args[i] = createValueObject(methodParam.getType(), request);
      }
    }

    return args;
  }

  // 문자열을 주어진 타입으로 변환하여 리턴한다.
  private Object valueOf(String strValue, Class<?> type) {
    if (type == byte.class) {
      return Byte.parseByte(strValue);
    } else if (type == short.class) {
      return Short.parseShort(strValue);
    } else if (type == int.class) {
      return Integer.parseInt(strValue);
    } else if (type == long.class) {
      return Long.parseLong(strValue);
    } else if (type == float.class) {
      return Float.parseFloat(strValue);
    } else if (type == double.class) {
      return Double.parseDouble(strValue);
    } else if (type == boolean.class) {
      return Boolean.parseBoolean(strValue);
    } else if (type == char.class) {
      return strValue.charAt(0);
    } else if (type == Date.class) {
      return Date.valueOf(strValue);
    } else if (type == String.class) {
      return strValue;
    }
    return null;
  }

  // request handler의 파라미터 타입이 도메인 클래스일 때,
  // 해당 클래스의 객체를 생성하고 요청 파라미터 값을 담아서 리턴한다.
  private Object createValueObject(Class<?> type, HttpServletRequest request) throws Exception {
    // 1) 도메인 클래스의 생성자 알아냄
    Constructor constructor = type.getConstructor();

    // 2) 생성자를 이용하여 도메인 객체 생성
    Object obj = constructor.newInstance();

    // 3) 도메인 클래스의 메서드 목록을 가져옴
    Method[] methods = type.getDeclaredMethods();

    // 4) 메서드 중에서 셋터 메서드를 알아냄
    for (Method setter : methods) {
      if (!setter.getName().startsWith("set")) {
        continue;
      }

      // 5) 셋터 메서드의 이름에서 프로퍼티 이름을 추출
      // 예) setFirstName ==> firstName
      String propName =
          Character.toLowerCase(setter.getName().charAt(3)) + setter.getName().substring(4);

      // 6) 프로퍼티 이름으로 넘어온 요청 파라미터 값을 꺼낸다.
      String requestParamValue = request.getParameter(propName);

      // 7) 도메인 객체의 프로퍼티 이름과 일치하는 요청 파라미터 값이 있다면 객체에 저장한다.
      if (requestParamValue != null) {
        // 셋터 메서드의 파라미터 타입을 알아낸다.
        Class<?> setterParameterType = setter.getParameters()[0].getType();

        // 셋터를 호출한다.
        // 예) setFirstName("길동");
        setter.invoke(obj, valueOf(requestParamValue, setterParameterType));
      }
    }
    return obj;
  }

  private String getCookieValue(String name, HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(name)) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

}
