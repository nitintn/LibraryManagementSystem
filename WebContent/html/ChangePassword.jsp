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
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>	
<script type='text/javascript' src='../js/menu_jquery.js'></script>
<script type="text/javascript" src="../js/jquery.js"></script>

<link rel="stylesheet" href="../css/signin.css"></link>
<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>
<link rel="icon" href="http://designshack.net/favicon.ico"/>
<link rel="stylesheet" href="../css/css.css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="../js/js.js"></script>

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


<title>Change Password</title>

<link rel="stylesheet" type="text/css" href="../css/AddLibrarian.css"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/AddLibrarian.js"></script>

</head>


<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">

	<div id="top-stuff">
	
		<div id="top-bar-out">
    	
			<div id="container">
    	
				<div id="topnav">
        
					<div id="session">
            
							<a id="signin-link" href="../Guest1?page=SignOut"><strong>Sign Out</strong></a>
            
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

	
<div id='cssmenu' style="width: 20%; float:left; height:270px; background-image: url(../images/wood.jpg)">
	
<ul>
   			<li class='active'><a href='GuestHome.html'><span>Home</span></a></li>

</ul>	
</div>
	<div id="content" style="float: left; width:60%; background-color:black; height: 270px;">	
	<form action="../Guest1" method="post" id="addLibrarian_form">
		<fieldset><legend style="color: white;">Change Password</legend>
			<p>
				<label for="password" style="color: white;">Password : <span>*</span></label>
				<input type="password" name="password"size="30" />
			</p>
			<p>
				<label for="cpassword" style="color: white;">Confirm Password : <span>*</span></label>
				<input type="password" name="cpassword"size="30" />
			</p>
			<p>
	   			<input type="submit" value="Submit" name="submit" />
	   			<input type="hidden" name="page" value="ChangePassword"></input>
	  		</p>	
	  		<p style="color:white;"><%String error = (String) request.getAttribute("error");%>
	  		<%if(error!= null){%><%=error%><%}%></p>
		 </fieldset>
	</form>
	</div>

	<div id="login" style="float:left; width: 20%; background-color:black; height: 270px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 400px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 60%; background-color:black; height: 400px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 400px; color:white; background-image: url(../images/wood.jpg)">
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