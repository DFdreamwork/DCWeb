<%@page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="DCwork.servlet.bd.ResourceUpdatedBd"%>
<% ResourceUpdatedBd bd = new ResourceUpdatedBd(request); %>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<form name="frmName" action="./resource_update.jsp" >
<div><%= bd.getResourceAsHtml() %></div>
<div>
<%= bd.showResoure() %>
</div>

<input type="submit" value="submit" />
</form>
</body>
</html>