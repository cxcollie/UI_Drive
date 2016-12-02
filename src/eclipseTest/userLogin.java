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

public class userLogin extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		
		// connect to server
		try {
			String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
		    Connection connection = null; 
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		    
		    //get parameter
		    String username=request.getParameter("username");
		    HttpSession session = request.getSession(true);
		    String pwd=request.getParameter("pwd");
		    
		    //get connection
		    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
		    if(!connection.isClosed()) {
		    	Statement st= connection.createStatement(); 
		    	
		    	//1. verification
		    	//ResultSet rs=st.executeQuery("select * from UserInfo where username='"+username+"'"); 
		    	ResultSet rs=st.executeQuery("select * from UserInfo where username = '" + username + "'");
		    	boolean userFound = false;
		    	while(rs.next()) {
		    		if(rs.getString("password").equals(pwd)) {
		    			userFound = true;
		    			String userID=rs.getString("id"); 
		    			session.setAttribute("userID",userID);
		    			String url="/userCenter"; //relative url for display jsp page
					    ServletContext sc = getServletContext();
					    RequestDispatcher rd = sc.getRequestDispatcher(url);
					    rd.forward(request, response);
		    		}
		    	} 
		    	
		    	if (!userFound) { 
	    			String url="/Home.jsp"; //relative url for display jsp page
	    			request.setAttribute("loginStatus", "1"); // incorrect password
				    ServletContext sc = getServletContext();
				    RequestDispatcher rd = sc.getRequestDispatcher(url);
				    rd.forward(request, response);
	    		}
		    	
		    	//2. jump to user center
//		    	String url="/userCenter"; //relative url for display jsp page
//			    ServletContext sc = getServletContext();
//			    RequestDispatcher rd = sc.getRequestDispatcher(url);
//			    rd.forward(request, response);
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