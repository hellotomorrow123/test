

<%@page import="JavaBean.MyMessBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统->修改信息"></s:text></title>
    </head>
    <body bgcolor="gray">
        <hr noshade/>
      <s:div align="center">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:text name="修改个人信息"></s:text>
              </td>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/personMessage/lookMessage.jsp">查看个人信息</s:a>
              </td>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/personMessage/updatePass.jsp">修改个人密码</s:a>
              </td>
          </tr>
      </table>
      </s:div>
      <hr noshade/>
      <s:form action="upMessAction" method="post">
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <%
            ArrayList MyMessage=(ArrayList)session.getAttribute("MyMess");
            if(MyMessage==null||MyMessage.size()==0){
                response.sendRedirect("http://localhost:8080/ch13/login/index.jsp");
            }else{
                for(int i=MyMessage.size()-1;i>=0;i--){
                    MyMessBean mess=(MyMessBean)MyMessage.get(i);
                    %>
                       <tr>
                            <td height="30"><s:text name="用户姓名"></s:text></td>
                            <td><input type="text" name="name" value="<%=mess.getName()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="用户性别"></s:text></td>
                            <td><input type="text" name="sex" value="<%=mess.getSex()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="出生日期"></s:text></td>
                            <td><input type="text" name="birth" value="<%=mess.getBirth()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="用户民族"></s:text></td>
                            <td><input type="text" name="nation" value="<%=mess.getNation()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="用户学历"></s:text></td>
                            <td><input type="text" name="edu" value="<%=mess.getEdu()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="用户职称"></s:text></td>
                            <td><input type="text" name="work" value="<%=mess.getWork()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="用户电话"></s:text></td>
                            <td><input type="text" name="phone" value="<%=mess.getPhone()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="家庭住址"></s:text></td>
                            <td><input type="text" name="place" value="<%=mess.getPlace()%>"/></td>
                       </tr>
                       <tr>
                            <td height="30"><s:text name="邮箱地址"></s:text></td>
                            <td><input type="text" name="email" value="<%=mess.getEmail()%>"/></td>
                       </tr>
                       <tr>
                            <td colspan="2" align="center">
                             <input type="submit" value="确 定"/>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             <input type="reset" value="还 原"/>
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
