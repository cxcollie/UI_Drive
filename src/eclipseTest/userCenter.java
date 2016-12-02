package eclipseTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userCenter extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
			
			// connect to server
			try {
				String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
			    Connection connection = null; 
			    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
			    if(!connection.isClosed()) {
			    	Statement st= connection.createStatement(); 
			    	
			    	HttpSession session = request.getSession(true);
			    	String userID=request.getParameter("userID");
			    	if (userID != null) session.setAttribute("userID", userID);
			    	else userID = (String)session.getAttribute("userID");
			    	
			    	ResultSet rs=st.executeQuery("SELECT * FROM UserInfo WHERE id = " + userID);
			    	String url="/userCenterDisplay.jsp"; //relative url for display jsp page
				    ServletContext sc = getServletContext();
				    RequestDispatcher rd = sc.getRequestDispatcher(url);
				    
				    // check whether this user is locked by event
				    rs.next();
				    String lockedStatus = rs.getString("lockedAs");
				    request.setAttribute("userNameStr", rs.getString("username"));
				    
				    if (lockedStatus.equals("0")) { // not locked
				    	// do nothing
				    } else {
				    	// check whether we can unlock this user
				    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        Calendar cal = Calendar.getInstance();
				        String currTime = formatter.format(cal.getTime());
				        
				        String eventTime = rs.getString("eventTime");
				        
				        // update status if needed
				        if (currTime.compareTo(eventTime) > 0) { // modify status to 0
				        	st.executeUpdate("UPDATE UserInfo SET lockedAs = 0 WHERE id = " + userID);
				        	lockedStatus = "0";
				        } else { // keep status unchanged
				        	request.setAttribute("eventTime", eventTime.substring(0, eventTime.length() - 5));
				        	
				        	Statement st2= connection.createStatement();
				        	String driveID = rs.getString("driveID");
				        	ResultSet driveRS=st2.executeQuery("SELECT * FROM driveTable WHERE driveID = " + driveID);
			        		if (driveRS.next())
				        	request.setAttribute("veriCode", driveRS.getString("verificationCode"));
			        		
			        		String startPlaceAbbre = driveRS.getString("startPlace");
			        		if (startPlaceAbbre.equals("Gra")) request.setAttribute("startPlace", "Grainger Library");
			        		else if (startPlaceAbbre.equals("ArmoE")) request.setAttribute("startPlace", "308 E Armory");
			        		else if (startPlaceAbbre.equals("Union")) request.setAttribute("startPlace", "Illini Union");
			        		else if (startPlaceAbbre.equals("BookS")) request.setAttribute("startPlace", "Bookstore");
			        		else if (startPlaceAbbre.equals("CC")) request.setAttribute("startPlace", "Campus Circle");
			        		else if (startPlaceAbbre.equals("ECEB")) request.setAttribute("startPlace", "ECE Building");
			        		
			        		String endPlaceAbbre = driveRS.getString("endPlace");
			        		if (endPlaceAbbre.equals("Gra")) request.setAttribute("endPlace", "Grainger Library");
			        		else if (endPlaceAbbre.equals("ArmoE")) request.setAttribute("endPlace", "308 E Armory");
			        		else if (endPlaceAbbre.equals("Union")) request.setAttribute("endPlace", "Illini Union");
			        		else if (endPlaceAbbre.equals("BookS")) request.setAttribute("endPlace", "Bookstore");
			        		else if (endPlaceAbbre.equals("CC")) request.setAttribute("endPlace", "Campus Circle");
			        		else if (endPlaceAbbre.equals("ECEB")) request.setAttribute("endPlace", "ECE Building");
			        		
				        	if (lockedStatus.equals("1")) { // driver
				        		int passNum = driveRS.getInt("passengerNumber") - driveRS.getInt("passengerLeft");
				        		request.setAttribute("passNum", passNum);     		
				        	} else { // passenger
				        		
				        	}
				        	//st2.close(); 
				        }
				    } // end of if
				    
				    request.setAttribute("lockedStatus", lockedStatus);
				    //st.close();
				    rd.forward(request, response);
			    } // end of if(connect)
			    connection.close();
			} catch(Exception ex){
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
