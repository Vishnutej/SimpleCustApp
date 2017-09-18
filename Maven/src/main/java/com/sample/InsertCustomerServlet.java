package com.sample;
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
public class InsertCustomerServlet extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        System.out.println("----- InsertCustomerServlet -----");
        String message = "";
        RequestDispatcher dispatcher = null;
        try {
        	request.setAttribute("message", message);
        // Get the customer value submitted from Customer.jsp page through HttpServletRequest object
//        	String userid = (String)request.getSession().getAttribute("userid");
        	
        	String userid = request.getParameter("userid");
        	System.err.println("USER ID val:"+userid);
            String name=request.getParameter("name");
            String address=request.getParameter("address");
            String mobile=request.getParameter("mobile");
            String emailid=request.getParameter("emailid");
             
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
            Customer customer=new Customer();
            customer.setUserid(userid);
            customer.setName(name);
            customer.setAddress(address);
            customer.setMobile(Long.valueOf(mobile));
            customer.setEmailid(emailid);
             
            SimpleCustDAO.insertCustomer(customer);
            dispatcher=request.getRequestDispatcher("/Welcome.jsp");
            //Set the customer instance into request.Then only the customer object 
            //will be available in the Welcome.jsp page
            request.setAttribute("cust",customer);
           
        }catch(Exception e){
        	e.printStackTrace();
        	dispatcher=request.getRequestDispatcher("/Customer.jsp");
            message = "Error occured while processing!!!";
            request.setAttribute("message", message);
        }
        
        if(dispatcher!=null)
        {
        	 dispatcher.forward(request, response);
        }
         
    }
 
}