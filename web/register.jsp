<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>小动物注册</title>
    <script src="https://cdn.bootcss.com/jquery/2.2.2/jquery.js"></script>
</head>
<style>
    /*注册框*/
    .registerFrame {
        background-color: antiquewhite;
        border: 1px solid aquamarine;
        width: 500px;
        padding: 50px 20px;
        margin: 50px auto;
    }
    /*注册标题*/
    .registerTitle {
        /*border: 1px solid black;*/
        font-weight: bold;
        font-family: 幼圆;
        font-size: 40px;
        text-align: center;
        color: darkblue;
    }
    /*表格的位置*/
    table {
        margin: auto;
        margin-top: 50px;
    }
    td {
        /*border: 2px solid black;*/
        /*background-color: salmon;*/
        text-align: right;
        padding: 5px 20px;
    }
    td input {
        text-align: left;
        margin-left: 0px;
    }
    td select {
       width: 173px;
        height: 21px;
    }
    button {
        background-color: honeydew;
        padding: 5px 15px;
        margin-left: 60%;
        border-radius: 5px;
    }
</style>
<body>
<div class="registerFrame">
    <div class="registerTitle">用户注册</div>
    <form action="./register" method="post">
    <table>
        <tr>
            <td>学&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
            <td><input type="number" name="uid" id="uid" /></td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" id="username"/></td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
            <td><input type="password" name="pwd1" id="password1"/></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input type="password" name="pwd2" id="password2" /></td>
        </tr>
        <tr>
            <td>职&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
            <td>
                <select id="identity">
                    <option value="学生">学生</option>
                    <option value="教师">教师</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
            <td><input type="email" name="mailBox" id="mailBox" /></td>
        </tr>
        <tr>
            <td></td>
            <td><button type="button" onclick="register()" >注册</button></td>
        </tr>
    </table>
    </form>
</div>

</body>
<script>
    function register() {
        $.ajax({
            url: "./register",
            type: "post",
            data: {
                uid: $("#uid").val(),
                userName: $("#username").val(), //键名与servlet一致？
                pwd1: $("#password1").val(),
                pwd2: $("#password2").val(),
                identity: $("#identity").val(),
                mailBox: $("#mailBox").val(),
            },
            success: function (res) {
                var jsParseRes = JSON.parse(res);
                    if(jsParseRes.code == 200) {
                        alert("注册成功！")
                    }else {
                        alert("注册失败，用户名或学号已存在")
                    }
            },
            error: function (res) {
              alert("系统异常，注册失败！")
            }
        })
    }
</script>
</html>
