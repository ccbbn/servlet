package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 모델 안에 클라가 보낸 데이터가 들어있다
        // 하지만 뷰(jsp)로 포워딩할 때 필요한 매개 값은 "request"다.
        // 따라서 "model"안에 잇는 데이터를 "request"에 복사한다. 지금 리퀘스트는 비어있음


        modelToRequestAttribute(model, request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //포이치를 사용해서 맵안 에 있는 모든 키값을 순회한다
        model.forEach((key, value) -> {
            request.setAttribute(key, value);
        });
    }


}
