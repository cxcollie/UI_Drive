<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>homeTest</title>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata">

      <link rel="stylesheet" href="css_1/style.css">

<style>
p.quote {
	font-size: 1.5em;
	font-style: italic;
}
</style>  
</head>

<body>
  
<!-- Mixins-->
<!-- Pen Title-->
<div class="w3-container w3-grayscale-min" id="us">
  <div class="w3-content">
    <h1 class="w3-center w3-text-grey"><b>UI Drive</b></h1>
  </div>
</div>
<% String loginStatus = (String)request.getAttribute("loginStatus");
  if (loginStatus!= null && loginStatus.equals("1")) { // wrong password
  %>
<p class="w3-center quote">Your username/password is incorrect</p>
<% } else if (loginStatus!= null && loginStatus.equals("2")) { // invalid email
	%>
<p class="w3-center quote">Please provide an Illinois edu email address</p>
<% }  else if (loginStatus!= null && loginStatus.equals("3")) { // successful register%> 
<p class="w3-center quote">Successfully registered!</p>
<% } %>
<!-- <div class="rerun"><a href="">Login</a></div> -->
<div class="container">
  <div class="card"></div>
  <div class="card">
    <h1 class="title">Login</h1>
    <form action="userLogin">
      <div class="input-container">
        <input type="text" name="username" required="required"/>
        <label for="Username">Username</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" name="pwd" required="required"/>
        <label for="Password">Password</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
        <button><span>Go</span></button>
      </div>
    </form>
  </div>
  <div class="card alt">
    <div class="toggle"></div>
    <h1 class="title">Register
      <div class="close"></div>
    </h1>
    <form action="newUserRegister">
      <div class="input-container">
        <input type="text" name="username" required="required"/>
        <label for="Username">Username</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="text" name="email" required="required"/>
        <label for="Email">Email</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" name="pwd" required="required"/>
        <label for="Password">Password</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
        <button><span>Next</span></button>
      </div>
    </form>
  </div>
</div>
  	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="js/index.js"></script>
</body>
<footer class="w3-center w3-padding-large">
<a href="Home.jsp" >UI Drive v1.0</a>
 Developed by <a href="https://www.linkedin.com/in/xiaocongchen" >Xiaocong Chen</a> and 
<a href="https://www.linkedin.com/in/xinzhou-zhao-9a2406103/en" >Xinzhou Zhao</a>.
All pictures credit to GitHub, Inc.
</footer>
</html>