<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Error Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2{font-family: "Raleway", sans-serif}
body, html {height: 100%}
p {line-height: 2}
.bgimg, .bgimg2 {
    min-height: 100%;
    background-position: center;
    background-size: cover;
}
p.quote {
	font-size: 1.5em;
}
p.quote2 {
	font-size: 1em;
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
    <h1 class="w3-center w3-text-grey"><b>Error Page</b></h1>
  </div>
</div>

<div>

<div class="w3-half quotes">
<p class="w3-center quote">Ooops! We have found some small bugs:</p>
<p class="w3-center quote2"><%= (String)request.getAttribute("errorCode") %> </p>
<br>
<p class="quote2 w3-center">"I'm not in danger. I am the danger."</p>
<p class="quote2 w3-center">--Breaking Bad</p>

</div>

<div class="w3-half" >
<img src="images/heisencat.png" style="width:100%"/>
</div>

</div>

</body>
<footer class="w3-center w3-padding-large">
<a href="Home.jsp" >UI Drive</a>
 Developed by <a href="https://www.linkedin.com/in/xiaocongchen" >Xiaocong Chen</a> and 
<a href="https://www.linkedin.com/in/xinzhou-zhao-9a2406103/en" >Xinzhou Zhao</a>.
All pictures credit to GitHub, Inc.
</footer>
</html>