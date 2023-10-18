package com.example.servlet04;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // 세션에서 로그인 여부를 확인
        if (session.getAttribute("loggedIn") != null) {
            // 이미 로그인되어 있는 경우 boardList.jsp 이동
            RequestDispatcher reqDis = request.getRequestDispatcher("/boardList.jsp");
            reqDis.forward(request, response);
        } else {
            // 로그인되지 않은 경우 loginForm.jsp 이동
            RequestDispatcher reqDis = request.getRequestDispatcher("/loginForm.jsp");
            reqDis.forward(request, response);
        }
    }

    //로그인 된 상태 -> boardList.jsp
    //로그인 x 상태 -> loginForm.jsp

}
