package library.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ServletUtil {
    // 编码
    public static void encode(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e)  {
            e.printStackTrace();
        }
    }
     // 填充信息
    public static Object toFillInfo(HttpServletRequest req, Class clazz) {
        Object clazzObj = null;
        try {
            clazzObj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                // 判断属性是否为静态
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if (isStatic) {
                    continue;
                }
                String fname = field.getName();
                Object reqValue = req.getParameter(fname);
                field.set(clazzObj,reqValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            System.out.print("填充信息失败");
        }
        return clazzObj;
    }
}
