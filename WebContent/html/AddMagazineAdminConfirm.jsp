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

<SCRIPT type="text/javascript">     
window.history.forward();     
function noBack() { window.history.forward(); } 
</SCRIPT>

<title>AddMagazine</title>

<link rel="stylesheet" type="text/css" href="./css/AddBook.css"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="./js/AddBook.js"></script>

</head>

<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">

	<div id="top-stuff">
	
		<div id="top-bar-out">
    	
			<div id="container">
    	
				<div id="topnav">
        
					<div id="session">
            
							<a id="signin-link" href="./Guest1?page=SignOut"><strong>Sign Out</strong></a>
            
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

	
<div id='cssmenu' style="width: 20%; float:left; height:693px; background-image: url(../images/wood.jpg)">
	
	<ul>   
		<li><a href='AdminServlet?Page=changelog'><span>Home</span></a></li>   
		<li class='has-sub active'><a href='#'><span>Add Item</span></a>     
		<ul>        
			<li><a href='html/AddBookAdmin.jsp'><span>Book</span></a></li>        
			<li><a href='html/AddCdAdmin.jsp'><span>Cd</span></a></li>         
			<li><a href='html/AddMagazineAdmin.jsp'><span>Magazine</span></a></li>         
			<li class='last'><a href='html/AddJournalAdmin.jsp'><span>Journal</span></a></li>      
		</ul>
		</li>
		<li class='has-sub'><a href='#'><span>Search Item</span></a>		
		<ul>
			<li><a href='html/SearchBook.jsp'><span> Search Book</span></a></li>
			<li><a href='html/SearchCd.jsp'><span> Search Cd</span></a></li>
			<li><a href='html/SearchMagazine.jsp'><span>Search Magazine</span></a></li>
			<li class='last'><a href='html/SearchJournal.jsp'><span>Search Journal</span></a></li>
		</ul>
		</li>
		
   		<li class='has-sub'><a href='#'><span>Delete Item</span></a>
   		<ul>
			<li><a href='html/DeleteBook.jsp'><span> Delete Book</span></a></li>
			<li><a href='html/DeleteCd.jsp'><span> Delete Cd</span></a></li>
			<li><a href='html/DeleteMagazine.jsp'><span>Delete Magazine</span></a></li>
			<li class='last'><a href='html/DeleteJournal.jsp'><span>Delete Journal</span></a></li>
		</ul>
		</li>
		
		<li class='has-sub'><a href='#'><span>Master Profile</span></a>
		<ul>         
			<li><a href='html/AddMasterProfile.html'><span>Add Master Profile</span></a></li>        
			<li class='last'><a href='AdminServlet1?Page=MasterProfileUpdate&isFirst=first'><span>Update Master Profile</span></a></li>     
		</ul>
		</li>
		
		<li class='has-sub'><a href='#'><span>Issue Item</span></a>     
		<ul>        
			<li><a href='html/issueItem2.jsp'><span>Issue</span></a></li>  
			<li><a href='html/issueByPayment.jsp'><span>Issue by Payment</span></a></li>        
			<li class='last'><a href='html/issueItemReservedInAdvance1.html'>Issue Advance Reserved Item<span></span></a></li>      
		</ul>
		</li>
   
		<li class='has-sub'><a href='#'><span>Advance Reservation</span></a>     
		<ul>        
			<li><a href='html/LibrarianNeel.jsp'><span>Make Reservation</span></a></li>  
			<li><a href='html/CancelReservationLib.jsp'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='html/viewReservationList.jsp'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='last'><a href='jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
		<li class='last'><a href='html/index.html'><span>Renew Item</span></a></li>
		
		<li class='last'><a href='LostServlet?req=viewAll'><span>View Lost/Stolen Report</span></a></li>
		
		<li class='last'><a href='jsp/viewAdminReview.jsp'><span>View Review</span></a></li>
		
		<li class='last'><a href='jsp/Searchdefaulterslist.jsp'><span>Search Defaulter List</span></a></li>
		
		<li class='last'><a href='jsp/viewDefaulterslist.jsp'><span>View Defaulter List</span></a></li>
   
   		<li><a href='jsp/rackHomeAdd.jsp'><span>Add Item to Rack</span></a></li>   		
   		
		<li><a href='jsp/rackHomeView.jsp'><span>View Rack</span></a></li>
		
		<li class='has-sub'><a href='#'><span>News</span></a>
			<ul>        
			<li><a href='jsp/AddNews.jsp'><span>Add News</span></a></li>  
			<li><a href='jsp/DeleteNews.jsp'><span>Delete News</span></a></li>    
			</ul>
		</li>   	
   
		<li class='has-sub'><a href='#'><span>Staff</span></a>      
		<ul>         
			<li><a href='html/AddAdmin.jsp'><span>Add Admin</span></a></li>         
			<li><a href='html/AddLibrarian.jsp'><span>Add Librarian</span></a></li>         
			<li class='last'><a href='html/DeleteMember.jsp'><span>Delete User</span></a></li>   
			<li class='last'><a href='html/DeleteStaff.jsp'><span>Delete Staff</span></a></li>
		</ul></li>
					
	</ul>	
	</div>
	
	<div id="filler" style="float:left; width: 5%; background-color:black; height: 574px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
	<div id="content" style="float: left; width:55%; background-color:black; height: 574px; color:white; overflow: auto;">	
		<fieldset><legend style="color: white;">Add Magazine Confirmation</legend>
		<%String confirm = (String) request.getAttribute("confirm");
		if(confirm!=null){%>
		<br><h2 style="color: white;"><%=confirm %></h2>
		<%} %>
		</fieldset>
	</div>

	<div id="login" style="float:left; width: 20%; background-color:black; height: 574px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="fillerOne" style="float:left; width: 60%; background-color:black; height: 119px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
	<div id="fillerTwo" style="float:left; width: 20%; background-color:black; height: 119px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="footerTwo" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="footerThree" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
</body>


</html>