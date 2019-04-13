<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>JSP测试</title>
</head>
<body>
    <%
        for (int i = 1; i < 10; i ++) {
            for (int j = 1; j <= i; j ++) {
                out.print(j + "*" + i + "=" + (i * j));
                out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            out.print("<br>");
        }
    %>
    <%="2342qeaasdase"%>
</body>
</html>
