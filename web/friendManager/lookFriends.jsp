

<%@page import="JavaBean.MyFriBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统->查看联系人"></s:text></title>
    </head>
    <body bgcolor="gray">
      <hr noshade/>
      <s:div align="center">
      <s:form action="findFriAction" method="post">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/friendManager/addFriend.jsp">增加联系人</s:a>
              </td>
              <td width="33%">
                  <s:text name="查看联系人"></s:text>
              </td>
              <td width="33%">
                  <s:text name="修删联系人:"></s:text>
                  <input type="text" name="friendname"/>
                  <input type="submit" value="查找"/>
              </td>
          </tr>
      </table>
      </s:form>
      </s:div>
      <hr noshade/>
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <tr>
              <th height="30">好友姓名</th>
              <th height="30">好友电话</th>
              <th height="30">邮箱地址</th>
              <th height="30">工作单位</th>
              <th height="30">家庭住址</th>
              <th height="30">QQ</th>
          </tr>
          <%
            ArrayList friends=(ArrayList)session.getAttribute("friends");
            if(friends==null||friends.size()==0){
            %>
            <s:div align="center"><%="您还没有添加联系人！"%></s:div>
            <%
            }else{
                for(int i=friends.size()-1;i>=0;i--){
                    MyFriBean ff=(MyFriBean)friends.get(i);
                    %> 
                   <tr>
                     <td><%=ff.getName()%></td>
                     <td><%=ff.getPhone()%></td>
                     <td><%=ff.getEmail()%></td>
                     <td><%=ff.getWorkplace()%></td>
                     <td><%=ff.getPlace()%></td>
                     <td><%=ff.getQQ()%></td>
                   </tr>
                    <%
                }
            }
          %>
        </table>
    </body>
</html>
