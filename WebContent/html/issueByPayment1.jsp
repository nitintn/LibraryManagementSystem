<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%@page language="java" import="java.sql.*"%>
    <%@page language="java" import="java.awt.*" %>
    <%@page language="java" import="java.util.*" %>
     <%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.security.auth.callback.LanguageCallback"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/issueByPayment.js"></script>
<title></title>
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
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">
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
		<li class='active'><a href='#'><span>Add Item</span></a>     
		<ul>        
			<li><a href='html/AddBookAdmin.jsp'><span>Book</span></a></li>        
			<li><a href='html/AddCdAdmin.jsp'><span>Cd</span></a></li>         
			<li><a href='html/AddMagazineAdmin.jsp'><span>Magazine</span></a></li>         
			<li class='last'><a href='html/AddJournalAdmin.jsp'><span>Journal</span></a></li>      
		</ul>
		</li>
		<li><a href='#'><span>Search Item</span></a>		
		<ul>
			<li><a href='html/SearchBook.jsp'><span> Search Book</span></a></li>
			<li><a href='html/SearchCd.jsp'><span> Search Cd</span></a></li>
			<li><a href='html/SearchMagazine.jsp'><span>Search Magazine</span></a></li>
			<li class='last'><a href='html/SearchJournal.jsp'><span>Search Journal</span></a></li>
		</ul>
		</li>
		
   		<li><a href='#'><span>Delete Item</span></a>
   		<ul>
			<li><a href='html/DeleteBook.jsp'><span> Delete Book</span></a></li>
			<li><a href='html/DeleteCd.jsp'><span> Delete Cd</span></a></li>
			<li><a href='html/DeleteMagazine.jsp'><span>Delete Magazine</span></a></li>
			<li class='last'><a href='html/DeleteJournal.jsp'><span>Delete Journal</span></a></li>
		</ul>
		</li>
		
		<li class='has-sub'><a href='#'><span>Master Profile</span></a>
		<ul>         
			<li><a href='html/AddMasterProfile.jsp'><span>Add Master Profile</span></a></li>        
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
   
		<li class='has-sub' class= 'active'><a href='#'><span>Staff</span></a>      
		<ul>         
			<li><a href='html/AddAdmin.jsp'><span>Add Admin</span></a></li>         
			<li><a href='html/AddLibrarian.jsp'><span>Add Librarian</span></a></li>         
			<li class='last'><a href='html/DeleteMember.jsp'><span>Delete User</span></a></li>   
			<li class='last'><a href='html/DeleteStaff.jsp'><span>Delete Staff</span></a></li>
		</ul></li>
					
	</ul>	
	</div>
	
<div id="filler"
	style="float: left; width: 5%; background-color: black; height: 574px; color: white; background-image: url(images/wood.jpg)">
</div>

<div id="content"
	style="float: left; width: 55%; background-color: black; height: 574px; color: white; overflow: auto;">


<br><br><br><center>

<%!String msg=""; 
String msg1="";
String msg2="";
String msg3="";
Float msg4 =null;
String msg5="";
String memberID="";
String itemId="";
String item_type="";

%>




<%






 msg=(String)request.getAttribute("msg");
 msg1=(String)request.getAttribute("msg1");
 msg2=(String)request.getAttribute("msg2");
 msg3=(String)request.getAttribute("msg3");
 msg4=(Float)request.getAttribute("msg4");
 msg5=(String)request.getAttribute("msg5");
 if(msg!=null)
 {%>

 <font color="red"><%=msg %></font>

 <% }

 if(msg1!=null)
 {%>
 	
 	
 	<font color="red"><%=msg1 %></font>
 	

 <% }
 if(msg2!=null)
 {
 	%>
 	<font color="red"><%=msg2 %></font>

 <% }
 if(msg3!=null)
 {%>
 	
 	<font color="red"><%=msg3 %></font>
 	
 <% }

 if(msg4!=null)
 {
 %>	

 <font color="red"> pay Rs <%=msg4 %></font>

 <% }

 if(msg5!=null)
 {%>

 <font color="red"><%=msg5 %></font>

 <% }%>



<%if(msg4!=null)
{
 %>

<form action="issueItemByPaymentServlet1">
<table>
<tr>
<td></td>
<td><input type="submit" value="Pay Amount"></td>
<td><a href="./html/issueByPayment.jsp">BACK</a></td>
<TD><input type="hidden" name="page" value="amountPaid"></TD>
</tr>
</table>
</form>
</body>
</html>



<%} %>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/issueByPayment.js"></script>
</head>
<body>

<form action="issueItemByPaymentServlet" id="issueItemFormByPaying">
<table>
<tbody>
<h>Issue item by paying</h>
<tr>
<td>Member ID<font color="red">*</font></td>
<td><input type="text" id="memberID" name="memberID"></td>
<td><font color="red"><p id="memDisplay" style="color:red"></p></font></td>
</tr>

<tr>
<td>Item<font color="red">*</font></td>
<td>
<select id="item" onchange="itemCheck()" name="item_type">
<option value="select">---Select---</option>
<option value="book">Book</option>
<option value="magazine">Magazine</option>
<option value="cd">CD</option>
<option value="journal">Journal</option>
</select>
</td>
<td><font color="red"><p id="itm" style="color:red"></p></font></td>

</tr>


<tr>

<td>Item ID<font color="red">*</font></td>
<td><input type="text" name="itemID" id="itemID"></td>
<td><font color="red"><p id="itemDisplay" style="color:red"></p></font></td>
</tr> 

<tr>
<td><input type="button" value="Submit" onclick="validateIssueItemForm()"></td>
<td><input type="reset" value="Reset"></td>
<td><input type="hidden" name="page" value="issueItemByPaying"></td>
</tr>
</tbody>
</table>
</form>

  </center>
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
