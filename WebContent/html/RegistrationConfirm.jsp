<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
    <%@ page import="com.tcs.ilp.lms.bean.MemberBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	
<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />	
<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	
<link rel='stylesheet' type='text/css' href='./css/styles.css' />	
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>	
<script type='text/javascript' src='./js/menu_jquery.js'></script>


<script type="text/javascript" src="./js/jquery.js"></script>
<link rel="stylesheet" href="./css/signin.css"></link>
<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>
<link rel="icon" href="http://designshack.net/favicon.ico"/>
<link rel="stylesheet" href="./css/css.css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/js.js"></script>

<script type="text/javascript">
$(document).ready(function () {
$('.active-links').click(function () {
        if ($('#signin-dropdown').is(":visible")) {
            $('#signin-dropdown').hide(1000);
			$('#session').removeClass('active');
        } else {
            $('#signin-dropdown').show(1000);

			$('#session').addClass('active');
        }
		return false;
    });
		$('#signin-dropdown').click(function(e) {
        e.stopPropagation();
    });
    $(document).click(function() {
        $('#signin-dropdown').hide();
		$('#session').removeClass('active');
    });
});
</script>

<title>Registration Form</title>

<link rel="stylesheet" type="text/css" href="./css/SignUp.css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="./js/SignUp.js"></script>

<script type="text/javascript">     
window.history.forward();     
function noBack() { window.history.forward(); } 
</script>

</head>


<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">

	<div id="top-stuff">
	
		<div id="top-bar-out">
    	
			<div id="container">
    	
				<div id="topnav">
        
					<div class="active-links">
            
						<div id="session">
            
							<a id="signin-link" href="#">
            
            
							<strong>Sign in/Register</strong>
</a>
            
						</div>
            	
						<div id="signin-dropdown">
        		
							<form method="post" class="signin" action="Guest1">
                
							<fieldset class="textbox">
            	
								<label class="username"><span>UserID</span><input id="userId" name="userId" value="" type="text"/>
</label>               
                		
								<label class="password">
<span>Password</span><input id="password" name="password" value="" type="password"/>
</label>
                						
							</fieldset>

                
                			<button class="submit button" type="submit">Sign in</button></br>
							


<a class="forgot" href="SignUp.html">Not a Member? Register Here</a>          
						</form>
         		
						</div>
        		
					</div>
               	
				</div>
    	
			</div>
    
		</div>	

	</div>

	<div id="header" name="headerOne" style="width: 25%; height:140px; background-image: url(./images/header.jpg); color: white; line-height: 100px;float: left">
	</div>

	<div id="header" name="headerTwo" style="width: 55%; height:140px; background-image: url(./images/wood.jpg); color: white; line-height: 100px;float: left">
	<center><b>Library Management System</b></center>
	</div>

	<div id="header" name="headerThree" style="width: 20%; height:140px; background-image: url(./images/wood.jpg); color: white; line-height: 100px;float: left">
	</div>

	
<div id='cssmenu' style="width: 20%; float:left; height:410px; background-image: url(./images/wood.jpg)">
	
<ul>
   
		<li class='last'><a href='GuestHome.html'><span>Home</span></a></li>
   
		<li class='last'><a href='GuestAbout.html'><span>About Us</span></a>
</li>       
		<li class='last'><a href='GuestMembershipPolicies.html'><span>Membership Policies</span></a>
</li>      
		<li class='last'><a href='GuestGallery.html'><span>Gallery</span></a>
</li>        
		<li class='last'><a href='GuestContactUs.html'><span>Contact Us</span></a>
</li>        
	
</ul>
	
</div>
<% 
MemberBean memberBean = new MemberBean(); 
memberBean = (MemberBean) request.getAttribute("member");
%>
<div id="content" style="float: left; width:60%; background-color:black; height: 410px;">
	<form action="MemberServlet1" method="post">
	<fieldset><legend style="color: white;">Confirm Registration</legend>
	<center>
	<table style="color:white; text-align:left;">
		<tr>
			<td>
				User ID : 
			</td>
			<td>
				<%=memberBean.getUserId()%>
			</td>
		</tr>
		<tr>
			<td>
				First Name : 
			</td>
			<td>
				<%=memberBean.getFirstName()%>
			</td>
		</tr>
		<tr>
			<td>
				Last Name : 
			</td>
			<td>
				<%=memberBean.getLastName()%>
			</td>
		</tr>
		<tr>
			<td>
				Member Type : 
			</td>
			<td>
				<%=memberBean.getMemberType()%>
			</td>
		</tr>
		<tr>
			<td>
				Identity ID : 
			</td>
			<td>
				<%=memberBean.getIdentityId()%>
			</td>
		</tr>
		<tr>
			<td>
				Contact No : 
			</td>
			<td>
				<%=memberBean.getContact()%>
			</td>
		</tr>
		<tr>
			<td>
				Email : 
			</td>
			<td>
				<%=memberBean.getEmail()%>
			</td>
		</tr>
		<tr>
			<td>
				Address : 
			</td>
			<td>
				<%=memberBean.getAddress()%>
			</td>
		</tr>
		<tr>
			<td>		
			<input type="submit" name="submit" value="Confirm"></input>
			<input type="hidden" name="userId" value="<%=memberBean.getUserId()%>"></input>
			<input type="hidden" name="password" value="<%=memberBean.getPassword()%>"></input>
			<input type="hidden" name="firstName" value="<%=memberBean.getFirstName()%>"></input>
			<input type="hidden" name="lastName" value="<%=memberBean.getLastName()%>"></input>
			<input type="hidden" name="contactNo" value="<%=memberBean.getContact()%>"></input>
			<input type="hidden" name="IdentityId" value="<%=memberBean.getIdentityId()%>"></input>
			<input type="hidden" name="email" value="<%=memberBean.getEmail()%>"></input>
			<input type="hidden" name="address" value="<%=memberBean.getAddress()%>"></input>
			<input type="hidden" name="memberType" value="<%=memberBean.getMemberType()%>"></input>
			<input type="hidden" name="page" value="RegistrationConfirmation"></input>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>
	</form>
</div>



<div id="login" style="float:left; width: 20%; background-color:black; height: 410px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div id="loginmodal" style="display:none;">
    
	<h1>User Login</h1>
    
	<form id="loginform" name="loginform" method="post" action="#">
      
		<label for="username">Username:</label>
      
		<input type="text" name="username" id="username" class="txtfield" tabindex="1"/>
      
      
		<label for="password">Password:</label>
      
		<input type="password" name="password" id="password" class="txtfield" tabindex="2"/>
      
      
		<div class="center"><input type="submit" name="loginbtn" id="loginbtn" class="flatbtn-blu hidemodal" value="Log In" tabindex="3"></div>
    
	</form>

</div>
	
</body>

</html>