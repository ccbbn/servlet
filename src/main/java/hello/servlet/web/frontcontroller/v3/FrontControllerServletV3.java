package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet ( name = "frontControllerServletV3" ,  urlPatterns = "/front-controller/v3/*")

public class FrontControllerServletV3 extends HttpServlet {


    /// <URI, Controller 객체>
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 서비스 안에 넣어도 됨
    // 컨테이너 안에 넣

    // 객체를 만들어 놓고 요청이랑 비교
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // http 요청 메시지를 읽어서 URI를 확인 why? 어떤놈을 호출할 지 알기 위해서
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);

        if ( controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        // 일치하는 컨트롤을 호출하면서 해당하는 컨트롤의 뷰객체를 반환
        // v2 모델 :MyView view = controller.process(request, response);
        // v3 모델 : 레퀘스트랑 리스폰스를 map으로 대체, MyView -> ModelView, 서블릿에 대한 종속성을 제거하기 위해

        //클라가 보낸 정보
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        //"save" -> WEB-INF/view/save.jsp"
        MyView view = viewResolver(mv.getViewName());

        //클라가 보낸 {username : 나, age : 555}의 정보는 어디? => modelView 객체 안에 들어있다
        view.render(mv.getModel(), request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/"+ viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // getParameterNames 모든 파라미터를 다 가져옴, 리퀘스트에 있는 것을 paramMap에 담음
        // {username : 나, age : 555}
        // request.getParameter("username")
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
