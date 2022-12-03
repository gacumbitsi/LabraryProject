package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.UserDTO;


@WebServlet("/getAllRecords")
public class GetAllRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the username & password which is coming from client side (login.jsp)
		//String name=request.getParameter("username");
		//String pass=request.getParameter("pswd");
		
		//business logic to communicate with database
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			 try {
				 
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "ujmgac983");
				
				
				PreparedStatement ps = conn.prepareStatement("select * from profile");
				
				
				List<UserDTO> listOfRecords=new ArrayList<>(); //empty arrayList
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					//for valid user
					String username=rs.getString(1);
					String password=rs.getString(2);
					String fullname=rs.getString(3);
					String email=rs.getString(4);
					String gender=rs.getString(5);
					
				
					//request.setAttribute("username", username);
					//request.setAttribute("password", password);
					//request.setAttribute("fullname", fullname);
					//request.setAttribute("email", email);
					//request.setAttribute("gender", gender);
					
					UserDTO userDTO=new UserDTO(username,password,fullname,email,gender);
					listOfRecords.add(userDTO); //listOfrecords is a collection of number of objects 
				}
				
				
					request.setAttribute("listOfRecords", listOfRecords);
					
					//request.getRequestDispatcher("congratulation.jsp").forward(request, response);
					//request.getRequestDispatcher("congrats.jsp").forward(request, response);
					request.getRequestDispatcher("getAll.jsp").forward(request, response);
				
				
				
				
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
