package hello.servlet.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "MemberSaveServlet", value = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String username = request.getParameter("username");
        int age= Integer.parseInt(request.getParameter("age"));

        Member member = Member.builder().username(username).age(age).build();  // new Member(username, age) 랑 같은데 빌더는 매개변수 순서 상관 없이 입력가능

        memberRepository.save(member);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String res = objectMapper.writeValueAsString(memberRepository.findById(member.getId()));
        response.getWriter().write(res);



    }

}
