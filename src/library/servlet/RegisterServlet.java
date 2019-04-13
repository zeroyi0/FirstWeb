package library.servlet;

import library.model.User;
import library.model.status.RegisterStatus;
import library.service.RegisterService;
import library.service.impl.RegisterServiceImpl;
import library.util.Result;
import library.util.ServletUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.encode(req, resp);
        long uid = Long.parseLong(req.getParameter("uid"));//valueOf
        String userName = req.getParameter("userName");
        String password1 = req.getParameter("pwd1");
        String password2 = req.getParameter("pwd2");
        String uIdentity = req.getParameter("identity");
        String uMailBox = req.getParameter("mailBox");

        //输出流
        PrintWriter out = resp.getWriter();
        if (isEmpty(uid) || isEmpty(userName) || isEmpty(password1) || isEmpty(password2) || isEmpty(uIdentity) || isEmpty(uMailBox)) {
            String rgstResult = Result.Fail("必填项不允许为空！").toString();
            out.println(rgstResult);
            return;
        }
        if (!password1.equals(password2)) {
            String rgstResult = Result.Fail("两次密码输入不一致！请重新注册").toString();
            out.println(rgstResult);
            return;
        }
        // 用户注册
        User user = new User(uid, userName, password1, uIdentity, uMailBox);
        RegisterService registerService = RegisterServiceImpl.getIntance();
        // 注册结果
        String rgst = null;
        switch (registerService.register(user)) {
            case RegisterStatus.REGISTER_SUCCESS:
                rgst = Result.OK("注册成功！").toString();
                out.println(rgst);
                break;
            case RegisterStatus.NAME_HAS_EXIST:
                rgst = Result.Fail("注册失败,用户名已存在").toString();
                out.println(rgst);
                break;
            case RegisterStatus.ID_HAS_EXIST:
                rgst = Result.Fail("注册失败,用户ID已存在").toString();
                out.println(rgst);
                break;
            default:
                rgst = Result.Fail("注册失败,未知错误").toString();
                out.println(rgst);
                break;
        }
    }
    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }
}
