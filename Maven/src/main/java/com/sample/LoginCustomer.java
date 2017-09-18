package com.sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCustomer extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		 String message = "";
	     RequestDispatcher dispatcher = null;
		try {
			String user=request.getParameter("username");
			String pass=request.getParameter("password");
//			SimpleCustDAO.createTables();
			String action = request.getParameter("btn");
			if("Login".equalsIgnoreCase(action))
			{
			 if(SimpleCustDAO.validateUser(user,pass)) {
				Customer cust = SimpleCustDAO.getCustomer(user);
				if(cust!=null)
				{
					request.setAttribute("cust",cust);
					dispatcher=request.getRequestDispatcher("/Welcome.jsp");
				}
				else
				{
					request.setAttribute("uname",user);
					dispatcher=request.getRequestDispatcher("/Customer.jsp");
				}
			}
			else {
				message = "Invalid Credentials or User does not exist!!!";
				request.setAttribute("message",message);
				dispatcher=request.getRequestDispatcher("/Login.jsp");
			}
		  }
			else if("Sign Up".equalsIgnoreCase(action))
			{
				Customer cust = SimpleCustDAO.getCustomer(user);
				if(cust!=null)
				{
					message = "User already exists!!!";
					request.setAttribute("message",message);
					dispatcher=request.getRequestDispatcher("/Login.jsp");
				}
				else
				{
					SimpleCustDAO.signUpUser(user,pass);
					request.setAttribute("uname",user);
					dispatcher=request.getRequestDispatcher("/Customer.jsp");
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			dispatcher=request.getRequestDispatcher("/Login.jsp");
            message = "Error occured while processing!!!";
            request.setAttribute("message", message);
		}
		if(dispatcher!=null)
		{
			dispatcher.forward(request, response);
		}
	}
}
