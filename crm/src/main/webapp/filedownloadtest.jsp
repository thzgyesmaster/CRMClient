<%--
  Created by IntelliJ IDEA.
  User: lifu
  Date: 2024/5/4
  Time: 下午2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js">
            $(function(){
                //给下载按钮添加单击事件
                $("#fileDownloadBtn").click(function(){
                    //发送文件下载的请求
                    window.location.href="workbench/activity/fileDownload.do";
                })
            })
    </script>
</head>
<body>
    <input type="button" value="下载文件" id="fileDownloadBtn">
</body>
</html>
