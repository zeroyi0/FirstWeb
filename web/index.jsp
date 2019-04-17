<%@ page import="library.model.status.LoginStatus" %>
<%@ page import="library.servlet.LoginServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
  <head>
    <title>小熊一家</title>
    <script src="https://cdn.bootcss.com/jquery/2.2.2/jquery.js"></script>
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
    input {
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
    button {
      background-color: peachpuff;
      border-radius: 48px;
      padding: 5px 30px;
      margin: 10px 5px;
      outline: none;
      border: 1px solid #e0bba1;
      cursor: pointer;
    }
    button:active {
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
  <%
    // 通过request获取到所有的cookie
    Cookie[] cookies = request.getCookies();
    String userName = "";  // 建立变量来保存cookie的值
    String userPwd = "";
    if (cookies.length > 0) {
      for (Cookie cookie : cookies) {
        // 建立cookie（键name 值value 对）
        // 通过name来判断是否为需要获取的cookie
        if (cookie.getName().equals("userName")) {
            userName = cookie.getValue();
            continue;
        }
        if (cookie.getName().equals("userPwd")) {
            userPwd = cookie.getValue();
            continue;
        }
      }
    }
  %>
  <body>
      <div class="loginFrame">
        <div class="loginTitle">用户登录</div>
        <%-- action： 表单提交地址； method：表单提交方式 --%>
        <form action="./login" method="post" class="loginText">
          <div>用户名：</div>
            <input type="text" name="name" id="username" value="<%=userName%>"/>
          <div>密码：</div>
            <input type="password" name="pwd" id="password" value="<%=userPwd%>"/>

          <div class="btn-area">
            <a href="./register.jsp">
              <button type="button">注册</button>
            </a>
            <button type="button" onclick="login()" style="float: right">登录</button>
          </div>
        </form>
      </div>

  </body>
  <script>
    function isEmpty(obj) {
        if (obj == null || obj == "") {
            return true;
        }
    }
    function login() {
        var userName = $("#username").val();
        var passWord = $("#password").val();
        if (isEmpty(userName)) {
            alert("用户名不能为空");
            return;
    }
        if (isEmpty(passWord)) {
            alert("密码不能为空")
            return;
        }
        $.ajax({
          url: "./loginCheck",
          type: "post",
          data: {
              name: userName,
              pwd: passWord
          },
          success: function (data) {
              // console.log(data);
              // console.log(data == {"code":500,"errMsg":"登录失败！密码错误！"});
              // console.log(data.code);
              // 格式化
              try {
                  // console.log(data);
                  // console.log("==========");

                  var dataParse = JSON.parse(data);
                  // console.log(dataParse);
                  console.log(dataParse.code);
                  if (dataParse.code != 200) {
                      alert(dataParse.errMsg);
                      return;
                  } else {
                      $("form").submit();
                  }
              }catch (e) {
                  alert("登录失败");
                  console.log(e);
              }
          },
          error:function (res) {
              console.log(res);
            alert("系统异常，登陆失败")
        }
        })
    }
  </script>
</html>

