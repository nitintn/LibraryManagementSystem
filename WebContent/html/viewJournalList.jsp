<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tcs.ilp.lms.bean.JournalBean1"%>
<%
	Object jourBean = request.getAttribute("viewJournalList");
	ArrayList<JournalBean1> jourList = (ArrayList<JournalBean1>) jourBean;
	System.out.println("Size: " + jourList.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<title>Return Journal</title>

<script type="text/javascript" src="js/jquery.js"></script>

<link rel='stylesheet' type='text/css' href='css/styles.css' />
	
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
	
<script type='text/javascript' src='js/menu_jquery.js'></script>
<link rel="stylesheet" href="css/bootstrap-responsive.min.css"/>	
<link rel="stylesheet" href="css/bootstrap.min.css"/>

<script type="text/javascript" src="js/jquery.js"></script>

<link rel="stylesheet" href="css/signin.css"></link>

<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>

<link rel="icon" href="http://designshack.net/favicon.ico"/>

<link rel="stylesheet" href="css/css.css"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<script type="text/javascript" src="js/js.js"></script>

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
<script type="text/javascript" src="js/AddAdmin.js"></script>
</head>
<body style="background-image: url(images/wood.jpg);" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">
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

	

	<div id="header" name="headerOne" style="width: 25%; height:140px; background-image: url(images/header.jpg); color: white; line-height: 100px;float: left">
	</div>

	<div id="header" name="headerTwo" style="width: 55%; height:140px; background-image: url(images/wood.jpg); color: white; line-height: 100px;float: left">
	<center><b>Library Management System</b></center>
	</div>

	<div id="header" name="headerThree" style="width: 20%; height:140px; background-image: url(images/wood.jpg); color: white; line-height: 100px;float: left">
	</div>

<div id='cssmenu'
	style="width: 20%; float: left; height: 693px; background-image: url(images/wood.jpg)">
	
	
<ul>
   
		<li><a href='#'><span>Home</span></a></li>
   
		<li class='has-sub'><a href='#'><span>Add Item</span></a>
      
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
         
			<li class='last'><a href='AdminServlet1?Page=MasterProfileUpdate&isFirst=first'><span>Update Master Profile</span></a></li>
      
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
			<li><a href='LibrarianNeel.html'><span>Make Reservation</span></a></li>  
			<li><a href='CancelReservationLib.html'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='viewReservationList.html'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='active'><a href='jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
		<li class='last'><a href='index.html'><span>Renew Item</span></a></li>
		
		<li class='last'><a href='LostServlet?req=viewAll'><span>View Lost/Stolen Report</span></a></li>
		
		<li class='last'><a href='jsp/viewAdminReview.jsp'><span>View Review</span></a></li>
		
		<li class='last'><a href='Searchdefaulterslist.html'><span>View Defaulter List</span></a></li>
   
   		<li><a href='jsp/rackHomeAdd.jsp'><span>Add Item to Rack</span></a></li>   		
   		
		<li><a href='jsp/rackHomeView.jsp'><span>View Rack</span></a></li>
		
		<li class='has-sub'><a href='#'><span>News</span></a>
			<ul>        
			<li><a href='jsp/AddNews.jsp'><span>Add News</span></a></li>  
			<li><a href='jsp/DeleteNews.jsp'><span>Delete News</span></a></li>    
			</ul>
		</li>   	
   
		<li  class='active'><a href='#'><span>Staff</span></a>
      
		<ul>
         
			<li><a href='AddAdmin.html'><span>Add Admin</span></a></li>
         
			<li><a href='AddLibrarian.html'><span>Add Librarian</span></a></li>
         
			<li class='last'><a href='DeleteUser.jsp'><span>Delete User</span></a></li>
      
		</ul></li>
					
</ul>
	
</div>
		
<div id="filler"
	style="float: left; width: 5%; background-color: black; height: 574px; color: white; background-image: url(images/wood.jpg)">
</div>

<div id="content"
	style="float: left; width: 55%; background-color: black; height: 574px; color: white; overflow: auto;">


<br></br>
				
<center>
		<ul id="ddtoptabs">

		<li><a href="ReturnServlet?req=issuedbook" >Issued Books</a></li>

		<li><a href="ReturnServlet?req=issuedcd" >Issued Cds</a></li>

		<li><a href="ReturnServlet?req=issuedmagazine" >Issued Magazines</a></li>

		<li><a href="ReturnServlet?req=issuedjournal" >Issued Journals</a></li>

		<li><a href="ReturnServlet?req=issuedlist" >All Issued Items</a></li>

		</ul>
</center>		

	</div>
	
		 <% if(jourList.size()==0) { %>
 		<form >
 		<br></br>
 		<br></br>
 		<br></br>
 		
 		
 		    <center>
			<h5>Sorry!!No Journals issued by this member</h5>
			</center>
 
 		</form>
 <%} else { %>
			
			
		<form action="ReturnServlet?req=jourstatuschange" method="post" onsubmit="return check2()">
			
	
	<div class="a" id="div1" align="center" >
		<br></br>
		<br></br>
		
		
		<h3 id="cdlist">Issued Journals List</h3>
		<br>
		<table id="booklist" border="1">
	<tr>
		<td></td>
		
		<td>Journal Id</td>
		<td>Title</td>
		<td>Issued Date</td>

		<td>Due Date</td>
	</tr>		
	

		

<%
		for (int i = 0; i < jourList.size(); i++) {
			JournalBean1 e = jourList.get(i);
	%>
	<tr>
		<td><input type="checkbox" value="<%=Integer.toString(e.getTxnId())%>" name="radio1"></td>

		<td><%=e.getJournalId()%></td>
		<td><%=e.getJournalName()%></td>
		<td><%=e.getIssuedDate()%></td>
		<td><%=e.getDueDate()%></td>
		<%
			}
		%>
<%HttpSession session3=request.getSession(true);
		session3.setAttribute("journallist",jourList);
	%>
	</tr>

		</table>
		<br></br>
		<input type="submit" value="Return Journal" name="returnjour"></input></form>
				<div id="message" style="color: red" align="center"></div>	
<%} %>
    </div>
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