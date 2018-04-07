<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统"/></title>
    </head>
    <body bgcolor="#CCCCFF">
        <s:form action="loginAction" method="post">
            <table align="center" width="100%">
                <tr>
                    <td align="right" width="50%">
                        <img src="../images/1.jpg" height="80"/>
                    </td>
                    <td align="left" width="50%">
                        <h1>个人信息管理系统</h1>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr align="center" width="100%" size="20" color="green"/>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="center">
                        <image src="../images/2.jpg" height="50%"/>
                    </td>
                    <td width="70%">
                        <table border="5" align="center" bgcolor="#99aadd">
                            <tr>
                                <td>
                                    <s:textfield name="userName" label="登录名" size="16"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:password name="password" label="登录密码" size="18"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="确定"/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="reset" value="清空"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <s:a href="http://localhost:8080/ch13/login/register.jsp">注册</s:a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </s:form>
    </body>
</html>
