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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Advanced Reservation</title>

<script type="text/javascript" src="../js/jquery.js"></script>
<link rel='stylesheet' type='text/css' href='../css/styles.css' />
<script type="text/javascript" src="../js/AdvancedReservationJavaScript.js"  ></script>

<script type="text/javascript" src="../js/SearchItemForReservationScript.js"></script>	

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

<link rel="stylesheet" type="text/css" href="css/AddAdmin.css"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/AddAdmin.js"></script>

</head>
<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">
<div id="top-stuff">
	
		<div id="top-bar-out">
    	
			<div id="container">
    	
				<div id="topnav">
                    
						<div id="session">
            
							<a id="signin-link" href="Guest1?page=SignOut"><strong>Sign Out</strong></a>
            
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

	
<div id='cssmenu' style="width: 20%; float:left; height:558px; background-image: url(../images/wood.jpg)">	
		
<ul>
   
		<li><a href='#'><span>Home</span></a></li>
   
		<li ><a href='#'><span>Add Item</span></a>
      
		<ul>
         
			<li><a href='AddBookAdmin.html'><span>Book</span></a></li>
         
			<li><a href='AddCdAdmin.html'><span>Cd</span></a></li>
         
			<li><a href='AddMagazineAdmin.html'><span>Magazine</span></a></li>
         
			<li class='last'><a href='AddJournalAdmin.html'><span>Journal</span></a></li>
      
		</ul>
		</li>
   
		<li><a href='Search.jsp'><span>Search Item</span></a></li>
   
   		<li><a href='Delete.jsp'><span>Delete Item</span></a></li>
   
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
   
		<li class='has-sub active'><a href='#'><span>Advance Reservation</span></a>     
		<ul>        
			<li><a href='LibrarianNeel.html'><span>Make Reservation</span></a></li>  
			<li><a href='Cancel Reservation.html'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='viewReservationList.html'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='last'><a href='jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
		<li class='last'><a href='index.html'><span>Renew Item</span></a></li>
		
		<li class='last'><a href='../LostServlet?req=viewAll'><span>View Lost/Stolen Report</span></a></li>
		
		<li class='last'><a href='../jsp/viewAdminReview.jsp'><span>View Review</span></a></li>
		
		<li class='last'><a href='Searchdefaulterslist.html'><span>View Defaulter List</span></a></li>
   
   		<li><a href='../jsp/rackHomeAdd.jsp'><span>Add Item to Rack</span></a></li>   		
   		
		<li><a href='../jsp/rackHomeView.jsp'><span>View Rack</span></a></li>
		
		<li class='has-sub'><a href='#'><span>News</span></a>
			<ul>        
			<li><a href='../jsp/AddNews.jsp'><span>Add News</span></a></li>  
			<li><a href='../jsp/DeleteNews.jsp'><span>Delete News</span></a></li>    
			</ul>
		</li>   	
   
		<li  class='has-sub'><a href='#'><span>Staff</span></a>
      
		<ul>
         
			<li><a href='AddAdmin.html'><span>Add Admin</span></a></li>
         
			<li><a href='AddLibrarian.html'><span>Add Librarian</span></a></li>
         
			<li class='last'><a href='DeleteUser.jsp'><span>Delete User</span></a></li>
      
		</ul></li>
					
</ul>
	
</div>
<div id="filler" style="float:left; width: 5%; background-color:black; height: 438px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
	<div id="content" style="float: left; width:55%; background-color:black; height: 438px; overflow: auto;">	
	
<br><br><br><center>


<form method="post" action="../neel?result=Submit" onsubmit="return checkUserDetails()">
    
		<div id="err"></div>
			<div id="review">
			<table class="rackTable">
				<tr>
				
				<td> <select id="category" name="category" onchange="hideShowDiv();">
				<option value="Select Category">Select Category</option>
				<option value="BOOK" id="BOOK">BOOK</option>
				<option value="CD" id="CD">CD</option>
				<option value="MAGAZINE" id="MAGAZINE">MAGAZINE</option>
				<option value="JOURNAL" id="JOURNAL">JOURNAL</option>
				</select></td>
			</tr>
			<tr><td></td><td><p id="categoryErr" style="color: red; font-size: xx-small" align="right"></p>
			</td>
			</tr>
			</table>
			</div>
			
<div id="bookDiv" style="display:none;"> 
            <table>
			<tr>
			<td><font style="color: red;">&nbsp;</font> Title : </td>
			<td> <input type="text" id="Title" name="Title"/></td><td><p id="titleErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr> 
			<td><font style="color: red;">&nbsp;</font>Author : </td>
			<td><input type="text" id="Author" name="Author"/></td><td><p id="AuthorErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr> 
			<td><font style="color: red;">&nbsp;</font>Subject : </td>
			<td><input type="text" id="Subject" name="Subject"/></td><td><p id="SubjectErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr>
			<td><font style="color: red;">&nbsp;</font>Publication : </td>
			<td><input type="text" id="Publication" name="Publication"/></td><td><p id="PublicationErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			</table>
			<center>
			<input type="submit" name = "Submit1" value="Submit">
			</center>
		    </div>
		
<div id="CDDiv" style="display:none;">
			<table>
			<tr>
			<td><font style="color: red;">&nbsp;</font> Title : </td>
			<td> <input type="text" id="Title1" name="Title1"/></td><td><p id="titleErr1" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr> 
			<td><font style="color: red;">&nbsp;</font>Subject : </td>
			<td><input type="text" id="Subject1" name="Subject1"/></td><td><p id="SubjectErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr>
			<td><font style="color: red;">&nbsp;</font>Publication : </td>
			<td><input type="text" id="Publication1" name="Publication1"/></td><td><p id="PublicationErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			</table>
			<center>
			<input type="submit" name = "Submit1" value="Submit">
			</center>
		    </div>
		
		<div id="MagDiv" style="display:none;">
			<table>
			<tr>
			<td><font style="color: red;">&nbsp;</font> Title : </td>
			<td> <input type="text" id="Title2" name="Title2"/></td><td><p id="titleErr2" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr>
			<td><font style="color: red;">&nbsp;</font> Volume Number : </td>
			<td> <input type="text" id="Volume2" name="Volume2"/></td><td><p id="VolumeErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			</table>
			
			<center>
			<input type="submit" name = "Submit1" value="Submit">
			</center>
		</div>
		
		<div id="JournalDiv" style="display:none;">
			<table>
			<tr>
			<td><font style="color: red;">&nbsp;</font> Title : </td>
			<td> <input type="text" id="Title3" name="Title3"/></td><td><p id="titleErr3" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			<tr>
			<td><font style="color: red;">&nbsp;</font> Volume Number : </td>
			<td> <input type="text" id="Volume3" name="Volume3"/></td><td><p id="VolumeErr" style="color: red; font-size: xx-small" align="right"></p></td>
			</tr>
			</table>
			
			<center>
			<input type="submit" name = "Submit1" value="Submit">
			</center>
		</div>
		
</form>
</center>
	</div>
<div id="login" style="float:left; width: 20%; background-color:black; height: 438px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
	<div id="fillerOne" style="float:left; width: 60%; background-color:black; height: 120px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
	<div id="fillerTwo" style="float:left; width: 20%; background-color:black; height: 120px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerTwo" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>

	<div id="footerThree" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
</body>


</html>