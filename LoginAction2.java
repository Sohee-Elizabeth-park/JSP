package com.example.servlet04;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginAction2")
public class LoginAction2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        //id와 pwd가 일치하는 경우 : userInfo.jsp로 연결
        if(id.equals("asdf") && pwd.equals("1234")) {
            //id, pwd 값을 서블릿에서 JSP 페이지로 데이터 전달
            request.setAttribute("id", id);
            request.setAttribute("pwd", pwd);
            //id, pwd 일치하면 jsp 페이지로 이동
            request.getRequestDispatcher("/userInfo.jsp").forward(request, response);

        } else {
            //id와 pwd가 일치하지 않는 경우 : login.html로 돌아가기
            RequestDispatcher reqDis = request.getRequestDispatcher("/login.html");
            reqDis.forward(request, response);
        }
    }
}
