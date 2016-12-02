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

public class selectDrive extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
			// also we need to check the user status first, in case of double publish
		
			// connect to server
			try {
				String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
			    Connection connection = null; 
			    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			    HttpSession session = request.getSession(true);
			    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
			    if(!connection.isClosed()) {
			    	//0.delete old drives
			    	Statement st= connection.createStatement(); 
			    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        Calendar cal = Calendar.getInstance();
			        String currTime = formatter.format(cal.getTime());
			        st.executeUpdate("DELETE FROM driveTable WHERE startTime < '" + currTime + "'");
			    	
			    	// 1.take from drive table
			    	String endPlace = request.getParameter("endPlace");
			    	String startPlace = request.getParameter("startPlace");
			    	String startPosX, startPosY;
			    	Random rnd = new Random(); // generate random value
			    	if (startPlace.equals("Gra")) { // Grainger
			    		startPosX = Double.toString(40.112746 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.226960 + rnd.nextDouble() / 10000 - 0.00005);
			    	} else if (startPlace.equals("ArmoE")) { // 308 E Armory
			    		startPosX = Double.toString(40.105918 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.234357 + rnd.nextDouble() / 10000 - 0.00005);
			    	} else if (startPlace.equals("Union")) { // Illini Union
			    		startPosX = Double.toString(40.109862 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.227206 + rnd.nextDouble() / 10000 - 0.00005);
			    	} else if (startPlace.equals("BookS")) { // Bookstore
			    		startPosX = Double.toString(40.108316 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.228987 + rnd.nextDouble() / 10000 - 0.00005);
			    	} else if (startPlace.equals("CC")) { // Campus Circle
			    		startPosX = Double.toString(40.116526 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.221927 + rnd.nextDouble() / 10000 - 0.00005);
			    	} else if (startPlace.equals("ECEB")) { // ECE Building
			    		startPosX = Double.toString(40.114827 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.228820 + rnd.nextDouble() / 10000 - 0.00005);
			    	} else {
			    		startPosX = Double.toString(40.105918 + rnd.nextDouble() / 10000 - 0.00005);
			    		startPosY = Double.toString(-88.234357 + rnd.nextDouble() / 10000 - 0.00005);
			    	}
			    	request.setAttribute("centerX", startPosX);
			    	request.setAttribute("centerY", startPosY);
			    	
			    	String startTime = request.getParameter("startTime");
			    	
			    	StringBuilder execuStrMarkers = new StringBuilder();
			    	execuStrMarkers.append("SELECT * FROM driveTable WHERE endPlace = '" +endPlace+"'");
			    	String timeLimit = request.getParameter("timeLimit");
			    	if(timeLimit.equals("1")) execuStrMarkers.append(" AND startTime <= '" + startTime + "'");
			    	else if(timeLimit.equals("2")) execuStrMarkers.append(" AND startTime >= '" + startTime + "'");
			    	ResultSet rs = st.executeQuery(execuStrMarkers.toString());
			    	
			    	// 2.forward to select driver page(displayMarker)
			    	String url="/displayMarkers.jsp"; //relative url for display jsp page
			    	//String url="/displayMarkers.jsp"; //relative url for display jsp page
				    ServletContext sc = getServletContext();
				    RequestDispatcher rd = sc.getRequestDispatcher(url);
				    
				    request.setAttribute("databaseDataList", rs);
				    //st.close();
				    //st2.close();
				    //connection.close();
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