package hello.servlet.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MemberSaveServlet", value = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // 가져온다
        String username = request.getParameter("username");
        int age= Integer.parseInt(request.getParameter("age"));


        // 저장한다
        Member member = Member.builder().username(username).age(age).build();  // new Member(username, age) 랑 같은데 빌더는 매개변수 순서 상관 없이 입력가능

        memberRepository.save(member);

        // 클라로 보낼 http 제작
        //1 제이슨 방식
//        respondJson(response, member);
        //2 http 방식
        responseHtml(response, member);


    }

    private void respondJson(HttpServletResponse response, Member member) throws IOException {
        // 헤더 정보
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        // 바디 입력
        String res = objectMapper.writeValueAsString(memberRepository.findById(member.getId()));
        response.getWriter().write(res);
    }


    // 서블릿 안에 직접 동적 html 생성
    private void responseHtml(HttpServletResponse response, Member member) throws IOException {
        // 헤더 정보
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        // 바디 입력
        PrintWriter w =response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+ member.getId() + "</li>\n" +
                " <li>username="+ member.getUsername() + "</li>\n" +
                " <li>age="+ member.getAge() + "</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");

    }

}
