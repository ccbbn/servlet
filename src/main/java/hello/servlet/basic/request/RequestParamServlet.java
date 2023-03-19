package hello.servlet.basic.request;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.SortedMap;

@WebServlet(name = "RequestParamServlet", value = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터-start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName -> {
                    System.out.println(paraName + " = " +
                            request.getParameter(paraName));
                });

        System.out.println("전체 파리미터- end");
        System.out.println();

        System.out.println("단일 파라미터-start");
        System.out.println("request.getParameter(\"username\") = " + request.getParameter("username"));
        System.out.println("request.getParameter(\"age\") = " + request.getParameter("age"));

        System.out.println("이름이 같은");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");
        for ( String name : usernames) {
            System.out.println("username =" +name);
        }

        response.getWriter().write("ok");

    }


}
