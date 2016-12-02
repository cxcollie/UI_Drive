package eclipseTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class passengerConfirmation extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
			
			// connect to server
			try {
				String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
			    Connection connection = null; 
			    Class.forName("com.mysql.jdbc.Driver").newInstance();
			    HttpSession session = request.getSession(true);
			    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
			    if(!connection.isClosed()) {
			    	Statement st= connection.createStatement(); 
			    	
			    	//1. update user info
			    	String userID = (String)session.getAttribute("userID");
			    	String driveID = (String) request.getParameter("driveID");
			    	ResultSet rs=st.executeQuery("SELECT * FROM driveTable WHERE driveID ="+driveID);
			    	String verificationCode = "abc";
			    	if(rs.next()){
			    		String startTime = rs.getString("startTime");
			    		request.setAttribute("startTime",startTime);
			    		
			    		String startPlaceAbbre = rs.getString("startPlace");
		        		if (startPlaceAbbre.equals("Gra")) request.setAttribute("startPlace", "Grainger Library");
		        		else if (startPlaceAbbre.equals("ArmoE")) request.setAttribute("startPlace", "308 E Armory");
		        		else if (startPlaceAbbre.equals("Union")) request.setAttribute("startPlace", "Illini Union");
		        		else if (startPlaceAbbre.equals("BookS")) request.setAttribute("startPlace", "Bookstore");
		        		else if (startPlaceAbbre.equals("CC")) request.setAttribute("startPlace", "Campus Circle");
		        		else if (startPlaceAbbre.equals("ECEB")) request.setAttribute("startPlace", "ECE Building");
		        		
		        		String endPlaceAbbre = rs.getString("endPlace");
		        		if (endPlaceAbbre.equals("Gra")) request.setAttribute("endPlace", "Grainger Library");
		        		else if (endPlaceAbbre.equals("ArmoE")) request.setAttribute("endPlace", "308 E Armory");
		        		else if (endPlaceAbbre.equals("Union")) request.setAttribute("endPlace", "Illini Union");
		        		else if (endPlaceAbbre.equals("BookS")) request.setAttribute("endPlace", "Bookstore");
		        		else if (endPlaceAbbre.equals("CC")) request.setAttribute("endPlace", "Campus Circle");
		        		else if (endPlaceAbbre.equals("ECEB")) request.setAttribute("endPlace", "ECE Building");
			    		
				    	String executeStr = "UPDATE UserInfo SET driveID ='" + driveID + "'WHERE id ='" + userID +"'";
				    	String executeStr1 = "UPDATE UserInfo SET eventTime ='" + startTime + "'WHERE id ='" + userID +"'";

				    	Statement st2= connection.createStatement();
				    	st2.executeUpdate(executeStr);
				    	st2.executeUpdate(executeStr1);
				    	//2. set passengerLeft
				    	int PassengerLeft = 0;
				    	PassengerLeft = rs.getInt("passengerLeft");
				    	
				    	//PassengerLeft = Integer.parseInt(rs.getString("passengerLeft"));
				    	PassengerLeft--;
				    	String query = "update driveTable set passengerLeft = " +PassengerLeft+ " where driveID = " + driveID;
				    	st2.executeUpdate(query);
				    	//3. set output
				    	verificationCode = rs.getString("verificationCode");
				    	
				    	// 4. update to UserInfo
				    	Statement st3 = connection.createStatement(); 
				    	String executeStr2 = "UPDATE UserInfo SET lockedAs = 2 WHERE id ='" + userID +"'";
				    	st3.executeUpdate(executeStr2);
			    	}
			    	
			    	request.setAttribute("veriCode",verificationCode);
			    	request.setAttribute("userID", userID);
			    	
			    	String url="/confirmationPage.jsp"; //relative url for display jsp page
				    ServletContext sc = getServletContext();
				    RequestDispatcher rd = sc.getRequestDispatcher(url);

				    rd.forward(request, response);
			    }   
			    connection.close();
			}catch(Exception ex){
				/*StringBuilder page = new StringBuilder();
				page.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" "
						+ "\"http://www.w3.org/TR/html4/loose.dtd\">");
				page.append("<HTML><HEAD><TITLE>Multi Form</TITLE></HEAD>");
				page.append("<BODY>");
				page.append("Oh no... Something gets wrong, I guess it's because "+ex);
				page.append("the problem occurs at line" + ex.getStackTrace()[0].getLineNumber());
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
