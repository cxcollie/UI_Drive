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

public class newUserRegister extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		
		// connect to server
		try {
			String connectionURL = "jdbc:mysql://aar02ikswe8nxu.cj5kik5rd5qf.us-east-1.rds.amazonaws.com:3306/ebdb";
		    Connection connection = null; 
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		    
		    //get parameter
		    //String user=request.getParameter("userID"); 
		    HttpSession session = request.getSession(true);
		    //session.setAttribute("userID",user);
		    //session.setAttribute("id", userID);
		    String username=request.getParameter("username");  
		    String email=request.getParameter("email"); 
		    
		    if (email.length() <=13 || !email.substring(email.length() - 13).startsWith("@illinois.edu")){
		    	
		    	String url="/Home.jsp"; //relative url for display jsp page
			    ServletContext sc = getServletContext();
			    RequestDispatcher rd = sc.getRequestDispatcher(url);
			    request.setAttribute("loginStatus", "2"); // illegal email address
			    rd.forward(request, response);
		    }
		    else{
		    	
		    String pwd=request.getParameter("pwd");
		    //get connection
		    connection = DriverManager.getConnection(connectionURL, "uidrivedatabase", "woaishuati");
		    
		    if(!connection.isClosed()) {
		    	Statement st= connection.createStatement(); 
		    	
		    	//insert to mysql
		    	//String query = "INSERT INTO UserInfo (id, username, email, password) VALUES ('"+user+"','"+username+"','"+email+"','"+pwd+"')";
		    	String query = "INSERT INTO UserInfo (username, email, password) VALUES ('"+username+"','"+email+"','"+pwd+"')";
		    	st.executeUpdate(query);
		    	String query1 = "SELECT * FROM UserInfo where username = '" + username + "'";
		    	ResultSet rs = st.executeQuery(query1);
		    	if(rs.next()){
		    		
		    		session.setAttribute("userID", rs.getString("id"));
		    	}

		    	String url="/Home.jsp"; //relative url for display jsp page
			    ServletContext sc = getServletContext();
			    request.setAttribute("loginStatus", "3"); // successful register
			    RequestDispatcher rd = sc.getRequestDispatcher(url);
			    rd.forward(request, response);
		    }
		    connection.close();
		   }
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