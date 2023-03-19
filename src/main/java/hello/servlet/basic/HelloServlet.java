package hello.servlet.basic;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello") /*ex http://localhost:8080/hello */
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpServletRequest was로 파싱해서 가져온 요청
        System.out.println(req.getMethod());
        System.out.println(req.getRemoteUser());
        System.out.println(req.getRequestURL());

        System.out.println(req.getParameter("username"));

//        if ( req.getParameter("username").equals("현덕") ){
//            resp.setContentType("text/plain");
//            resp.setCharacterEncoding("utf-8");
//
//
//            resp.getWriter().write(req.getParameter("username")+"안녕하세요");
//        }



        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello");
        resp.getWriter().write("\n");
        resp.getWriter().write("ㅎㅇㅎㅇㅎㅇ");
        resp.getWriter().write(req.getParameter("username")+"안녕하세요");
    }
}
