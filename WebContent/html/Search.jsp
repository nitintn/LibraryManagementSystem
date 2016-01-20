<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
    
<!DOCTYPE html>

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

<title>Search Item</title>

<link rel="stylesheet" type="text/css" href="../css/AddLibrarian.css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/AddLibrarian.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/DeleteUser.js"></script>

<script type="text/javascript" src="../js/DeleteItem.js"></script>
<script type="text/javascript" src="../js/search.js"></script>

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

	
<div id='cssmenu' style="width: 20%; float:left; height:1300px; background-image: url(../images/wood.jpg)">
	
	<ul>   
		<li><a href='#'><span>Home</span></a></li>   
		<li class='active'><a href='#'><span>Add Item</span></a>     
		<ul>        
			<li><a href='AddBookAdmin.jsp'><span>Book</span></a></li>        
			<li><a href='AddCdAdmin.jsp'><span>Cd</span></a></li>         
			<li><a href='AddMagazineAdmin.jsp'><span>Magazine</span></a></li>         
			<li class='last'><a href='AddJournalAdmin.jsp'><span>Journal</span></a></li>      
		</ul>
		</li>
		<li><a href='#'><span>Search Item</span></a>		
		<ul>
			<li><a href='SearchBook.jsp'><span> Search Book</span></a></li>
			<li><a href='SearchCd.jsp'><span> Search Cd</span></a></li>
			<li><a href='SearchMagazine.jsp'><span>Search Magazine</span></a></li>
			<li class='last'><a href='SearchJournal.jsp'><span>Search Journal</span></a></li>
		</ul>
		</li>
		
   		<li><a href='#'><span>Delete Item</span></a>
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
		
		<li class='last'><a href='jsp/Searchdefaulterslist.jsp'><span>Search Defaulter List</span></a></li>
		
		<li class='last'><a href='jsp/viewDefaulterslist.jsp'><span>View Defaulter List</span></a></li>
   
   		<li><a href='../jsp/rackHomeAdd.jsp'><span>Add Item to Rack</span></a></li>   		
   		
		<li><a href='../jsp/rackHomeView.jsp'><span>View Rack</span></a></li>
		
		<li class='has-sub'><a href='#'><span>News</span></a>
			<ul>        
			<li><a href='../jsp/AddNews.jsp'><span>Add News</span></a></li>  
			<li><a href='../jsp/DeleteNews.jsp'><span>Delete News</span></a></li>    
			</ul>
		</li>   	
   
		<li class='has-sub' class= 'active'><a href='#'><span>Staff</span></a>      
		<ul>         
			<li><a href='AddAdmin.jsp'><span>Add Admin</span></a></li>         
			<li><a href='AddLibrarian.jsp'><span>Add Librarian</span></a></li>         
			<li class='last'><a href='DeleteMember.jsp'><span>Delete User</span></a></li>   
			<li class='last'><a href='DeleteStaff.jsp'><span>Delete Staff</span></a></li>
		</ul></li>
					
	</ul>	
	</div>
	<center>
	<div id="content" style="float: left; width:60%; background-color:black; height: 950px;">	
		<br><br><br>
		<p>
				<label for="ItemTyp2">Item Type : <span>*</span></label>
				<select name="ItemType" id= "Itemtype"><option value="Book">Book</option>
				<option value="CD">CD</option>
				<option value="Magazine">Magazine</option>
				<option value="Journal">Journal</option></select>
	  		</p>
	  		
	  		<p>
				<label for="Title">Title : <span>*</span></label>
				<input type="text" name="Title" id="Id1" size="30" />
			</p>
			
			<p>
	   			<input type="button" value="Submit" name="Search " onclick="onChange();" />
	   			<input type="hidden" id="page" name="page" value="SearchItem"></input>
	  		</p>	  		
			<center><div id="txtHint"></div></center>		
	</div>
</center>
	<div id="login" style="float:left; width: 20%; background-color:black; height: 950px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

</div>
	
</body>


</html>