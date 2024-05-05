<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>演示文件上传</title>
</head>
<body>
    <form action="workbench/activity/fileUpload.do" method="post" enctype="multipart/form-data">
        <input type="file" name="myFile"><br>
        <input type="text" name="userName"><br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
