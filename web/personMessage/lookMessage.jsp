
<%@page import="JavaBean.MyMessBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统"></s:text></title>
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
                  <s:text name="查看个人信息"></s:text>
              </td>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/personMessage/updatePass.jsp">修改个人密码</s:a>
              </td>
          </tr>
      </table>
      </s:div>
      <hr noshade/>
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
                         <td><%=mess.getName()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="用户性别"></s:text></td>
                         <td><%=mess.getSex()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="出生日期"></s:text></td>
                         <td><%=mess.getBirth()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="用户民族"></s:text></td>
                         <td><%=mess.getNation()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="用户学历"></s:text></td>
                         <td><%=mess.getEdu()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="用户职称"></s:text></td>
                         <td><%=mess.getWork()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="用户电话"></s:text></td>
                         <td><%=mess.getPhone()%></td>
                       </tr>
                       <tr>
                         <td height="30"><s:text name="家庭住址"></s:text></td>
                         <td><%=mess.getPlace()%></td>
                       </tr>
                       <tr>
                           <td height="30"><s:text name="邮箱地址"></s:text></td>
                         <td><%=mess.getEmail()%></td>
                       </tr>
                    <%
                }
            }
          %>
        </table>
    </body>
</html>
