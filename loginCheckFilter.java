package com.example.servlet04;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;

@WebFilter("/protected/*")
public class loginCheckFilter implements Filter {
    //filter 기능 수행, chain 이용 -> 체인의 다음 필터로 처리 전달 가능
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 세션에서 로그인 여부를 확인
        HttpSession session = ((HttpServletRequest) request).getSession();
        boolean loggedIn = session.getAttribute("loggedIn") != null;

        if (loggedIn) {
            // 사용자가 로그인한 경우 요청을 계속 진행시킴
            chain.doFilter(request, response);
        } else {
            // 사용자가 로그인하지 않은 경우 로그인 페이지로 리디렉션
            ((HttpServletResponse) response).sendRedirect(request.getServletContext().getContextPath() + "/loginForm.jsp");
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필요한 경우 초기화 작업 수행
    }

    @Override
    public void destroy() {
        // 필요한 경우 필터 파괴 시 작업 수행
    }
}

