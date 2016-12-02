<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>User Center</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2{font-family: "Raleway", sans-serif}
body, html {height: 100%}
p {line-height: 2}
p.quote {
	font-size: 1.5em;
	font-style: italic;
}
div.quotes {
	margin-top: 50px;
}
</style>
<body>

<!-- Welcome title -->
<div class="w3-container w3-grayscale-min" id="us">
  <div class="w3-content">
    <h1 class="w3-center w3-text-grey"><b>Welcome back, <%= (String)request.getAttribute("userNameStr") %>!</b></h1>
  </div>
</div>

<% String status = (String)request.getAttribute("lockedStatus");
  if (status.equals("0")) { // not locked
  %>
<div>


<div class="w3-half quotes">
<p class="w3-center quote">"My master, do you want to </p>
<p class="w3-center"><a href="shareDrive.jsp" class="w3-btn w3-round w3-padding-large w3-large">Share a Drive</a></p>
<p class="w3-center"><a href="selectDrive.jsp" class="w3-btn w3-round w3-padding-large w3-large">Search a Drive</a></p>
<p class="w3-center quote">May the force be with you."</p> <br>

<div>
<p class="w3-center"><a href="Home.jsp" 
class="w3-btn w3-round w3-padding-large w3-large">Home</a></p>
</div>
</div>

<div class="w3-half">
<img src="images/githubStarWar.jpg" style="width:100%"/>
</div>
</div>


<% } else if (status.equals("1")){ // drive %>
<div>
<div class="w3-half">
<img src="images/githubStarWar.jpg" style="width:100%"/>
</div>

<div class="w3-half quotes">
<p class="w3-center quote">"My master, your shared drive starts at <%= (String)request.getAttribute("eventTime") %>. </p>
<p class="w3-center quote">Your verification code is <%= (String)request.getAttribute("veriCode") %>.</p>
<p class="w3-center quote">Your start place is <%= (String)request.getAttribute("startPlace") %>.</p>
<p class="w3-center quote">Your destination is <%= (String)request.getAttribute("endPlace") %>.</p>
<p class="w3-center quote"><%= request.getAttribute("passNum") %> passenger(s) will take your drive.</p>
<p class="w3-center quote">May the force be with you."</p> <br>

<div>
<p class="w3-center"><a href="Home.jsp" 
class="w3-btn w3-round w3-padding-large w3-large">Home</a></p>
</div>
</div>
</div>

<% } else { // passenger %>
<div>
<div class="w3-half">
<img src="images/githubStarWar.jpg" style="width:100%"/>
</div>

<div class="w3-half quotes">
<p class="w3-center quote">"My master, you booked a drive starting at <%= (String)request.getAttribute("eventTime") %>.</p>
<p class="w3-center quote">Your verification code is <%= (String)request.getAttribute("veriCode") %>.</p>
<p class="w3-center quote">Your start place is <%= (String)request.getAttribute("startPlace") %>.</p>
<p class="w3-center quote">Your destination is <%= (String)request.getAttribute("endPlace") %>.</p>
<p class="w3-center quote">May the force be with you."</p> <br>

<div>
<p class="w3-center"><a href="Home.jsp" 
class="w3-btn w3-round w3-padding-large w3-large">Home</a></p>
</div>
</div>
</div>
<% } %>
</body>

<footer class="w3-center w3-padding-large">
<a href="Home.jsp" >UI Drive</a>
 Developed by <a href="https://www.linkedin.com/in/xiaocongchen" >Xiaocong Chen</a> and 
<a href="https://www.linkedin.com/in/xinzhou-zhao-9a2406103/en" >Xinzhou Zhao</a>.
All pictures credit to GitHub, Inc.
</footer>
</html>