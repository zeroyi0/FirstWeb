<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>弹呀弹</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }
    .div {
        width: 100px;
        height: 100px;
    }
    .red {
        background-color: red;
    }
    .blue {
        background-color: blue;
    }
    .yellow {
        background-color: yellow;
    }
    .flex {
        display: flex;
        flex-direction: row;
        width: 500px;
        height: 500px;
        background-color: beige;
        justify-content: space-between;
        align-items: flex-end;
        flex-wrap: wrap;
    }
    .father {
        position: relative;
        width: 400px;
        height: 400px;
        /* 距离左边10px */
         left: 100px;
        /*right: 10px;*/
        background-image: url("./imgs/xx.jpg");
        background-size: 100% 100%;
    }
    .son {
        position: absolute;
        right: 10px;
    }
</style>
<body>
    <%--<div class="father red">--%>
        <%--<div class="div son yellow"></div>--%>
    <%--</div>--%>
    <div class="flex">
        <div class="div red"></div>
        <div class="div yellow"></div>
        <div class="div blue"></div>
    </div>
</body>
</html>
