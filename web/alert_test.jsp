<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>弹框</title>
    <style>
        .show {
            display: block;
        }
        .hide {
            display: none;
        }
        textarea{
            width: 200px;
            height: 150px;
        }
        .position_f {
            /*background-color: #555555;*/
            position: relative;
        }
        .position_son {
            position: absolute;
            left: 150px;
            bottom: 1px;
        }
    </style>
</head>
<body>
    <button style="" onclick="alter()">按钮</button>
    <div id="kuang" class="hide position_f" >
        <textarea></textarea>
        <button onclick="queDing()" class="position_son">保存</button>
    </div>
</body>
<script>
    function alter() {
        document.getElementById("kuang").style.display = "block";
    }
    function queDing() {
        alert("修改成功");
        var elemobj = document.getElementById("kuang");
        elemobj.classList.remove("hide")
        // .style.display = "none";

    }

</script>

</html>
