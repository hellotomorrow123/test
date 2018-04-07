<%@page import="JavaBean.UserNameBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人管理系统"/></title>
    </head>
    <body bgcolor="#CCDDEE">
        <%  
            String loginname=null;
            ArrayList login=(ArrayList)session.getAttribute("userName");
            if(login==null||login.size()==0){
                loginname="水木清华";
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    UserNameBean nm=(UserNameBean)login.get(i);
                    loginname=nm.getUserName();
                }
            }
        %>
        <table width="100%" align="right" bgcolor="blue">
              <tr height="10" bgcolor="gray" align="center">
                  <td><a href="http://localhost:8080/ch13/personMessage/lookMessage.jsp" target="main">个人信息管理</a></td>
                  <td><a href="http://localhost:8080/ch13/friendManager/lookFriends.jsp" target="main">通讯录管理</a></td>
                  <td><a href="http://localhost:8080/ch13/dateTimeManager/lookDay.jsp" target="main">日程安排管理</a></td>
                  <td><a href="http://localhost:8080/ch13/fileManager/lookFile.jsp" target="main">个人文件管理</a></td>
                  <td><a href="http://localhost:8080/ch13/login/index.jsp" target="_top">退出主页面</a></td>
                  <td>欢迎<%=loginname%>使用本系统！</td>
              </tr>
        </table>
    </body>
</html>
