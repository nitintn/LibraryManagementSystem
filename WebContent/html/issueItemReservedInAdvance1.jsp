<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page language="java" import="java.sql.*"%>
<%@page language="java" import="java.util.*"%>
<%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
<%@page language="java" import="com.tcs.ilp.lms.bean.*"%>
<%@page language="java" import="com.tcs.ilp.lms.service.*"%>
<script type="text/javascript" src="js/issueItemReservedInAdvance1.js"></script>

<%@page import="javax.security.auth.callback.LanguageCallback"%>




<script type="text/javascript" src="js/issueItemReservedInAdvance1.js"></script>
<html>
<head>

<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />

<link rel='stylesheet' type='text/css' href='css/styles.css' />
<link rel="stylesheet" type="text/css" href="css/SignUp.css" />
<link rel="stylesheet" href="css/signin.css"></link>
<link rel="shortcut icon" href="http://designshack.net/favicon.ico" />
<link rel="icon" href="http://designshack.net/favicon.ico" />
<link rel="stylesheet" href="css/css.css" />
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<script type='text/javascript' src='js/menu_jquery.js'></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/SignUp.js"></script>

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
$(function(){
    /*
     * this swallows backspace keys on any non-input element.
     * stops backspace -> back
     */
    var rx = /INPUT|SELECT|TEXTAREA/i;

    $(document).bind("keydown keypress", function(e){
        if( e.which == 8 ){ // 8 == backspace
            if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
                e.preventDefault();
            }
        }
    });
});
</script>

<title></title>

</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="" oncontextmenu="return false;">
<div id="top-stuff">

<div id="top-bar-out">

<div id="container">

<div id="topnav">

<div id="session"><a id="signin-link" href="Guest1?page=SignOut"><strong>Sign
Out</strong></a></div>

</div>

</div>

</div>

</div>

<div id="header" name="headerOne"
	style="width: 25%; height: 140px; background-image: url(images/header.jpg); color: white; line-height: 100px; float: left">
</div>

<div id="header" name="headerTwo"
	style="width: 55%; height: 140px; background-image: url(images/wood.jpg); color: white; line-height: 100px; float: left">
<center><b>Library Management System</b></center>
</div>

<div id="header" name="headerThree"
	style="width: 20%; height: 140px; background-image: url(images/wood.jpg); color: white; line-height: 100px; float: left">
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
		<li><a href='html/SearchMagazine.jsp'><span>Search
		Magazine</span></a></li>
		<li class='last'><a href='html/SearchJournal.jsp'><span>Search
		Journal</span></a></li>
	</ul>
	</li>

	<li><a href='#'><span>Delete Item</span></a>
	<ul>
		<li><a href='html/DeleteBook.jsp'><span> Delete Book</span></a></li>
		<li><a href='html/DeleteCd.jsp'><span> Delete Cd</span></a></li>
		<li><a href='html/DeleteMagazine.jsp'><span>Delete
		Magazine</span></a></li>
		<li class='last'><a href='html/DeleteJournal.jsp'><span>Delete
		Journal</span></a></li>
	</ul>
	</li>

	<li class='has-sub'><a href='#'><span>Master Profile</span></a>
	<ul>
		<li><a href='html/AddMasterProfile.html'><span>Add
		Master Profile</span></a></li>
		<li class='last'><a
			href='AdminServlet1?Page=MasterProfileUpdate&isFirst=first'><span>Update
		Master Profile</span></a></li>
	</ul>
	</li>

	<li class='has-sub'><a href='#'><span>Issue Item</span></a>
	<ul>
		<li><a href='html/issueItem2.jsp'><span>Issue</span></a></li>
		<li><a href='html/issueByPayment.jsp'><span>Issue by
		Payment</span></a></li>
		<li class='last'><a href='html/issueItemReservedInAdvance1.html'>Issue
		Advance Reserved Item<span></span></a></li>
	</ul>
	</li>

	<li class='has-sub'><a href='#'><span>Advance
	Reservation</span></a>
	<ul>
		<li><a href='html/LibrarianNeel.jsp'><span>Make
		Reservation</span></a></li>
		<li><a href='html/CancelReservationLib.jsp'><span>Cancel
		Reservation</span></a></li>
		<li class='last'><a href='html/viewReservationList.jsp'>View
		Reservation List<span></span></a></li>
	</ul>
	</li>

	<li class='last'><a href='jsp/ReturnItem.jsp'><span>Return
	Item</span></a></li>

	<li class='last'><a href='html/index.html'><span>Renew
	Item</span></a></li>

	<li class='last'><a href='LostServlet?req=viewAll'><span>View
	Lost/Stolen Report</span></a></li>

	<li class='last'><a href='jsp/viewAdminReview.jsp'><span>View
	Review</span></a></li>

	<li class='last'><a href='jsp/Searchdefaulterslist.jsp'><span>Search
	Defaulter List</span></a></li>

	<li class='last'><a href='jsp/viewDefaulterslist.jsp'><span>View
	Defaulter List</span></a></li>

	<li><a href='jsp/rackHomeAdd.jsp'><span>Add Item to
	Rack</span></a></li>

	<li><a href='jsp/rackHomeView.jsp'><span>View Rack</span></a></li>

	<li class='has-sub'><a href='#'><span>News</span></a>
	<ul>
		<li><a href='jsp/AddNews.jsp'><span>Add News</span></a></li>
		<li><a href='jsp/DeleteNews.jsp'><span>Delete News</span></a></li>
	</ul>
	</li>

	<li class='has-sub' class='active'><a href='#'><span>Staff</span></a>
	<ul>
		<li><a href='html/AddAdmin.jsp'><span>Add Admin</span></a></li>
		<li><a href='html/AddLibrarian.jsp'><span>Add
		Librarian</span></a></li>
		<li class='last'><a href='html/DeleteMember.jsp'><span>Delete
		User</span></a></li>
		<li class='last'><a href='html/DeleteStaff.jsp'><span>Delete
		Staff</span></a></li>
	</ul>
	</li>

