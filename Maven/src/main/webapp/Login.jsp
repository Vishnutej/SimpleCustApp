<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
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
    String message=(String)request.getAttribute("message");
	if(message == null)
	{
		message = "";
	}
%>
<form action="insertLogin" method="post" name="frm" validate>
<div id="msg" name="message" class="messageStyle"><%=message %></div>
<input type="hidden" name="btn" />
<table align="center" bgcolor="#99FFCC" border="1" width="70%">
    <tr>
        <td colspan="2" align="center">Login or Sign Up</td>
    </tr>
    <tr>
        <td>User Name </td>
        <td><input id="login_field1" type="text" name="username" maxlength="25" required></td>
    </tr>
    <tr>
        <td>Password </td>
        <td><input type="password" name="password" maxlength="25" required></td>
    </tr>
    </table>
    <br/>
    <div align="center">
        <input type="submit" value="Login" onclick="{document.frm.btn.value=this.value;}">
        <input type="submit" value="Sign Up" onclick="{document.frm.btn.value=this.value;}">
      </div>

</form>
</body>
<script>
/*$(window).bind("pageshow", function() {
    $("#login_field1").val('');
});
document.getElementById('msg').innerHTML=''; */
</script>
</html>