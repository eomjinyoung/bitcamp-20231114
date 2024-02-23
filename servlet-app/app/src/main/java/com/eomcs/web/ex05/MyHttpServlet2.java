package com.eomcs.web.ex05;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyHttpServlet2 extends GenericServlet {

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {
    this.service((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
  }

  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getMethod().equals("GET")) {
      this.doGet(request, response);
    } else if (request.getMethod().equals("POST")) {
      this.doPost(request, response);
    } else {
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("요청 메서드를 지원하지 않습니다.");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("아직 구현하지 않았습니다.");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("아직 구현하지 않았습니다.");
  }

}
