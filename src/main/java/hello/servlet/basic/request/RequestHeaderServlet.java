package hello.servlet.basic.request;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RequestHeaderServlet", value = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);

    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("헤더---클라가 요청-----");

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + ":" +request.getHeader(headerName));
        });

        System.out.println("헤더-------END--");
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("request---클라가 요청-----");
        System.out.println("request.getMethod" + request.getMethod());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); // https가 적용되었냐?
        System.out.println("request-------END--");



    }
}
