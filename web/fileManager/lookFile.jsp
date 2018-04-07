

<%@page import="JavaBean.MyFileBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统->查看"></s:text></title>
    </head>
    <body bgcolor="gray">
        <hr noshade/>
      <s:div align="center">
      <s:form action="findFileAction" method="post">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/fileManager/fileUp.jsp">上传文件</s:a>
              </td>
              <td width="33%">
                  <s:text name="文件列表"></s:text>
              </td>
              <td width="33%">
                  <s:text name="文件标题:"></s:text>
                  <input type="text" name="title"/>
                  <input type="submit" value="下载"/>
              </td>
          </tr>
      </table>
      </s:form>
      </s:div>
      <hr noshade/>
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <tr>
              <th height="30">文件标题</th>
              <th height="30">文件名字</th>
              <th height="30">文件类型</th>
              <th height="30">文件大小</th>
          </tr>
          <%
            ArrayList file=(ArrayList)session.getAttribute("file");
            if(file==null||file.size()==0){
            %>
            <s:div align="center"><%="您还没有上传文件！"%></s:div>
            <%
            }else{
                for(int i=file.size()-1;i>=0;i--){
                    MyFileBean ff=(MyFileBean)file.get(i);
                    %> 
                   <tr>
                     <td><%=ff.getTitle()%></td>
                     <td><%=ff.getName()%></td>
                     <td><%=ff.getContentType()%></td>
                     <td><%=ff.getSize()%></td>
                   </tr>
                    <%
                }
            }
          %>
        </table>
    </body>
</html>
