package com.sample;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCustomer extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			String user=request.getParameter("username");
			String pass=request.getParameter("password");
			Customer c=new Customer();
			RequestDispatcher dispatcher;
			if(user.equals(c.getUsername())&&pass.equals(c.getPassword())) {
				dispatcher=request.getRequestDispatcher("/Customer.jsp");
			}
			else {
				dispatcher=request.getRequestDispatcher("/Login.jsp");
			}
			request.setAttribute("uname",user);
            dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
