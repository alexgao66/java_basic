<%--
  Created by IntelliJ IDEA.
  User: gaojun
  Date: 16/3/14
  Time: 下午7:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello,meituan</title>
</head>
<body>
<h1>PDF watermark:</h1>
<form action="/tools/uploadPdf" method="post" enctype="multipart/form-data">
    Select File to Upload:<input type="file" name="pdfFile">
    <br>
    <input type="submit" value="Upload">
</form>
</body>
</html>
