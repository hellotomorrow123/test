
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBean.UserNameBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统->修改密码"></s:text></title>
    </head>
    <body bgcolor="gray">
      <hr noshade/>
      <s:div align="center">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/personMessage/updateMessage.jsp">修改个人信息</s:a>
              </td>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/personMessage/lookMessage.jsp">查看个人信息</s:a>
              </td>
              <td width="33%">
                  <s:text name="修改个人密码"></s:text>
              </td>
          </tr>
      </table>
      </s:div>
      <hr noshade/>
      <s:form action="upPassAction" method="post">
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <%
            ArrayList login=(ArrayList)session.getAttribute("userName");
            if(login==null||login.size()==0){
                response.sendRedirect("http://localhost:8080/ch13/login/index.jsp");
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    UserNameBean nm=(UserNameBean)login.get(i);
                    %>
                       <tr>
                           <td height="30"><s:text name="用户密码"></s:text></td>
                         <td><input type="text" name="password1" value="<%=nm.getPassword()%>"/></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="重复密码"></s:text></td>
                         <td><input type="text" name="password2" value="<%=nm.getPassword()%>"/></td>
                       </tr>
                       <tr>
                         <td colspan="2" align="center">
                             <input type="submit" value="确 定" size="12"/>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             <input type="reset" value="清 除" size="12"/>
                         </td>
                       </tr>
                    <%
                    }
            }
          %>
        </table>
       </s:form>
    </body>
</html>
