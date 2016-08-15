<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sunny Dale School: Add Teache</title>
</head>
<body>
<h1>Sunny Dale School: Add Teacher</h1> 
<form action="/sunnyDaleSchool/addTeacher" method="post">   
	<div>    
		<label for="name">Full Name</label>     
		<input type="text" name="name" id="name" value="" />   
	</div>   
	<button type="submit" name="submit" value="submitvalue">Submit</button>  
</form>
</body>
</html>