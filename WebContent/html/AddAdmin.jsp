<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     
     
    <%HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../html/sessionRedirect.html"); %>
    
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
<link rel="stylesheet" type="text/css" href="../css/AddAdmin.css"/>

<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>	
<script type='text/javascript' src='../js/menu_jquery.js'></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="../js/js.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/AddAdmin.js"></script>

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

<title>Add Admin</title>

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
	
<div id='cssmenu' style="width: 20%; float:left; height:693px; background-image: url(../images/wood.jpg)">
	
	<ul>   
		<li><a href='../AdminServlet1?Page=changelog'><span>Home</span></a></li>   
		<li class='has-sub'><a href='#'><span>Add Item</span></a>     
		<ul>        
			<li><a href='AddBookAdmin.jsp'><span>Book</span></a></li>        
			<li><a href='AddCdAdmin.jsp'><span>Cd</span></a></li>         
			<li><a href='AddMagazineAdmin.jsp'><span>Magazine</span></a></li>         
			<li class='last'><a href='AddJournalAdmin.jsp'><span>Journal</span></a></li>      
		</ul>
		</li>
		<li class='has-sub'><a href='#'><span>Search Item</span></a>		
		<ul>
			<li><a href='SearchBook.jsp'><span> Search Book</span></a></li>
			<li><a href='SearchCd.jsp'><span> Search Cd</span></a></li>
			<li><a href='SearchMagazine.jsp'><span>Search Magazine</span></a></li>
			<li class='last'><a href='SearchJournal.jsp'><span>Search Journal</span></a></li>
		</ul>
		</li>
		
   		<li class='has-sub'><a href='#'><span>Delete Item</span></a>
   		<ul>
			<li><a href='DeleteBook.jsp'><span> Delete Book</span></a></li>
			<li><a href='DeleteCd.jsp'><span> Delete Cd</span></a></li>
			<li><a href='DeleteMagazine.jsp'><span>Delete Magazine</span></a></li>
			<li class='last'><a href='DeleteJournal.jsp'><span>Delete Journal</span></a></li>
		</ul>
		</li>
		
		<li class='has-sub'><a href='#'><span>Master Profile</span></a>
		<ul>         
			<li><a href='AddMasterProfile.html'><span>Add Master Profile</span></a></li>        
			<li class='last'><a href='../AdminServlet1?Page=MasterProfileUpdate&isFirst=first'><span>Update Master Profile</span></a></li>     
		</ul>
		</li>
		
		<li class='has-sub'><a href='#'><span>Issue Item</span></a>     
		<ul>        
			<li><a href='issueItem2.jsp'><span>Issue</span></a></li>  
			<li><a href='issueByPayment.jsp'><span>Issue by Payment</span></a></li>        
			<li class='last'><a href='issueItemReservedInAdvance1.html'>Issue Advance Reserved Item<span></span></a></li>      
		</ul>
		</li>
   
		<li class='has-sub'><a href='#'><span>Advance Reservation</span></a>     
		<ul>        
			<li><a href='LibrarianNeel.jsp'><span>Make Reservation</span></a></li>  
			<li><a href='CancelReservationLib.jsp'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='viewReservationList.jsp'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='last'><a href='../jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
		<li class='last'><a href='index.html'><span>Renew Item</span></a></li>
		
		<li class='last'><a href='../LostServlet?req=viewAll'><span>View Lost/Stolen Report</span></a></li>
		
		<li class='last'><a href='../jsp/viewAdminReview.jsp'><span>View Review</span></a></li>
		
		<li class='last'><a href='../jsp/Searchdefaulterslist.jsp'><span>Search Defaulter List</span></a></li>
		
		<li class='last'><a href='../jsp/viewDefaulterslist.jsp'><span>View Defaulter List</span></a></li>
   
   		<li><a href='../jsp/rackHomeAdd.jsp'><span>Add Item to Rack</span></a></li>   		
   		
		<li><a href='../jsp/rackHomeView.jsp'><span>View Rack</span></a></li>
		
		<li class='has-sub'><a href='#'><span>News</span></a>
			<ul>        
			<li><a href='../jsp/AddNews.jsp'><span>Add News</span></a></li>  
			<li><a href='../jsp/DeleteNews.jsp'><span>Delete News</span></a></li>    
			</ul>
		</li>   	
   
		<li class='has-sub active'><a href='#'><span>Staff</span></a>      
		<ul>         
			<li><a href='AddAdmin.jsp'><span>Add Admin</span></a></li>         
			<li><a href='AddLibrarian.jsp'><span>Add Librarian</span></a></li>         
			<li class='last'><a href='DeleteMember.jsp'><span>Delete User</span></a></li>   
			<li class='last'><a href='DeleteStaff.jsp'><span>Delete Staff</span></a></li>
		</ul></li>
					
	</ul>	
	</div>
	
	<div id="filler" style="float:left; width: 5%; background-color:black; height: 574px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
	<div id="content" style="float: left; width:55%; background-color:black; height: 574px; overflow: auto;">	
		<form action="../AdminServlet1" method="post" id="addAdmin_form">
		<fieldset><legend style="color: white;">Add Admin</legend>
			<p>
				<label for="userId" style="color: white;">User ID : <span>*</span></label>
				<input id="userId1" type="text" name="userId" size="30" maxlength="6" onkeyup="checkUserIdAvailability()" onkeypress="return RestrictSpace()" onblur="removeText()" onfocus="displayText()" />
				<div id="userIdMessage" style="display:none"><font color=red>Please enter a User ID</font></div>
				<div id="userIdAvailabilityMessage" style="color:red;"></div>	
			</p>	
			<p>
				<label for="password" style="color: white;">Password (One time password) : <span>*</span></label>
				<input id="password1" type="text" name="password" size="30"  disabled  onkeyup="enablePassword()" onkeypress="return RestrictSpace()" onblur="removePasswordText()"/>	
			</p>	
			<p>
				<label for="firstName" style="color: white;">First Name : <span>*</span></label>
				<input id="firstName1" type="text" name="firstName" size="30"  disabled  onkeyup="enableFirstName()" onkeypress="return RestrictSpace()" onblur="removeFirstNameText()"/>	
			</p>	
			<p>
				<label for="lastName" style="color: white;">Last Name : <span>*</span></label>
				<input id="lastName1" type="text" name="lastName" size="30"  disabled  onkeyup="enableLastName()" onkeypress="return RestrictSpace()" onblur="removeLastNameText()"/>	
			</p>	
			<p>
				<label for="IdentityId" style="color: white;">Identity ID (PAN/License) : <span>*</span></label>
				<input id="identityId1" type="text" name="identityId" size="30" onkeyup="checkIdentityAvailability()" disabled  onkeyup="enableIdentityId()" onkeypress="return RestrictSpace()" onblur="removeIdentityIdText()"/>	
				<div id="IdentityIdAvailabilityMessage" style="color:white;"></div>	
			</p>		
			<p>
				<label for="contactNo" style="color: white;">Contact No (10 digits) : <span>*</span></label>
				<input id="contact1" type="text" name="contactNo" size="30" maxlength="10" disabled  onkeyup="enableContact()" onkeypress="return RestrictSpace()" onblur="removeContactText()"/>	
			</p>	
			<p>
				<label for="email" style="color: white;">Email : <span>*</span></label>
				<input id="email1" type="text" name="email" size="30" disabled  onkeyup="enableEmail()" onkeypress="return RestrictSpace()"/>	
			</p>	
			<p>
	   			<label for="address" style="color: white;">Address : <span>*</span></label>
	   			<textarea rows="5" cols="50" name="address"></textarea>
	  		</p>
	  		<p>
	   			<input type="submit" value="Submit" name="submit" />
	   			<input type="hidden" name="page" value="AddAdmin"></input>
	  		</p>
		 </fieldset>
	</form>
	</div>

	<div id="login" style="float:left; width: 20%; background-color:black; height: 574px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="fillerOne" style="float:left; width: 60%; background-color:black; height: 119px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
	<div id="fillerTwo" style="float:left; width: 20%; background-color:black; height: 119px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerTwo" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerThree" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

</body>

</html>