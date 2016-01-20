<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	
<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />	
<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />	

<link rel='stylesheet' type='text/css' href='../css/styles.css' />	
<link rel="stylesheet" href="../css/signin.css"></link>
<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>
<link rel="icon" href="http://designshack.net/favicon.ico"/>
<link rel="stylesheet" href="../css/css.css"/>
<link rel="stylesheet" href="../css/commonCss.css"/>
<link rel="stylesheet" type="text/css" href="../css/SignUp.css"/>

<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>	
<script type='text/javascript' src='../js/menu_jquery.js'></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="../js/js.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/SignUp.js"></script>
<script type="text/javascript" src="../js/valid.js"></script>

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

<script type="text/javascript">     
window.history.forward();     
function noBack() { window.history.forward(); } 
</script>

<title>Registration Form</title>

</head>


<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">

	<div id="top-stuff">	
		<div id="top-bar-out">    	
			<div id="container">    	
				<div id="topnav">       
					<div class="active-links">          
						<div id="session">            
							<a id="signin-link" href="#"><strong>Sign in/Register</strong></a>           
						</div>            	
						<div id="signin-dropdown">        		
						<form method="post" class="signin" action="Guest1">
                		<fieldset class="textbox">
            			<label class="username"><span>UserID</span><input id="userId" name="userId" value="" type="text"/></label>               
                		<label class="password"><span>Password</span><input id="password" name="password" value="" type="password"/></label>
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

	<div id="header" name="headerOne" style="width: 25%; height:140px; background-image: url(../images/header.jpg); color: white; line-height: 100px;float: left">
	</div>

	<div id="header" name="headerTwo" style="width: 55%; height:140px; background-image: url(../images/wood.jpg); color: white; line-height: 100px;float: left">
	<center><b>Library Management System</b></center>
	</div>

	<div id="header" name="headerThree" style="width: 20%; height:140px; background-image: url(../images/wood.jpg); color: white; line-height: 100px;float: left">
	</div>
	
	<div id='cssmenu' style="width: 20%; float:left; height:950px; background-image: url(../images/wood.jpg)">
	
<ul>
   			<li class='active'><a href='GuestHome.html'><span>Home</span></a></li>

</ul>	
</div>
	
	<div id="content" style="float: left; width:60%; background-color:black; height: 1000px;">	
	<form action="../MemberServlet1" method="post" id="signup_form">
		<fieldset><legend>Member Sign-Up</legend>
			<p>
				<label for="userId" style="color:white;">User ID : (6 digits)<span>*</span></label>
				<input type="text" name="userId" size="30" />
			</p>
			<p>
				<label for="password" style="color:white;">Password : (Min 6 characters)<span>*</span></label>
				<input type="password" name="password"size="30" />
			</p>
			<p>
				<label for="firstname" style="color:white;">First Name : <span>*</span></label>
				<input type="text" name="firstName" size="30" id="firstName1" onchange="fname()"  />
				<font color="red"><label id="errormsg1"></label></font>
			</p>
			<p>
				<label for="lastname" style="color:white;">Last Name : <span>*</span></label>
				<input type="text" name="lastName" size="30" id="lastName1" onchange="lname()"/>
				<font color="red"><label id="errormsg2"></label></font>
			</p>
			<p>
				<label for="IdentityId" style="color:white;">Identity ID (PAN/License) : <span>*</span></label>
				<input type="text" name="IdentityId"size="30" />
			</p>
			<p>
				<label for="contactNo" style="color:white;">Contact No : <span>*</span></label>
				<input type="text" name="contactNo" size="30" />
			</p>
	  		<p>
	   			<label for="email" style="color:white;">Email : <span>*</span></label>
	   			<input type="text" name="email" size="30" />
	   		</p>
	   		<p>
				<label for="memberType" style="color:white;">Member Type : <span>*</span></label>
				<select name="memberType"><option value="Silver">Silver</option><option value="Gold">Gold</option><option value="Platinum">Platinum</option></select>
	  		</p>
	  		<p>
	   			<label for="address" style="color:white;">Address : <span>*</span></label>
	   			<textarea rows="5" cols="50" name="address"></textarea>
	  		</p>
	  		<p>
	   			<input type="submit" value="Submit" name="submit" />
	   			<input type="hidden" name="page" value="SignUp"></input>
	  		</p>
		 </fieldset>
	</form>
	</div>

	<div name="login" style="float:left; width: 20%; background-color:black; height: 1000px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div name="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div name="footerOne" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div name="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

</body>

</html>