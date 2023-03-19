package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ResponseJsonServlet", value = "/response-json")
public class ResponseJsonServlet extends HttpServlet {


    private ObjectMapper objectMapper = new ObjectMapper(); // 제이슨을 위해 필요 한 것

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

//        User user = new User();
//        user.setUsername("현덕");
//        user.setAge(43243);
//        String res = objectMapper.writeValueAsString(user);// 출력할 것을 제이슨으로 직렬화

        // QueryString을 이용해 클라이언트에서 username과 age를 보내고
        // 서버에서 해당 정보를 읽어 user 객체를 생성한다.
        // 생성된 user 객체는 json으로 변환한다.




        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        String res = objectMapper.writeValueAsString(user);
        response.getWriter().write(res);


    }


}
