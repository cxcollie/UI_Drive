package eclipseTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class publishDrive extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
			// also we need to check the user status first, in case of double publish
		
			// connect to server
			try {
				String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
			    Connection connection = null; 
			    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
			    if(!connection.isClosed()) {
			    	// 1.update to driveTable
			    	String maxPassNum=request.getParameter("passNum");
			    	String briefInfo=request.getParameter("briefInfo");
			    	if (briefInfo == null) briefInfo = ""; // in case it is left blank
			    	HttpSession session = request.getSession(true);
			    	String userID=(String)session.getAttribute("userID");
			    	String timeArr = request.getParameter("startTime").replace("T", " ");
			    	String startTimeExp = "'" + timeArr + "'";
			    	
			    	String startPlace = request.getParameter("startPlace");
			    	String startPosX, startPosY;
			    	Random rnd = new Random(); // generate random value
			    	if (startPlace.equals("Gra")) { // Grainger
			    		startPosX = Double.toString(40.112746 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.226960 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "Grainger Library");
			    	} else if (startPlace.equals("ArmoE")) { // 308 E Armory
			    		startPosX = Double.toString(40.105918 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.234357 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "308 E Armory");
			    	} else if (startPlace.equals("Union")) { // Illini Union
			    		startPosX = Double.toString(40.109862 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.227206 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "Illini Union");
			    	} else if (startPlace.equals("BookS")) { // Bookstore
			    		startPosX = Double.toString(40.108316 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.228987 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "Bookstore");
			    	} else if (startPlace.equals("CC")) { // Campus Circle
			    		startPosX = Double.toString(40.116526 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.221927 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "Campus Circle");
			    	} else if (startPlace.equals("ECEB")) { // ECE Building
			    		startPosX = Double.toString(40.114827 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.228820 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "ECE Building");
			    	} else {
			    		startPosX = Double.toString(40.105918 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.234357 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("startPlace", "308 E Armory");
			    	}
			    	
			    	String endPlace = request.getParameter("endPlace");
			    	String endPosX, endPosY;
			    	if (endPlace.equals("Gra")) { // Grainger
			    		endPosX = Double.toString(40.112746 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.226960 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "Grainger Library");
			    	} else if (endPlace.equals("ArmoE")) { // 308 E Armory
			    		endPosX = Double.toString(40.105918 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.234357 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "308 E Armory");
			    	} else if (endPlace.equals("Union")) { // Illini Union
			    		endPosX = Double.toString(40.109862 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.227206 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "Illini Union");
			    	} else if (endPlace.equals("BookS")) { // Bookstore
			    		endPosX = Double.toString(40.108316 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.228987 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "Bookstore");
			    	} else if (endPlace.equals("CC")) { // Campus Circle
			    		endPosX = Double.toString(40.116526 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.221927 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "Campus Circle");
			    	} else if (endPlace.equals("ECEB")) { // ECE Building
			    		endPosX = Double.toString(40.114827 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.228820 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "ECE Building");
			    	} else {
			    		endPosX = Double.toString(40.105918 + rnd.nextDouble() / 10000 - 0.00005);
			    		endPosY = Double.toString(-88.234357 + rnd.nextDouble() / 10000 - 0.00005);
			    		request.setAttribute("endPlace", "308 E Armory");
			    	}
 	
			    	StringBuilder executeStr = new StringBuilder();
			    	executeStr.append("INSERT INTO driveTable (startTime, startPositionX, "
			    			+ "startPositionY, endPositionX, endPositionY, passengerNumber, passengerLeft, "
			    			+ "briefInfo, driverID, verificationCode, endPlace, startPlace) VALUES (");
			    	
			    	executeStr.append(startTimeExp + ", ");
			        executeStr.append(startPosX + ", "); // startX (change it to certain points)
			        executeStr.append(startPosY + ", "); // startY
			        executeStr.append(endPosX + ", "); // endX
			        executeStr.append(endPosY + ", "); // endY
			        executeStr.append(maxPassNum + ", "); // max passenger number
			        executeStr.append(maxPassNum + ", '"); // seats left
			        executeStr.append(briefInfo + "', "); // brief info
			        executeStr.append(userID + ", "); // driverID
			        
			        StringBuilder randVeriCode = new StringBuilder();
			        for (int i = 0; i < 6; i++) {
			        	randVeriCode.append(rnd.nextInt(10));
			        }
			        executeStr.append("'" + randVeriCode.toString() + "', "); // verification code
			        executeStr.append("'" + endPlace + "', "); // end place
			        executeStr.append("'" + startPlace + "');"); // start place
			        
			        Statement st= connection.createStatement(); 
			    	st.executeUpdate(executeStr.toString());
			    	
			    	// 1.5 obtain driveID
			    	StringBuilder execuStrDriveID = new StringBuilder();
			    	execuStrDriveID.append("SELECT * FROM driveTable WHERE ");
			    	execuStrDriveID.append("verificationCode = '" + randVeriCode + "'");
			    	execuStrDriveID.append(" AND driverID = " + userID + ";");
			    	
			    	ResultSet rsDriveID = st.executeQuery(execuStrDriveID.toString());
			    	rsDriveID.next();
			    	String currDriveID = rsDriveID.getString("driveID");
			    	
			    	// 2.update to UserInfo
			    	StringBuilder executeStr2 = new StringBuilder();
			    	executeStr2.append("UPDATE UserInfo SET lockedAs = 1, ");
			    	executeStr2.append("driveID = " + currDriveID + ", ");
			    	executeStr2.append("eventTime = " + startTimeExp);
			    	executeStr2.append(" WHERE id = " + userID + ";");
			    	
			    	Statement st2= connection.createStatement(); 
			    	st2.executeUpdate(executeStr2.toString());
			    	
			    	// 3.forward to verify page
			    	String url="/drivePubConfirm.jsp"; //relative url for display jsp page
				    ServletContext sc = getServletContext();
				    RequestDispatcher rd = sc.getRequestDispatcher(url);
				    
				    request.setAttribute("veriCode", randVeriCode.toString());
				    request.setAttribute("startTime", startTimeExp.substring(1,  startTimeExp.length() - 1));
				    
				    st.close();
				    st2.close();
				    connection.close();
				    rd.forward(request, response);
			    } // end of if(connect)
			    connection.close();
			}catch(Exception ex){
				
				/*StringBuilder page = new StringBuilder();
				page.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" "
						+ "\"http://www.w3.org/TR/html4/loose.dtd\">");
				page.append("<HTML><HEAD><TITLE>Multi Form</TITLE></HEAD>");
				page.append("<BODY>");
				page.append("Oh no... Something gets wrong, I guess it's because "+ex);
				page.append("</BODY></HTML>");

			    response.setContentType("text/html");
			    PrintWriter writer = response.getWriter();
			    writer.println(page.toString());
			    writer.close();*/
				
				String url="/errorPage.jsp"; //relative url for display jsp page
			    ServletContext sc = getServletContext();
			    RequestDispatcher rd = sc.getRequestDispatcher(url);
			    String errorStr = ""+ex;
			    request.setAttribute("errorCode", errorStr); // incorrect password
			    rd.forward(request, response);
			} 
		    
			} 
}
