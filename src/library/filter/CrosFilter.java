package library.filter;

import library.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {


        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        String origin = req.getHeader("Origin");


        if(!StringUtils.isEmpty(origin)){
            //带cookie的时候 Origin必须是全匹配 , 不能使用*
            res.addHeader("Access-Control-Allow-Origin", origin);
        }
        String header = req.getHeader("Access-Control-Request-Headers");


        //支持所有自定义头
        if( !StringUtils.isEmpty(header)){
            res.addHeader("Access-Control-Request-Headers",header);
        }
        res.addHeader("Access-Control-Allow-Methods", "*");
        //options预检命令
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        //options预检缓存
        res.addHeader("Access-Control-Max-Age", "3600");
        //enable cookie
        res.addHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
