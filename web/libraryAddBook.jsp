<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>新的图书信息</title>
    <script src="https://cdn.bootcss.com/jquery/2.2.2/jquery.js"></script>
</head>
<style>
    /*框*/
    .addBookFrame {
        background-color: antiquewhite;
        border: 1px solid aquamarine;
        width: 500px;
        padding: 50px 20px;
        margin: 50px auto;
    }
    /*标题*/
    .addBookTitle {
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
    button {
        background-color: honeydew;
        padding: 5px 15px;
        margin-left: 60%;
        border-radius: 5px;
    }
</style>
<body>
<div class="addBookFrame">
    <div class="addBookTitle">增加新的图书</div>
    <form action="./libraryAddBook.jsp" method="post"> <%-- 这时并不需要action --%>
        <table>
            <tr>
                <td>编&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td><input type="number" name="bookId" id="bookId" /></td>
            </tr>
            <tr>
                <td>书&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                <td><input type="text" name="bookName" id="bookName"/></td>
            </tr>
            <tr>
                <td>作&nbsp;&nbsp;&nbsp;&nbsp;者：</td>
                <td><input type="text" name="author" id="author"/></td>
            </tr>
            <tr>
                <td>数&nbsp;&nbsp;&nbsp;&nbsp;量：</td>
                <td><input type="number" name="bookNum" id="bookNum"/></td>
                <%-- value="0"--%>
            </tr>
            <tr>
                <td>详细信息：</td>
                <td><input type="text" name="information" id="information" /></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="button" onclick="addBook()">增加</button></td> <%--不加 type="button" 默认 type = "submit" --%>
            </tr>
        </table>
    </form>
</div>

</body>
<script>
    function isEmpty(obj) {
        return obj == null || obj == "";
    }
    function addBook() {
        var id = $("#bookId").val();
        var bookName = $("#bookName").val();
        var author = $("#author").val();
        var bookNum = $("#bookNum").val();
        var information = $("#information").val();
        if (isEmpty(id) || isEmpty(bookName)) {
            console.log("=============");
            alert("编号或书名不允许为空！");
            return;// 这里应跳回添加书页面
        }
        if (isEmpty(bookNum)) { // 在这里必须给数字付初始值，不然会报错
            bookNum = 0;
        }
        $.ajax({
            url: "./libraryAddBook",
            type: "get",
            data: {
                bookId: id,
                bookName: bookName,
                author: author,
                bookNum: bookNum,
                information: information
            },
            success: function (res) {
                res = JSON.parse(res);
                console.log("=============");
                console.log(res);
                // 比较地址，两个等于比较的是内容
                if (res.code === 200) {
                    alert("添加成功！");
                    window.location.href = "./library"
                } else {
                    alert("添加失败！")
                }
            }
        })
    }
</script>
</html>

