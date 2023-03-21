package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = Member.builder().username(username).age(age).build();

        memberRepository.save(member);
        // 모델
        request.setAttribute("member", member); // request에 모델이 담김
        // 프론트로 보내기
        return new MyView("/WEB-INF/views/save");

    }
}
