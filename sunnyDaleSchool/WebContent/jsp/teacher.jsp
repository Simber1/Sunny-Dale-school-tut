<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sunny Dale School: <c:out value="${ teacher.getName() }"></c:out></title>
</head>
<body>

<h1>
<c:out value="${ teacher.getName() }"></c:out>
</h1>  

<p>Moral State: ${ teacher.getMoralStateDescription() }</p> 
<p>Species: ${ teacher.getSpeciesName() }</p>

<c:if test="${ isSlayer }">
	<p>Stakings: ${ teacher.getStakings() } </p>
</c:if>
</body>
</html>