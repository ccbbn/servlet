package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet ( name = "frontControllerServletV2" ,  urlPatterns = "/front-controller/v2/*")

public class FrontControllerServletV2 extends HttpServlet {


    /// <URI, Controller 객체>
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 서비스 안에 넣어도 됨
    // 컨테이너 안에 넣

    // 객체를 만들어 놓고 요청이랑 비교
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // http 요청 메시지를 읽어서 URI를 확인 why? 어떤놈을 호출할 지 알기 위해서
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);

        if ( controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        // 일치하는 컨트롤을 호출하면서 해당하는 컨트롤의 뷰객체를 반환
        MyView view = controller.process(request, response);
        view.render(request, response);






    }
}
