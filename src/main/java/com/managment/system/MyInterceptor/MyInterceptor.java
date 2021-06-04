package com.managment.system.MyInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception {

        String name=(String) request.getSession().getAttribute("name");
        String password=(String) request.getSession().getAttribute("password");
        if(name!=null&password!=null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/index.html");
        return false;
    }
}
