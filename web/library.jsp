<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="library.model.User" %>
<%@ page import="library.model.status.LoginStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>

<%--<%--%>
    <%--User user = (User) session.getAttribute("ses_userInfo");--%>
    <%--if (user == null) {--%>
        <%--response.sendRedirect("./index.jsp");--%>
    <%--}--%>
<%--%>--%>
<html>
<head>
    <title>小熊书管理系统</title>
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
                 aa
            </div>
            <div class="col-lg-2" style="background-color: darksalmon">
                <div class="choseFrame">
                    <div>啦啦啦啦</div>
                    <br>
                    <div><a href="./library">全部图书信息</a></div>
                    <div><a href="./borrowBkInfo">借阅图书信息</a></div>
                </div>
            </div>
            <div class="col-lg-9" style="background-color: darkseagreen">
                <table class="table table-bordered table-striped" style="width: 70%">
                    <tr>
                        <th>编号</th>
                        <th>书名</th>
                        <th>作者</th>
                        <th>在架上数量</th>
                        <th>借出数量</th>
                        <th>详细信息</th>
                        <th style="width: 240px">改变图书信息
                            <a href="./libraryAddBook.jsp" style="text-decoration: none">
                                <c:if test="${sessionScope.ses_userInfo.identity == 'admin'}">
                                    <button class="btn btn-xs btn-primary" style="padding:1px 2px 4px 4px;margin-right: 1px">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </c:if>
                            </a>
                        </th>

                    </tr>
                    <%--填写信息--%>
                    <c:forEach var="book" items="${req_books}" varStatus="vsta">
                        <tr id="book_${book.bookId}"
                                <c:if test="${vsta.index % 2 == 1}">class="warning" </c:if>>
                            <td id="bookId_${book.bookId}">${book.bookId}</td>
                                <%--<td><c:out value="${book.name}" /></td>--%>
                            <td id="bookName_${book.bookId}">${book.bookName}</td>
                            <td id="author_${book.bookId}">${book.author}</td>
                            <td id="bookNum_${book.bookId}">${book.bookNum}</td>
                            <td id="borrowOut_${book.bookId}">${book.borrowOut}</td>
                            <td id="information_${book.bookId}">详细信息 </td>
                            <td><c:if test="${sessionScope.ses_userInfo.identity == 'admin'}">
                                <button class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal" onclick="chgBook(${book.bookId})">修改</button>&nbsp;&nbsp;
                                <button class="btn btn-danger btn-xs" id="delBtn_${book.bookId}" onclick="deleteBook(${book.bookId})">删除</button></c:if>      &nbsp;
                                <%--借书--%>
                                <button class="btn btn-primary btn-xs" id="borrowB_${book.bookId}" onclick="borrowBook(${book.bookId})">借书</button>&nbsp;&nbsp;
                                <%--还书--%>
                                <button class="btn btn-primary btn-xs" id="returnB_${book.bookId}" onclick="returnBook(${book.bookId})">还书</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <%--关闭按钮--%>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        要修改的书的编号为：${book.bookId}
                    </h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <td>书&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                            <td><input type="text" id="bookName"/></td>
                            <%-- value="0"--%>
                        </tr>
                        <tr>
                            <td>作&nbsp;&nbsp;&nbsp;&nbsp;者：</td>
                            <td><input type="text" id="author"/></td>
                            <%-- value="0"--%>
                        </tr>
                        <tr>
                            <td>现有数量：</td>
                            <td><input type="number" id="bookNum"/></td>
                            <%-- value="0"--%>
                        </tr>
                        <tr>
                            <td>借出数量：</td>
                            <td><input type="number" id="borrowOut"/></td>
                            <%-- value="0"--%>
                        </tr>
                        <tr>
                            <td>详细信息：</td>
                            <td><input type="text" id="information"/></td>
                            <%-- value="0"--%>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button id="updata" data-dismiss="modal" onclick="changeBook()" type="button" class="btn btn-primary">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
