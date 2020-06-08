package deverour.tower.controller;

import deverour.tower.domain.Group;
import deverour.tower.domain.User;
import deverour.tower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class LoginController {
    @Autowired
    private UserService UserService;
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) throws Exception {
        System.out.println("controller:login.run()");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();

        String username = request.getParameterValues("username")[0];
        String password = request.getParameterValues("password")[0];
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User loginUser = UserService.login(user);
        if(loginUser != null){
            //登录成功
            //将用户存入session

            request.setAttribute("login_msg","");
            httpSession.setAttribute("user",loginUser);
            System.out.println(loginUser.getName()+":登录成功  |   " +formatter.format(date) );
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/pages/home.html");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            response.sendRedirect(request.getContextPath());
            //request.getRequestDispatcher(request.getContextPath()+"/bills/login.html").forward(request,response);
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) throws Exception {
        System.out.println("controller:logout.run()");
        httpSession.setAttribute("user",null);
        System.out.println(httpSession.getAttribute("user"));
    }




}