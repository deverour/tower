package deverour.tower.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //System.out.println(uri);
        Object user = request.getSession().getAttribute("user");
        //System.out.println("getAttribute:"+user);
        if(user != null){
            //登录了。放行
            return true;
        }else{
            //没有登录。跳转登录页面
            //System.out.println("您尚未登录，请登录");
            request.setAttribute("login_msg","您尚未登录，请登录");
            //request.getRequestDispatcher("/index.html").forward(request,response);
            response.sendRedirect("/index.html");
            //System.out.println("重定向了");
            return false;

        }



    }
}
