<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UIDrive demo Regjsp</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%
String user=request.getParameter("id");
session.putValue("id",user); 
String username=request.getParameter("username");  
String email=request.getParameter("email"); 
String pwd=request.getParameter("pwd"); 
Class.forName("com.mysql.jdbc.Driver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb",
"uidrivedatabase","woaishuati"); 
Statement st= con.createStatement(); 
ResultSet rs; 
int i=st.executeUpdate("insert into UserInfo values ('"+user+"','"+username+"','"+email+"','"+pwd+"')"); 

out.println("Registered"); 


%>
<a href ="LoginPage.html">Login</a><br/><br/>
<a href="index.html">Home</a>
</body>
</html>