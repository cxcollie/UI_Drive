package eclipseTest;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import javax.*;

import javax.sql.*;
import java.sql.*;

public class obtainDriveInfo extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		
		// connect to server
		try {
			String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
		    Connection connection = null; 
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		    
		    //get parameter
		    String user=request.getParameter("id"); 
		    HttpSession session = request.getSession();;
		    session.putValue("id",user); 
		    String username=request.getParameter("username");  
		    String email=request.getParameter("email"); 
		    String pwd=request.getParameter("pwd");
		    
		    //get connection
		    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
		    
		    if(!connection.isClosed()) {
		    	Statement st= connection.createStatement(); 
		    	
		    	//insert to mysql
		    	//String query = "INSERT INTO UserInfo (id, username, email, password) VALUES ('"+user+"','"+username+"','"+email+"','"+pwd+"')";
		    	//st.executeUpdate(query);
		    	
		    	//read from mysql
		    	ResultSet rs = st.executeQuery("SELECT * FROM driveTable WHERE startTime >= '2016-11-03 23:59:59';");
		    	String url="/displayMarkers.jsp"; //relative url for display jsp page
			    ServletContext sc = getServletContext();
			    RequestDispatcher rd = sc.getRequestDispatcher(url);

			    request.setAttribute("databaseDataList", rs);
			    rd.forward(request, response);
			    st.close();
		    }
		    
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