</ul>
</div>

<div id="filler"
	style="float: left; width: 5%; background-color: black; height: 500px; background-image: url(images/wood.jpg)">
</div>
<div id="filler"
	style="float: left; width: 5%; background-color: black; height: 574px; color: white; background-image: url(images/wood.jpg)">
</div>

<div id="content"
	style="float: left; width: 55%; background-color: black; height: 574px; color: white; overflow: auto;">

<center><br>
<br>
<br>

<font color="white">Issue Item Reserved In Advance</font> <%!

String UserName="";
int memberID=0;
int ItemId=0;
String ItemName="";
int ReservationStatus=0;
String ReservationDate="";
ArrayList<AdvancedReservationBean> ar2 =null;
ArrayList<Integer> itemIDList=null;
ArrayList<AdvancedReservationBean> ar3 =null;
int status=0;
String msg="";
String msg1="";

%>


<form action="issueItemReservedInAdvanceServlet1" id="chkBoxForm"
	name="deleteFiles" onsubmit="return checkBox()">

<p id="alrt" style="color: red"></p>

<table border="1">


	<%

out.println("Issue Item Reserved In Advance");

String memberID1 =  (String) session.getAttribute("memberID");

memberID = Integer.parseInt(memberID1);

 ar2 = new LibrarianService().advResList(memberID);

itemIDList = (ArrayList<Integer>)request.getAttribute("itemList");

msg = (String)request.getAttribute("msg");

msg1 = (String)request.getAttribute("msg1");


if(msg!=null)
{%>

	<font color="red"><%=msg%></font>




	<%}

if(msg1!=null)
{%>

	<font color="red">itemID <%=msg1 %> because waiting number is
	not zero</font>
	<% }

%>



	<tr>
		<td><font color="white">select</font></td>
		<td><font color="white">memberName</font></td>
		<td><font color="white">memberID</font></td>
		<td><font color="white">itemID</font></td>
		<td><font color="white">itemName</font></td>
		<td><font color="white">Waiting Number</font></td>
		<td><font color="white">reservationDate</font></td>
	</tr>
	<% 
for(int i=0;i<ar2.size();i++)
{ %>
	<tr>
		<td><input type="checkbox" name="parteek"
			value="<%=ar2.get(i).getItemId()%>" id="<%=ar2.get(i).getItemId() %>"></td>
		<td><font color="white"><%=ar2.get(i).getUserName()%></font></td>
		<td><font color="white"><%=ar2.get(i).getUserId() %></font></td>
		<td><font color="white"><%=ar2.get(i).getItemId() %></font></td>
		<td><font color="white"><%=ar2.get(i).getItemName() %></font></td>
		<td><font color="white"><%=ar2.get(i).getReservationStatus() %></font></td>
		<td><font color="white"><%= ar2.get(i).getReservationDate()%></font></td>
	</tr>
	<%} %>

	<tr>
		<td><input type="submit" value="Submit"></td>
		<td><a href="html/issueItemReservedInAdvance1.html">Back</a></td>
	</tr>

	<input type="hidden" name="page" value="afterLogin">

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