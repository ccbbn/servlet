package hello.servlet.basic.response;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ResponseHeaderServlet", value = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // was가  request에 요청을 저장
        //[http status]
        response.setStatus(HttpServletResponse.SC_OK); //상수 값으로 바로 상태코드 접근

        //[http headers]
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello!");


        response.sendRedirect("/basic/hello-form.html"); // 저리가라



        //[http body]
        response.getWriter().println("ok!");
        response.getWriter().println("띄 어 쓰 기");

    }


}
