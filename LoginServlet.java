package com.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.UserDTO;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the username & password which is coming from client side (login.jsp)
		String name=request.getParameter("username");
		String pass=request.getParameter("pswd");
		
		//business logic to communicate with database
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("hiiii");
			
			 try {
				 
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "ujmgac983");
				
				System.out.println("hiiii 88888");
				
				PreparedStatement ps = conn.prepareStatement("select * from profile  where username=? and password=?");
				ps.setString(1,name);
				ps.setString(2,pass);
				
				ResultSet rs=ps.executeQuery();
				
				if(rs.next()) {
					//for valid user
					String username=rs.getString(1);
					String password=rs.getString(2);
					String fullname=rs.getString(3);
					String email=rs.getString(4);
					String gender=rs.getString(5);
					
				
//					request.setAttribute("username", username);
//					request.setAttribute("password", password);
//					request.setAttribute("fullname", fullname);
//					request.setAttribute("email", email);
//					request.setAttribute("gender", gender);
					
					UserDTO userDTO=new UserDTO(username,password,fullname,email,gender);
					request.setAttribute("userDTO", userDTO);
					
					
					request.getRequestDispatcher("congrats.jsp").forward(request, response);
					
			}else {
				request.setAttribute("message", "Invalid user.... or username/password is not correct--Re-Try..");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
					
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			 	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}