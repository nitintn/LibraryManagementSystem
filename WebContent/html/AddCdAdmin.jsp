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
<link rel="stylesheet" type="text/css" href="../css/AddCd.css"/>
<link rel="stylesheet" href="../css/signin.css"></link>
<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>
<link rel="icon" href="http://designshack.net/favicon.ico"/>
<link rel="stylesheet" href="../css/css.css"/>

<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<script type='text/javascript' src='../js/menu_jquery.js'></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="../js/js.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/AddCd.js"></script>

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

<title>Add CD</title>

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
		<li><a href='../AdminServlet?Page=changelog'><span>Home</span></a></li>   
		<li class='has-sub active' ><a href='#'><span>Add Item</span></a>     
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
   
		<li class='has-sub'><a href='#'><span>Staff</span></a>      
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
	<form action="../AdminServlet1" method="post" id="addCd_form">
		<fieldset><legend style="color: white;">Add CD</legend>
			<p>
				<label for="Title" style="color: white;">Title : <span>*</span></label>
				<input id="cdTitle" type="text" name="Title" size="30"  onblur="removeText()" onfocus="displayText()" >
				<div id="titleMessage" style="display:none"><font color=red>Please enter a CD title</font></div>
						<div id="categoryAvailabilityMessage"></div>
			</p>
			
			<p>
	   			<label for="Subject" style="color: white;">Description : <span>*</span></label>
	   			<input id="subject" type="text" name="Subject" size="30" disabled  onkeyup="enableSubjectTextBox()"
					 onblur="removeSubjectText()" onfocus="displaySubjectText()" >
					
					<div id="subjectMessage" style="display:none"><font color=red>Please enter CD description</font></div>
						<div id="categoryAvailabilityMessage"></div>
	   		</p>
	  		<p>
	   			<label for="PlaceOfPublication" style="color: white;">Place of Publication : <span>*</span></label>
	   			<input id="publication" type="text" name="PlaceOfPublication" size="30" disabled  onkeyup="enablePublicationTextBox()"
					 onblur="removePublicationText()" onfocus="displayPublicationText()" >
					
					<div id="publicationMessage" style="display:none"><font color=red>Please enter CD place of publication</font></div>
						<div id="categoryAvailabilityMessage"></div>
	  		</p>
	  		<p>
	   			<label for="Publisher" style="color: white;">Publisher : <span>*</span></label>
	   			<input id="publisher" type="text" name="Publisher" size="30" disabled  onkeyup="enablePublisherTextBox()"
					 onblur="removePublisherText()" onfocus="displayPublisherText()" >
					
					<div id="publisherMessage" style="display:none"><font color=red>Please enter CD publisher</font></div>
						<div id="categoryAvailabilityMessage"></div>
	  		</p>
	  		<p>
	   			<label for="PublicationYear" style="color: white;">Publication Year : <span>*</span></label>
	   			<input id="year" type="text" name="PublicationYear" size="30" disabled  onkeyup="enableYearTextBox()"
					onkeypress="return RestrictSpace()" onblur="removeYearText()" onfocus="displayYearText()" >
					
					<div id="yearMessage" style="display:none"><font color=red>Please enter CD publication year</font></div>
						<div id="categoryAvailabilityMessage"></div>
	  		</p>
	  		<p>
	   			<label for="Cost" style="color: white;">Cost : <span>*</span></label>
	   			<input id="cost" type="text" name="Cost" size="30" disabled  onkeyup="enableCostTextBox()"
					onkeypress="return RestrictSpace()" onblur="removeCostText()" onfocus="displayCostText()" >
					
					<div id="costMessage" style="display:none"><font color=red>Please enter CD cost</font></div>
						<div id="categoryAvailabilityMessage"></div>
	  		</p>
	  		<p>
	   			<input type="submit" value="Submit" name="submit" />
	   			<input type="hidden" name="page" value="AddCdAdmin"></input>
	   			<form action="../AddCdAdmin.jsp">
	   			<input type="submit" value="Cancel" />
			</form>
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