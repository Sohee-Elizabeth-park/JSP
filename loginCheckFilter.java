package com.example.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;

public class loginCheckFilter implements Filter {
    //filter 기능 수행, chain 이용 -> 체인의 다음 필터로 처리 전달 가능
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendRedirect(request.getContextPath() + "/login.do");
        } else{
            chain.doFilter(req, res);
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

