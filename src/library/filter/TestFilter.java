package library.filter;

import library.model.User;
import library.util.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("test过滤器诞生");
    }

    private String[] fxUrls = {"/index.jsp", "/logout.jsp", "/loginCheck", "/login", "/register", "/register.jsp", "/borrowBkInfo.jsp"};

    /**
     * 每次访问时对请求、响应进行过滤
     * @param servletRequest 客户端的请求
     * @param servletResponse 服务器要进行的响应
     * @param filterChain 过滤器执行链对象：filterChain.doFilter(servletRequest, servletResponse); 放行方法，将拦截的请求进行放行（一定要放行）
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 转换成HttpServlet...
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 数据统一编码
        ServletUtil.encode(req, resp);

        HttpSession session = req.getSession();
        // 查看session里面有没有用户信息
        Object sesUser = session.getAttribute("ses_userInfo");

        // 当前请求的url
        String url = req.getServletPath();

        // 放行静态资源
        if (url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".jpg") || url.endsWith(".ico") || url.endsWith(".png")) {
            filterChain.doFilter(req, resp);
            return ;
        }

        // 遍历要被放行的url
        for (String fxurl: fxUrls) {
            if (fxurl.equals(url)) {
                filterChain.doFilter(req, resp);
                return ;
            }
        }

//        // 没登陆被放行的url
//        if ("/index.jsp".equals(url) || "/logout".equals(url)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return ;
//        }

        // 没登陆的url被重定向到登录页面
        if (sesUser == null) {
            resp.sendRedirect("./index.jsp");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
