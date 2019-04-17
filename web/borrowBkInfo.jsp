<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="library.model.User" %>
<%@ page import="library.model.status.LoginStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>借阅图书信息</title>
    <%--bootstrop--%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }
    /*标题*/
    .libraryTitle {
        background-color: papayawhip;
        border: 1px solid salmon;
        font-size: 50px;
        font-weight: bold;
        color: chocolate;
        text-align: center;
        padding: 20px 0;
        position: relative;
    }
    .userInfo {
        position: absolute;
        font-size: 14px;
        color: #555;
        right: 10px;
        top: 2px;
    }

    .choseFrame {
        padding: 5px 30px;
        font-size: 17px;
    }
    /*table {*/
    /*margin: auto;*/
    /*}*/
    td,th {
        border: 1px solid darkgrey;
        width: 130px;
        text-align: center;
    }
    button {
        border-radius: 5px;
        font-size: 13px;
        background-color: beige;
    }
    .flex {
        display: flex;
    }
</style>
<body>
    <div class="libraryTitle">
        <span id="xx">小熊书管理系统</span>
        <span class="userInfo">你好,【<a data-toggle="dropdown" href="#">${ses_userInfo.userName}</a>】
                <ul class="dropdown-menu">
                    <li><a href="#">借书记录</a></li>
                    <li><a href="./logout">注销</a></li>
                </ul>
            </span>
    </div>
    <div class="container-fluid" style="background-color: darkgrey">
        <div class="row">
            <div class="col-lg-1" style="background-color: beige">
                saaa
            </div>
            <div class="col-lg-2" style="background-color: darksalmon">
                <div class="choseFrame">
                    <table class="table">
                    <tr><th>啦啦啦啦</th></tr>
                    <tr><td></td></tr>
                    <tr><td><a href="./library">全部图书信息</a></td></tr>
                    <tr><td><a href="./borrowBkInfo.jsp">借阅图书信息</a></td></tr>
                    <tr><td></td></tr>

                    </table>
                </div>
            </div>
            <div class="col-lg-8" style="background-color: darkseagreen">
                <table class="table table-bordered table-striped" style="width: 75%" >
                    <tr>
                        <th style="width: 330px">借书时间</th>
                        <th>借书人ID</th>
                        <th>姓名</th>
                        <th>图书ID</th>
                        <th>书名</th>
                        <th>是否归还</th>
                        <th style="width: 330px">还书时间</th>
                    </tr>
                    <%--填写信息--%>
                    <c:forEach var="borrowBk" items="${ses_borrowBkInfo}" varStatus="vsta">
                        <tr id="book_${borrowBk.bookId}"
                            <c:if test="${vsta.index % 2 == 1}">class="warning" </c:if>>
                            <td id="borrowTime_${borrowBk.borrowTime}">${borrowBk.borrowTime}</td>
                                <%--<td><c:out value="${book.name}" /></td>--%>
                            <td id="userId_${borrowBk.borrowTime}">${borrowBk.userId}</td>
                            <td id="userName_${borrowBk.borrowTime}">${borrowBk.userName}</td>
                            <td id="bookId_${borrowBk.borrowTime}">${borrowBk.bookId}</td>
                            <td id="bookName_${borrowBk.borrowTime}">${borrowBk.bookName}</td>
                            <td id="isReturn_${borrowBk.borrowTime}">${borrowBk.isReturnBook}</td>
                            <td id="returnTime_${borrowBk.borrowTime}">${borrowBk.returnTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>
<script>
    $(function() {
        $(".dropdown-toggle").dropdown('toggle');
    });
    function isEmpty(obj) {
        return obj === 0 || "" == obj;
    }
</script>

</html>
