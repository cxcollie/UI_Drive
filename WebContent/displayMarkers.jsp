<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import ="javax.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Drive Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
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
    p {line-height: 2}
	p.quote {
	font-size: 1.5em;
	font-style: italic;
	}
	div.quotes {
	margin-top: 50px;
	}
    </style>
  </head>
  <body>
  <div>
<p class="w3-center"><a href="selectDrive.jsp" 
class="w3-btn w3-round w3-padding-large w3-large">Back</a></p>
</div>
  <!-- <a href="selectDrive.jsp">Back</a> -->
    
    <div id="map"></div>
    <script>
      var map;
      function initMap() {
    	  
    	  var uluru = {lat: <%= request.getAttribute("centerX") %>, lng: <%= request.getAttribute("centerY") %>};
          var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 19,
            center: uluru
          });
         
          var contentString = '<div id="content">'+ '</div>';


      var infowindow = new google.maps.InfoWindow({
        content: contentString,
        maxWidth: 200
      });
          
    	  <% ResultSet driveSet = (ResultSet)request.getAttribute("databaseDataList"); 
    	     if (driveSet != null) {
    	       while(driveSet.next()) {%>
        	  	<% if(driveSet.getString("passengerLeft").equals("0")) continue; %>
    			var latLng = new google.maps.LatLng(<%= driveSet.getString("startPositionX") %>,<%= driveSet.getString("startPositionY") %>);
    	        var marker = new google.maps.Marker({
    	          position: latLng,
    	          map: map,
    	          icon: 'convertible.png'
    	        });
    	        
    	        google.maps.event.addListener(marker, 'click', function() {
    	              infowindow.setContent('<div><p class="w3-center quote"> I will leave at :' + "<%= (driveSet.getString("startTime")).substring(0, 20) %>" + 
    	            		  '<br></p>' + '</div>' + '<div><p class="w3-center quote"> message: ' + "<%= driveSet.getString("briefInfo") %>" + '<br></p>' +
    	                '</div>' + '<div><p class="w3-center quote"> So do you want to take it?' + '<A href= "passengerConfirmation?driveID=<%= driveSet.getString("driveID")%>"> Sure </A></p>' +
      	                '</div>');
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
    async defer></script>
  </body>
</html>