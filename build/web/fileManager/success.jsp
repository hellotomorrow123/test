
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="文件上传成功"></s:text></title>
        <META HTTP-EQUIV="Refresh" CONTENT="3; URL=http://localhost:8080/ch13/fileManager/lookFile.jsp"/>
    </head>
    <body bgcolor="gray">
        <s:div align="center">
            <h1>文件上传成功！</h1>
            <s:a href="http://localhost:8080/ch13/fileManager/lookFile.jsp"><h3>3秒后自动跳转......</h3></s:a>
            <hr/>
            文件标题:<s:property value="+title"/><br/>
            <s:property value="uploadFileName"/><br/>
            <image src="<s:property value="'../save/'+uploadFileName"/>"/>
        </s:div>     
    </body>
</html>
