<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>小熊书管理系统</title>
</head>
<style>
    .background {
        background: white url("imgs/xx.jpg") repeat-x right bottom;
        padding: 0px;
        margin: 0px;
    }
    .logoutTitle {
        background: peachpuff;
        font-size: 30px;
        text-align: center;
        padding: 30px 50px;
        margin: 200px 35%;
        position: absolute;
    }
    .son {
        position: absolute;
        right: 20px;
        top: 100px;
    }
    a:link {color: blue;}
    a:visited{color: green}
    a:hover{color: chocolate}
    a:active{color: salmon}

</style>
<body class="background">
    <div class="logoutTitle">
        您已成功退出小熊书管理系统
        <div><a href="index.jsp"><span class="son" style="font-size: 15px">重新登录</span></a></div>
    </div>

    <%--<img src="imgs/xx.jpg" alt="小熊" width="500px" height="505px">--%>
    <div ></div>

</body>
</html>
