package com.example.servlet04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dice-servlet")
public class DiceServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //num1, num2 변수 설정
        int num1 = 6, num2 = 6;

        //랜덤 다이스 설정
        int random_dice_01 = (int)(Math.random()*num1)+1;
        int random_dice_02 = (int)(Math.random()*num2)+1;

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<img src='img/dice_" + random_dice_01 + ".png' width=100px; height=100px;>");
        out.println("<img src='img/dice_" + random_dice_02 + ".png' width=100px; height=100px;>");
        out.println("</body>");
        out.println("</html>");
    }
}
