package library.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ServletUtil {

    public static void encode(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e)  {
            e.printStackTrace();
        }
    }

}
