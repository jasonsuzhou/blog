<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<%@ page import="java.lang.Exception"%>  
<!doctype html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>Error Page</title>  
<link rel="icon" href="images/icon/favicon.ico">
</head>  
<body>  
<h1>Error Occurred</h1>  
<%  
Exception e = (Exception)request.getAttribute("exception");  
out.print(e.getMessage());  
%>  
</body>  
</html>