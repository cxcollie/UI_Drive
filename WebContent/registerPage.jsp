<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import ="javax.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Drive Map</title>
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>
  <body>
 	<form class="login" action="newUserRegister">			
			 <h1>UI Drive</h1>
			Id :<input type="text" name="userID" class="login-input" placeholder="userID"/>
			UserName :<input type="text" name="username" class="login-input" placeholder="username"/>
			password :<input type="password" name="pwd" class="login-input" placeholder="password"/>
			Email :<input type="text" name="email" class="login-input" placeholder="Email"/><br/>
			<input type="submit" value="submit" class="login-submit">						
	</form> 
  
    <%-- <div id="map"></div>
    <script>
      var map;
      function initMap() {
    	  var uluru = {lat: 40.112746, lng: -88.226960};
          var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 7,
            center: uluru
          });
          
          

      var infowindow = new google.maps.InfoWindow({
        content: contentString,
        maxWidth: 200
      });
          
    	  <% ResultSet driveSet = (ResultSet)request.getAttribute("databaseDataList"); 
    	     if (driveSet != null) {
    	       while(driveSet.next()) {%>
        	  		
    			var latLng = new google.maps.LatLng(<%= driveSet.getString("startPositionX") %>,<%= driveSet.getString("startPositionY") %>);
    	        var marker = new google.maps.Marker({
    	          position: latLng,
    	          map: map
    	        });
    	        
    	        google.maps.event.addListener(marker, 'click', function() {
    	              infowindow.setContent('<div><p> This drive starts at x:' + <%= driveSet.getString("startPositionX") %> + 
    	            		  '<br>' + ' y: ' + <%= driveSet.getString("startPositionY") %> + '<br></p>' +
    	                '</div>' + '<div><p> So do you want to take it?' + '<A href= index.jsp> Sure </A></p>' +
      	                '</div>' + + '<input type="submit" value="submit">');
    	              infowindow.setPosition(latLng);
    	              infowindow.open(map, this);
    	              
    	            });
    				<%
    				  }
    	        }
    				%>
      }

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA7yPFJEFkDEaI1_LhnnoS_7T3c9CH1k5M&callback=initMap"
    async defer></script> --%>
  </body>
</html>