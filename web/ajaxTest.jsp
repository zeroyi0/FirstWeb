<%@ page import="library.model.status.LoginStatus" %>
<%@ page import="library.servlet.LoginServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>小熊一家</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
        -webkit-appearance: none;
    }
    /* 登录框*/
    .loginFrame {
        background-color: antiquewhite;
        border: 2px solid black;
        width: 300px;
        padding: 30px 50px;
        padding-bottom: 5px;
        margin: 200px auto;
        border-radius: 8px;
    }
    /*登录标题*/
    .loginTitle {
        /*background-color: green;*/
        /*border: 1px solid black;*/
        text-align: center;
        font-size: 30px;
        font-family: 幼圆;
        font-weight: bold;
    }
    /* 登录文本*/
    .loginText {
        /*background-color: antiquewhite;*/
        /*border: 2px solid salmon;*/
        padding: 20px;
        margin: 10px;
        text-align: left;
    }
    /*输入框*/
    input[type = 'text'],input[type = 'password'] {
        width: 100%;
        border: 1px solid #555;
        outline: none;
        background-color: white;
        border-radius: 8px;
        padding: 4px 6px;
        font-size: 16px;
        margin: 7px;
    }
    /* 登录按钮*/
    button,input[type='button'] {
        background-color: peachpuff;
        border-radius: 48px;
        padding: 5px 30px;
        margin: 10px 5px;
        outline: none;
        border: 1px solid #e0bba1;
        cursor: pointer;
    }
    button:active{
        background-color: #f4cfaf;
    }
    input[type='button']:active {
        background-color: #f4cfaf;
    }
    .btn-area {
        display: flex;
        justify-content: space-between;
    }
    .btn-area a {
        text-decoration: none;
        cursor: default;
    }
</style>

<body>
<div class="loginFrame">
    <div class="loginTitle">用户登录</div>
    <%-- action： 表单提交地址； method：表单提交方式 --%>
        <div>用户名：</div>
        <input type="text" name="name" id="username" />
        <div>密码：</div>
        <input type="password" name="pwd" id="password"/>

        <div class="btn-area">
            <a href="http://localhost:8080/biandan/register.jsp">
                <input type="button" value="注册"/>
            </a>
            <input type="button" value="登录" onclick="login()"/>
        </div>

    <div ></div>
</div>

</body>

<script src="https://cdn.bootcss.com/jquery/2.2.2/jquery.js"></script>
<script>
    function isEmpty(str) {
        return !str || str == "";
    }
    function login() {
        var userName = $("#username").val();
        var userPwd = $("#password").val();
        if (isEmpty(userName)) {
            alert("用户名不能为空");
            return ;
        }
        if (isEmpty(userPwd)) {
            alert("密码不能为空");
            return ;
        }
        $.ajax({
            url: "./login",
            type: "post",
            data: {
                name: userName,
                pwd: userPwd
            },
            datatype: 'json',
            async: true,
            success: function (res) {
                console.log(res)
                res = JSON.parse(res)
                if (res.code != 200) {
                    alert(res.errMsg)
                }
            }
        })
    }

    <%--$(document).ready(function(){--%>
    <%--$("button").click(function(){--%>
    <%--alert("登录成功")--%>
    <%--<%--%>

    <%--%>--%>
    <%--});--%>
    <%--});--%>
</script>
</html>

