<!DOCTYPE h<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL =../GuestHome.html"); %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Transaction Management</title>

<script type="text/javascript" src="../js/jquery.js"></script>
<link rel='stylesheet' type='text/css' href='../css/styles.css' />
	
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
	
<script type='text/javascript' src='../js/menu_jquery.js'></script>
<link rel="stylesheet" href="../css/bootstrap-responsive.min.css"/>	
<link rel="stylesheet" href="../css/bootstrap.min.css"/>

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

<link rel="stylesheet" type="text/css" href="../css/AddAdmin.css"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/AddAdmin.js"></script>
</head>


<body  onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">

<div id="top-stuff">
	
		<div id="top-bar-out">
    	
			<div id="container">
    	
				<div id="topnav">
                    
						<div id="session">
            
							<a id="signin-link" href="../Guest1?page=SignOutMember"><strong>Sign Out</strong></a>
            
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
		<li class='active'><a href='MemberHome.jsp'><span>Home</span></a></li>
		
		<li><a href='#'><span>Search Item</span></a>		
		<ul>
			<li><a href='MemberSearchBook.jsp'><span> Search Book</span></a></li>
			<li><a href='MemberSearchCd.jsp'><span> Search Cd</span></a></li>
			<li><a href='MemberSearchMagazine.jsp'><span>Search Magazine</span></a></li>
			<li class='last'><a href='MemberSearchJournal.jsp'><span>Search Journal</span></a></li>
		</ul>
		</li>
  
		<li ><a href='../makeANewRenewal?btn=MemSubmit'><span>Renew Item</span></a></li>
   
		<li class='has-sub'><a href='#'><span>Advance Reservation</span></a>
		<ul>        
			<li><a href='../html/MemAdvReservation.jsp'><span>Make Reservation</span></a></li>       
			<li><a href='../html/memCancelReservation.jsp'><span>Cancel Reservation</span></a></li>         
			<li class='last'><a href='../html/MemViewReservationList.html'><span>View Reservation List</span></a></li>     
		</ul>
   		</li>
   
		<li class='has-sub'><a href='#'><span>Report Lost/Stolen</span></a>
		<ul>     
			<li><a href='../LostServlet?req=register'><span>Issued Books</span></a></li>        
			<li><a href='../LostCdServlet?req=register'><span>Issued Cds</span></a></li>         
			<li><a href='../LostMagServlet?req=register'><span>Issued Magazines</span></a></li>			
			<li><a href='../LostJournalServlet?req=register'><span>Issued Journals</span></a></li>       
		</ul>
   		</li>
		<li ><a href='../LostServlet?req=view'><span>Cancel Report</span></a></li>
		<li class='has-sub'><a href='#'><span>Review</span></a>
		<ul>
         
			<li><a href='../jsp/GiveReview.jsp'><span>Add Review</span></a></li>         
			<li><a href='../jsp/viewReview.jsp'><span>View Review</span></a></li>         
			<li><a href='../jsp/UpdateReview.jsp'><span>Update Review</span></a></li>         
			<li class='last'><a href='../jsp/deleteReview.jsp'><span>Delete Review</span></a></li>
      
		</ul>
   		</li>
   
		<li><a href="../AdminServlet1?Button=UpgradeMemberType&Page=Menu"><span>Upgrade Member Type</span></a>      
		</li>
	
</ul>
	
</div>
	<div id="content" style="float: left; width:60%; background-color:black; height: 950px; color:white;">	
<br><br><br><center>	

 <form name="formCancelRes" action="../neel?result=CancelReservation" method="post">
<table>
<tr><td>
Enter Member ID: </td><td> <input type="text" name="memId" id="memId"></td></tr>
<tr><td>
Enter Reservation No.:</td><td> <input type="text" name="resNo" id="resId"></td></tr>
</table>
<center><input type="submit" name="advanceReservation" value="Cancel Reservation"> </center>

</form>
   </center>
	</div>
<div id="login" style="float:left; width: 20%; background-color:black; height: 950px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
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