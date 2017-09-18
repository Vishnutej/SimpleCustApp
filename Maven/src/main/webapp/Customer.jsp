<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
<style>
.messageStyle{
	margin-left: 50%;
    padding-bottom: 10px;
    font-weight: bold;
    color: red;
}
</style>
</head>
<body>
<%
    String userId=(String)request.getAttribute("uname");
	String message=(String)request.getAttribute("message");
	if(message == null)
	{
		message = "";
	}
%>
<form action="insertCustomer" method="post">
<div id="msg" name="message" class="messageStyle"><%=message%></div>
<input type="hidden" name="userid" value="<%=userId %>">
<table align="center" bgcolor="#99FFCC" border="1" width="70%">
    <tr>
        <td colspan="2" align="center">Customer Details </td>
    </tr>
    <tr>
       <td>User Id</td>
       <td><%=userId%></td>
    </tr>
    <tr>
        <td>Name </td>
        <td><input id="cust_field1" type="text" name="name" maxlength="25" pattern="[a-zA-Z\s]+" placeholder="Firstname LastName" required></td>
    </tr>
    <tr>
        <td>Address </td>
        <td><input id="cust_field2" type="text" name="address" maxlength="40" required></td>
    </tr>
    <tr>
        <td>Mobile </td>
        <td><input id="cust_field3" type="text" pattern="[0-9]{10}" name="mobile" placeholder="10 digit mobile number" required></td>
    </tr>
    <tr>
        <td>EmailId </td>
        <td><input id="cust_field4" type="email" name="emailid" maxlength="30" placeholder="abc@xyz.com" required></td>
    </tr> 
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Submit"></td>
    </tr> 
</table>
</form>
</body>
<script>
/*$(window).bind("pageshow", function() {
    $("#cust_field1").val('');
    $("#cust_field2").val('');
    $("#cust_field3").val('');
    $("#cust_field4").val('');
});*/
</script>
</html>
