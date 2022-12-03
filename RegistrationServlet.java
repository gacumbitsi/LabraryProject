package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.DatabaseConnection;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the username & password which is coming from client side (registration.jsp)
    	String username = request.getParameter("username");
    	String password = request.getParameter("psw");
    	String fullname = request.getParameter("fullname");
    	String email = request.getParameter("email");
    	String gender = request.getParameter("gender");
    	
        try {
  
            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();
            
  
            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con.prepareStatement("insert into profile values(?, ?, ?, ?, ?)");
  
            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            st.setString(1, username);
           
            st.setString(2, password);
            
            st.setString(3, fullname);
            
            st.setString(4, email);
            
            st.setString(5, gender);
  
            // Execute the insert command using executeUpdate()
            // to make changes in database
            int x = st.executeUpdate();
            if (x>0) {
            
           
          request.setAttribute("msg", "Congratulation you records have been added to the database!");
		  request.getRequestDispatcher("success.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

}
}
