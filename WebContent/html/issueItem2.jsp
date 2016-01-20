<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page language="java" import="com.tcs.ilp.lms.bean.*" %>
    <%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="../js/issueItem2.js"></script>
   
<%@page import="javax.security.auth.callback.LanguageCallback"%>



<%@page import="javax.security.auth.callback.LanguageCallback"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title></title>
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
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
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

	
<div id='cssmenu'
	style="width: 20%; float: left; height: 693px; background-image: url(../images/wood.jpg)">
		
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
			<li><a href='AddMasterProfile.jsp'><span>Add Master Profile</span></a></li>        
			<li class='last'><a href='../AdminServlet1?Page=MasterProfileUpdate&isFirst=first'><span>Update Master Profile</span></a></li>     
		</ul>
		</li>
		
		<li class='has-sub'><a href='#'><span>Issue Item</span></a>     
		<ul>        
			<li><a href='../html/issueItem2.jsp'><span>Issue</span></a></li>  
			<li><a href='../html/issueByPayment.jsp'><span>Issue by Payment</span></a></li>        
			<li class='last'><a href='../html/issueItemReservedInAdvance1.html'>Issue Advance Reserved Item<span></span></a></li>      
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
   
		<li class='has-sub' class= 'active'><a href='#'><span>Staff</span></a>      
		<ul>         
			<li><a href='AddAdmin.jsp'><span>Add Admin</span></a></li>         
			<li><a href='AddLibrarian.jsp'><span>Add Librarian</span></a></li>         
			<li class='last'><a href='DeleteMember.jsp'><span>Delete User</span></a></li>   
			<li class='last'><a href='DeleteStaff.jsp'><span>Delete Staff</span></a></li>
		</ul></li>
					
	</ul>	
	</div>
	
	<div id="filler"
	style="float: left; width: 5%; background-color: black; height: 574px; color: white; background-image: url(../images/wood.jpg)">
</div>

<div id="content"
	style="float: left; width: 55%; background-color: black; height: 574px; color: white; overflow: auto;">


<br><br><br><center>

<%!BookBean been3=null; %>
<%been3 = (BookBean)request.getAttribute("been2"); %>
<%!String msg="";
String msg1="";
String msg2="";
String msg3="";
String msg4="";
String msg5="";
String msg6="";
%>
<%
msg=(String)request.getAttribute("msg");
msg1=(String)request.getAttribute("msg1");
msg2=(String)request.getAttribute("msg2");
msg3=(String)request.getAttribute("msg3");
msg4=(String)request.getAttribute("msg4");
msg5=(String)request.getAttribute("msg5");
msg6=(String)request.getAttribute("msg6");






if(msg!=null)
{
	%>
	
  <font color="red"> <%=msg %></font>
<% }

if(msg1!=null)
{
	%>
	
	<font color="red"><%=msg1 %></font>
	
<% }

if(msg2!=null)
{
	%>
	
<font color="red">	<%=msg2 %></font>

<% }
if(msg3!=null)
{
	%>
	
  <font color="red"><%=msg3 %></font>
<% }
if(msg4!=null)
{
	%>
<font color="red"><%=msg4 %></font>

<% }
if(msg5!=null)
{
	%>
	
	<font color="red"><%=msg5 %></font>
<% }

if(msg6!=null)
{
	%>
<font color="red">	<%=msg6 %></font>
<% }



	%>

<form action="../issueServlet1" id="issueItemForm1"  >
<table>

<tr>
<td>
</td>
<td>Issue Item 
</td>
</tr>

<tr>
<td>Enter MemberID<font color="red">*</font></td>
<td><input type="text" name="memberID" id="memberID"></td>
<td><p id="memDisplay" style="color: red"></p></td>
</tr>


<tr>
<td>select Item<font color="red">*</font></td>
<td><select name="item" id="item">
<option value="select">---select---
<option value="book">Books
<option value="cd">CDs
<option value="journal">Journals
<option value="magazine">Magazines
</select></td>
<td><p id="itm" style="color: red"></p></td>
</tr>
<tr>
<td>Enter itemID<font color="red">*</font></td>
<td><input type="text" name="itemID" id="itemID"></td>
<td><p id="itemDisplay" style="color: red"></p></td>
</tr>
<tr>
<td><input type="hidden" name="page" value="searchItem"></td>
</tr>

<tr>
<td><input type="button" name="page" value="searchItem" onclick=" return validateIssueItemForm()"></td>
</tr>

</table>
</form>


<%if(been3!=null)
	
	{%>


<form action="issueServlet1">
<table border="1">

<tr>
<td>itemID</td>
<td>title</td>
<td>status</td>
</tr>


<tr>
<td><%=been3.getItemId() %></td>
<td><%=been3.getTitle() %></td>
<td><%=been3.getStatus() %></td>
</tr>

<tr>
<td><input type="submit" value="issueItem" name="page"></td>

</tr>

</table>
</form>


<%} %>

</center>
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