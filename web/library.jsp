<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="library.model.User" %>
<%@ page import="library.model.status.LoginStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
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
        background-color: beige;
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
    table {
        margin: auto;
    }
    td,th {
        border: 1px solid darkgrey;
        width: 130px;
        padding: 6px 12px;
        text-align: center;
    }
    button {
        border-radius: 5px;
        font-size: 13px;
        background-color: beige;
    }
</style>
<body>
    <div class="libraryTitle">
        <span id="xx">小熊书管理系统</span>
        <span class="userInfo">你好,【<%=((User)session.getAttribute("ses_userInfo")).getUserName()%>】
            <a href="./logout.jsp">注销</a>
        </span>
    </div>
    <table>
        <tr>
            <th>编号</th>
            <th>书名</th>
            <th>作者</th>
            <th>在架上数量</th>
            <th>借出数量</th>
            <th>详细信息</th>
            <th style="width: 170px">改变图书信息<a href="./libraryAddBook.jsp">
                <button style="width: 20px; height: 20px; margin-right: 1px">+</button>
            </a>
            </th>

        </tr>
        <c:forEach var="book" items="${req_books}">
            <tr id="book_${book.bookId}">
                <td id="bookId_${book.bookId}">${book.bookId}</td>
                    <%--<td><c:out value="${book.name}" /></td>--%>
                <td id="bookName_${book.bookId}">${book.bookName}</td>
                <td id="author_${book.bookId}">${book.author}</td>
                <td id="bookNum_${book.bookId}">${book.bookNum}</td>
                <td id="borrowOut_${book.bookId}">${book.borrowOut}</td>
                <td id="information_${book.bookId}">详细信息 </td>
                <td><button data-toggle="modal" data-target="#myModal" onclick="chgBook(${book.bookId})">修改</button>&nbsp;&nbsp;

                    <button id="delBtn_${book.bookId}" onclick="deleteBook(${book.bookId})">删除</button></td>
            </tr>
        </c:forEach>
        </div>
    </table>
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
<%--<script src="https://cdn.bootcss.com/jquery/2.2.2/jquery.js"></script>--%>
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script>
    function isEmpty(obj) {
        return obj === 0 || "" == obj;
    }

    function deleteBook(id) {
        if (confirm('确定删除此项？')) {
            alert('你删除的ID为：' + id);
        }
        $.ajax({
            url: "./libraryDelete",
            type: "get",
            data: {
                id: id
            },
            success: function (data) {
                console.log(data);
                // 移除id为 #book_id  的tr
                $("#book_" + id).remove();
                alert('删除成功'); //这里需要改成自动关闭
            }
        })
    }

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
            success: function (data) {
                console.log(data);
                alert('修改成功'); //这里需要改成自动关闭+ 改成删除tr
            }
        })
    }
</script>

</html>