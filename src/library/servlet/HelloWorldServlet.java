package library.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 继承HttpServlet
 */
public class HelloWorldServlet extends HttpServlet {

    /**
     * 接收GET请求
     * @param req request请求实体
     * @param resp response响应实体
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求、响应的编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 通过请求request获取到请求参数
        String name = req.getParameter("name");
        // 通过响应获取到响应的输出流
        PrintWriter out = resp.getWriter();
        // 打印输出内容
        out.println( "Hello！" + name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
