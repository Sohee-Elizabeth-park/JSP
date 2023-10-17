package com.example.servlet04;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String checkbox = request.getParameter("checkbox");

        if(id.equals("asdf") && pwd.equals("1234")) {
            //id, pwd 일치하면 쿠키 설정
            Cookie cookie = new Cookie("id", id);
            if(checkbox != null && checkbox.equals("true")) {
                cookie.setMaxAge(60*60*24);
            } else {
                cookie.setMaxAge(0);
            }
            //사용자에게 쿠키값을 전달
            response.addCookie(cookie);
            //id, pwd 일치하면 jsp 페이지로 이동
            RequestDispatcher reqDis = request.getRequestDispatcher("/userInfo.jsp");
            reqDis.forward(request, response);

        } else {
            //id와 pwd가 일치하지 않는 경우 : login.html로 돌아가기
            RequestDispatcher reqDis = request.getRequestDispatcher("/login.jsp");
            reqDis.forward(request, response);
        }
    }
    }
