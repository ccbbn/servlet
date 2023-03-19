package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyServlet", value = "/request-body")
public class RequestBodyServlet extends HttpServlet {


    private ObjectMapper objectMapper = new ObjectMapper();



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 크라이언트가 보낸 요청 http 메시지 중 바디 읽기
//        System.out.println(request.getServletContext());
//        BufferedReader reader = request.getReader();
//        System.out.println(reader.readLine());
//        reader.lines().forEach( line -> {
//            System.out.println(line);
//        });
        ServletInputStream inputStream = request.getInputStream();
        String messageBody
                = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("메시지 바디" + messageBody);

        User user = objectMapper.readValue(messageBody, User.class);






        // String -> 객체(json)변환

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("Ok" + "\n");
        response.getWriter().write(user.getUsername() + "\n");
        response.getWriter().write((user.getAge() + "\n"));
    }


}
