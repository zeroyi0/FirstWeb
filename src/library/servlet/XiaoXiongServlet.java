package library.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class XiaoXiongServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("URL路径信息：" + req.getRequestURI());
        System.out.println("Servlet的URL的一部分：" + req.getServletPath());
        System.out.println("IP: " + req.getRemoteAddr());
        System.out.println("Host: " + req.getRemoteHost());
        System.out.println("Port: " + req.getRemotePort());
        System.out.println("User: " + req.getRemoteUser());
        System.out.println("Server IP: " + req.getLocalAddr());
        System.out.println("Server Port: " + req.getServerPort());
        // 防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset:UTF-8");
        resp.setHeader("ContentType", "text/html;charset:UTF-8");
        resp.setStatus(404);
        // 获取请求参数并转成Integer类型
        int a = Integer.parseInt(req.getParameter("a"));
        Integer b = Integer.valueOf(req.getParameter("b"));
        Integer c = Integer.valueOf(req.getParameter("c"));
        // 获取输出流，使用输出流打印结果
        PrintWriter out = resp.getWriter();
        out.println("" +
                "<html>" +
                    "<head><title>" + "小熊" +"</title></head>" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\">" + "小兔子" + "</h1>\n" +
                        "<ul>\n" +
                        "  <li><b>站点名</b>："
                        + "小熊的家" + "\n" +
                        "  <li><b>网址</b>："
                        + req.getParameter("url") + "\n" +
                        "</ul>\n" +
                        "</body>" +
                "</html>" +
                "小熊算出来了：" + a + "+" + b + "+" + c + "=" + (a + b + c));
        resp.sendRedirect("https://www.baidu.com");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:UTF-8");
        // 获取请求参数并转成Integer类型
        Integer a = Integer.valueOf(req.getParameter("a"));
        Integer b = Integer.valueOf(req.getParameter("b"));
        // 获取输出流，使用输出流打印结果
        PrintWriter out = resp.getWriter();
        out.println("小熊算出来了：" + a + "+" + b + "=" + (a + b));
    }

}
