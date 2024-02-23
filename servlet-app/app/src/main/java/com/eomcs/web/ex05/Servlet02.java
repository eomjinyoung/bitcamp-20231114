package com.eomcs.web.ex05;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex05/s2")
public class Servlet02 extends MyHttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (request.getMethod().equals("GET")) {
      out.println("GET 요청입니다.");

    } else if (request.getMethod().equals("POST")) {
      out.println("POST 요청입니다.");

    } else {
      out.println("이 서블릿이 다루지 못하는 요청 방식입니다.");
    }
  }
}
