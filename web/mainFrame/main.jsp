

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统"/></title>
    </head>
    <frameset cols="20%,*" framespacing="0" border="no" frameborder="0">
        <frame src="../mainFrame/left.jsp" name="left" scrolling="no">
        <frameset rows="20%,10%,*">
            <frame src="../mainFrame/top.jsp" name="top" scrolling="no">
            <frame src="../mainFrame/toop.jsp" name="toop" scrolling="no">
            <frame src="../mainFrame/about.jsp" name="main">
        </frameset>
    </frameset>

</html>
