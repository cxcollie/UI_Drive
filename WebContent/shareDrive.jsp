<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Share a Drive</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata">
<style>
body, html {
    height: 100%;
    font-family: "Inconsolata", sans-serif;
}
.menu {
    display: none;
}
form.inputForm {
	margin-top: 10%;
	margin-left: 10%;
	margin-right: 10%;
	margin-bottom: 10%;
}
p.quote{
	font-size: 1em;
	font-style: italic;
}
div.quotes {
	margin-top: 0px;
}
</style>
<body>

<div class="w3-container w3-grayscale-min" id="us">
  <div class="w3-content">
    <h1 class="w3-center w3-text-grey"><b>Share Your Drive</b></h1>
  </div>
</div>

<div class="inputForm w3-half">
    <form class="inputForm w3-center" action="publishDrive" method="get">
	
	<p>Select your start place:
	<select name="startPlace">
  <option value="Gra">Grainger Library</option>
  <option value="ArmoE">308 E Armory</option>
  <option value="Union">Illi Union</option>
  <option value="BookS" > Bookstore</option>
  <option value="CC">Campus Circle</option>
  <option value="ECEB" > ECE Building</option>
	</select>
	</p>
	
	<p>Select your destination:
	<select name="endPlace">
  <option value="Gra">Grainger Library</option>
  <option value="ArmoE">308 E Armory</option>
  <option value="Union">Illi Union</option>
  <option value="BookS" > Bookstore</option>
  <option value="CC">Campus Circle</option>
  <option value="ECEB" > ECE Building</option>
	</select>
	</p>

      <p><input class="w3-input w3-padding-16 w3-border" type="number" placeholder="Max number of passenger" required name="passNum"></p>
      <p><input class="w3-input w3-padding-16 w3-border" type="datetime-local" placeholder="Start time" required name="startTime" value="2016-11-30T20:00"></p>
      <p><input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Message to passengers" name="briefInfo"></p>
      <p><button class="w3-btn w3-padding" type="submit">Share Drive</button></p>
      <div class="quotes w3-margin-right">
      <br>
      
    <p class="quote">"Life is like a free drive, </p>
	<p class="quote">you never know who you're gonna travel with."</p>
	<p class="quote">--Forrest UIDrive Gump</p>
    </div>
    </form>
    
    
   </div>
    
    <div>
<div class="w3-half">
<img src="images/minertocat.png" style="width:100%"/>
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