package com.example.servlet04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 세션에서 로그인 상태를 제거
        session.removeAttribute("loggedIn");
        // 로그아웃 후 Redirec 페이지 설정
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}

