package library.servlet;

import library.model.User;
import library.model.status.LoginStatus;
import library.service.LoginService;
import library.service.impl.LoginServiceImpl;
import library.util.Result;
import library.util.ServletUtil;
import library.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
/*
      不能直接在idea运行
      默认运行index.jsp文件
    1.先要发布。把Web加入到发布里面，servlet要在classes文件下
    2.运行服务器
    3.输入url
*/
public class LoginServlet extends HttpServlet {

    private LoginService loginService = LoginServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.encode(req, resp);

        String url = req.getServletPath();
        switch (url) {
            case "/loginCheck":
                loginCheck(req, resp);
                break;

            case "/login":
                login(req, resp);
                break;

            case "/logout":
                logOut(req, resp);
                break;
        }

    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置session生命有效时间为0
//        req.getSession().setMaxInactiveInterval(0);
//        清空用户信息缓存
        req.getSession().removeAttribute("ses_userInfo");
        resp.sendRedirect("./logout.jsp");
    }

    private void loginCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        String userName = req.getParameter("name");
        String userPwd = req.getParameter("pwd");

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd)) {
            out.println(Result.Fail("输入参数有误！"));
            return;
        }

        User user = new User();
        user.setUserName(userName);
        user.setUserPwd(userPwd);

        int res = loginService.login(user);
        if (res == LoginStatus.USER_NOT_EXISTS) {
            out.println(Result.Fail("登录失败，用户不存在！"));
            return;
        }

        if (res == LoginStatus.USER_PWD_WRONG) {
            out.println(Result.Fail("登录失败，密码不正确！"));
            return;
        }

        out.println(Result.OK("登录成功！"));

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        String userName = req.getParameter("name");
        String userPwd = req.getParameter("pwd");

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd)) {
            out.println(Result.Fail("输入参数有误！"));
            return;
        }

        User user = new User();
        user.setUserName(userName);
        user.setUserPwd(userPwd);

        int res = loginService.login(user);
        if (res == LoginStatus.LOGIN_SUCCESS) {
            User dbUserInfo = loginService.getUserInfo(userName);
            session.setAttribute("ses_userInfo", dbUserInfo);

            // 建立cookie（键name 值value 对）
            Cookie userNameCookie = new Cookie("userName", userName);
            userNameCookie.setMaxAge(300);
            Cookie userPwdCookie = new Cookie("userPwd", userPwd);
            userPwdCookie.setMaxAge(300);
            // 将cookie添加到响应对象，通过响应对象告诉浏览器要保存的cookie
            resp.addCookie(userNameCookie);
            resp.addCookie(userPwdCookie);

            // 跳转到首页
            resp.sendRedirect("./library");
        } else {
            out.println("非法操作！请先进行登录！");
        }
    }
}
