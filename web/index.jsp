<%--
  Created by IntelliJ IDEA.
  User: Mryang
  Date: 2019/11/28
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<a href="${pageContext.request.contextPath}/categoryServlet?method=toAddCategory">添加分类</a>
<a href="${pageContext.request.contextPath}/bookServlet?method=toAddBook">添加图书</a>
<a href="${pageContext.request.contextPath}/bookServlet?method=bookList&pageNum=1">点击查询所有图书</a>
  </body>
</html>
