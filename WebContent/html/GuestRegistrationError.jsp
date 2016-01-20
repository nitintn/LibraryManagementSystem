<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>

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
<link rel="stylesheet" href="./css/commonCss.css"/>

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
<script type="text/javascript" src="js/SignUp.js"></script>

</head>


<body>

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
                			<button class="submit button" type="submit" name="submit" value="SignIn">Sign in</button>
                			<input type="hidden" name="page" value="GuestHome"></input>
							<button class="submit button" type="submit" name="submit" value="Register">Not a member?Register</button></br>
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

	<div id='cssmenu' style="width: 20%; float:left; height:950px; background-image: url(./images/wood.jpg)">
	
<ul>
   			<li class='active'><a href='GuestHome.html'><span>Home</span></a></li>

</ul>	
</div>

	<div id="content" style="float: left; width:60%; background-color:black; height: 950px; overflow-y:scroll;">	
	<%String confirm = (String) request.getAttribute("confirm");
		if(confirm!=null){%>
		<br><p style="color: white;"><%=confirm %></p>
		<%} 
	%>
	<form action="Guest1" method="post" id="signup_form1">
		<fieldset><legend style="color: white;">Login Again</legend>
			<p>
				<label for="userId" style="color: white;">User ID : <span>*</span></label>
				<input type="text" name="userId" size="30" />
			</p>
			<p>
				<label for="password" style="color: white;">Password : <span>*</span></label>
				<input type="password" name="password"size="30" />
			</p>
	  		<p>
	   			<input type="submit" value="SignIn" name="submit" />
	   			<input type="hidden" name="page" value="GuestHome"></input>
	  		</p>
		 </fieldset>
	</form>
	<form action="./MemberServlet1" method="post" id="signup_form">
		<fieldset><legend style="color:white;">Not a Member yet? Sign Up</legend>
			<p>
				<label for="userId" style="color: white;">User ID : <span>*</span></label>
				<input id="userId1" type="text" name="userId" size="30" maxlength="6" onkeyup="checkUserIdAvailability()" onkeypress="return RestrictSpace()" onblur="removeText()" onfocus="displayText()" />
				<div id="userIdMessage" style="display:none"><font color=red>Please enter a User ID</font></div>
				<div id="userIdAvailabilityMessage" style="color:red;"></div>	
			</p>	
			<p>
				<label for="password" style="color: white;">Password (One time password) : <span>*</span></label>
				<input id="password1" type="text" name="password" size="30"  disabled  onkeyup="enablePassword()" onkeypress="return RestrictSpace()" onblur="removePasswordText()" onfocus="displayPasswordText()">	
			</p>	
			<div id="passwordMessage" style="display:none"><font color=red>Please enter a one time password</font></div>			
			<p>
				<label for="firstName" style="color: white;">First Name : <span>*</span></label>
				<input id="firstName1" type="text" name="firstName" size="30"  disabled  onkeyup="enableFirstName()" onkeypress="return RestrictSpace()" onblur="removeFirstNameText()" onfocus="displayFirstNameText()">	
			</p>	
			<div id="firstNameMessage" style="display:none"><font color=red>Please enter your First Name</font></div>			
			<p>
				<label for="lastName" style="color: white;">Last Name : <span>*</span></label>
				<input id="lastName1" type="text" name="lastName" size="30"  disabled  onkeyup="enableLastName()" onkeypress="return RestrictSpace()" onblur="removeLastNameText()" onfocus="displayLastNameText()">	
			</p>	
			<div id="lastNameMessage" style="display:none"><font color=red>Please enter your Last Name</font></div>						
			<p>
				<label for="IdentityId" style="color: white;">Identity ID (PAN/License) : <span>*</span></label>
				<input id="identityId1" type="text" name="identityId" size="30" onkeyup="checkIdentityAvailability()" disabled  onkeyup="enableIdentityId()" onkeypress="return RestrictSpace()" onblur="removeIdentityIdText()" onfocus="displayIdentityIdText()">	
				<div id="identityIdMessage" style="display:none"><font color=red>Please enter an Identity ID</font></div>	
				<div id="IdentityIdAvailabilityMessage" style="color:red;"></div>	
			</p>		
			<p>
				<label for="contactNo" style="color: white;">Contact No (10 digits) : <span>*</span></label>
				<input id="contact1" type="text" name="contactNo" size="30" maxlength="10" disabled  onkeyup="enableContact()" onkeypress="return RestrictSpace()" onblur="removeContactText()" onfocus="displayContactText()">	
			</p>	
			<div id="contactMessage" style="display:none"><font color=red>Please enter a Contact No</font></div>			
			<p>
				<label for="email" style="color: white;">Email : <span>*</span></label>
				<input id="email1" type="text" name="email" size="30" disabled  onkeyup="enableEmail()" onkeypress="return RestrictSpace()" onfocus="displayEmailText()">	
			</p>	
			<div id="EmailMessage" style="display:none"><font color=red>Please enter your Email ID</font></div>			
	   		<p>
	   		<%ArrayList<String> masterList = new ArrayList<String>();
	   		masterList = (ArrayList<String>) request.getAttribute("masterList");
	   		if(masterList!=null){
	   		%>
				<label for="memberType" style="color:white;">Member Type : <span>*</span></label>
				<select name="memberType">
			<% for(String master: masterList) { %>
			<option value="<%=master%>"><%=master%></option><%}}
			%>
			</select>
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

	<div name="login" style="float:left; width: 20%; background-color:black; height: 950px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div name="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div name="footerOne" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(./images/wood.jpg)">
	</div>

	<div name="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(./images/wood.jpg)">
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