<script>
    $(function() {
        $(".dropdown-toggle").dropdown('toggle');
    });
    function isEmpty(obj) {
        return obj === 0 || "" == obj;
    }
    function deleteBook(id) {
        if (!confirm('确定删除此项？')) {
            return;
        }
        alert('你删除的ID为：' + id);
        $.ajax({
            url: "./libraryDelete",
            type: "get",
            data: {
                id: id
            },
            success: function (data) {
                console.log(data);
                alert('删除成功'); //这里需要改成自动关闭
                // 移除id为 #book_id  的tr
                $("#book_" + id).remove();
                return;
            },
            error: function (data) {
                alert("系统错误，删除失败")
            }
        })
    }
    // 获取图书信息
    function getBookInfo(id) {
        return {
            bookId : $("#bookId_" + id).text(),
            bookName : $("#bookName_" + id).text(),
            author : $("#author_" + id).text(),
            bookNum : $("#bookNum_" + id).text(),
            borrowOut : $("#borrowOut_" + id).text(),
            information : $("#information_" + id).text()
        }
    }
    function chgBook(id) {
        var bookInfo = getBookInfo(id);
        document.getElementById("updata").bookId = id;
        // 填充值
        $("#myModalLabel").text("要填充的书的编号是：" + bookInfo.bookId);

        $("#bookName").val(bookInfo.bookName);
        $("#author").val(bookInfo.author);
        $("#bookNum").val(bookInfo.bookNum);
        $("#borrowOut").val(bookInfo.borrowOut);
        $("#information").val(bookInfo.information);

    }
    function changeBook() {
        var id = document.getElementById("updata").bookId;

        var bookName = $("#bookName").val();
        var author = $("#author").val();
        var bookNum = $("#bookNum").val();
        var borrowOut = $("#borrowOut").val();
        var information = $("#information").val();

        $.ajax({
            url: "./libraryChange",
            type: "post",
            data: {
                id: id,
                bookName: bookName,
                author: author,
                bookNum: bookNum,
                borrowOut: borrowOut,
                information: information
            },
            success: function (res) {
                console.log(res);
                var dataP = JSON.parse(res);
                console.log(dataP)
                 if (dataP.code === 200) {
                     alert('修改成功'); //这里需要改成自动关闭+ 改成删除tr
                     $("#bookId_" + id).text(id);
                     $("#bookName_" + id).text(bookName);
                     $("#author_" + id).text(author);
                     $("#bookNum_" + id).text(bookNum);
                     $("#borrowOut_" + id).text(borrowOut);
                     $("#information_" + id).text(information);
                     return;
                 }
                 alert("修改失败")
                return;
            },
            error: function (res) {
                alert("系统错误，未知错误")
                return;
            }
        })
    }

    function borrowBook(id) {
        console.log($("#bookNum_" + id).text());
        var bookNum = $("#bookNum_" + id).text();
        var borrowOut = $("#borrowOut_" + id).text();
        if (bookNum <= 0) {
            alert("库存不足，无法借书！");
            return;
        }
        $.ajax({
            url: "./libraryBorrow",
            type: "post",
            data: {
                id: id
            },
            success: function (data) {
                console.log(data);
                var dataP = JSON.parse(data);
                if (dataP.code === 200) {
                    alert('借书成功'); //这里需要改成自动关闭
                    $("#bookNum_" + id).text(bookNum - 1);
                    $("#borrowOut_" + id).text(parseInt(borrowOut) + 1);
                    return;
                } else {
                    alert("借书失败");
                    return;
                }
            },
            error: function (data) {
                alert("系统错误，借书失败");
            }
        })
    }

    function returnBook(id) {
        console.log($("#borrowOut_" + id).text());
        var borrowOut = $("#borrowOut_" + id).text();
        var bookNum = $("#bookNum_" + id).text();
        if (borrowOut <= 0) {
            alert("未借阅过此书，还书失败");
            return;
        }
        $.ajax({
            url: "./returnBk",
            type: "post",
            data: {
                id: id
            },
            success: function (data) {
                console.log(data);
                var dataP = JSON.parse(data);
                if (dataP.code === 200) {
                    alert('还书成功'); //这里需要改成自动关闭
                    $("#bookNum_" + id).text(parseInt(bookNum) + 1);
                    $("#borrowOut_" + id).text(borrowOut - 1);
                    return;
                } else {
                    alert("还书失败");
                    return;
                }
            },
            error: function (data) {
                alert("系统错误，还书失败");
            }
        })
    }
</script>

</html>
