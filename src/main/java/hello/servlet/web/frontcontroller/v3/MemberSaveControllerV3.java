package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3{

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.builder().username(username).age(age).build();

        memberRepository.save(member);
        // 모델
        ModelView mv = new ModelView("save"); // 앞에 주소 부분은 리졸브로 해결할꺼임

        mv.getModel().put("member", member); // 모델 뷰에 데이터 담음
       // ModelView.setAttribute("member", member); // request에 모델이 담김

        // 프론트로 보내기
        return mv;

    }
}
