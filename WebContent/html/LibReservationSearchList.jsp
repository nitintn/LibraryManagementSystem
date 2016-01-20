<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%
    HttpSession session1=request.getSession(false);
    String name=(String)session1.getAttribute("userName");
    
    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = ../GuestHome.html"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tcs.ilp.lms.bean.*"%>
<%@page import="java.util.*"%>
<!--

//-->

<%
Object advResBean = request.getAttribute("viewBookList");
ArrayList<AdvancedReservationBean> reservationlist = (ArrayList<AdvancedReservationBean>) advResBean;
System.out.println("Size: " + reservationlist.size());
Object category= session1.getAttribute("category");
String categoryS= (String)category;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/AdvancedReservationJavaScript.js"  ></script>

<title>reservation view</title>



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
 <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">  
 <script src="//code.jquery.com/jquery-1.9.1.js"></script>  
 <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  
 <link rel="stylesheet" href="/resources/demos/style.css">  
 <script>  $(function() {    $( "#datepicker" ).datepicker();  });  
 </script>
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

	

	<div id="header" name="headerOne" style="width: 25%; height:140px; background-image: url(images/header.jpg); color: white; line-height: 100px;float: left">
	</div>

	<div id="header" name="headerTwo" style="width: 55%; height:140px; background-image: url(images/wood.jpg); color: white; line-height: 100px;float: left">
	<center><b>Library Management System</b></center>
	</div>

	<div id="header" name="headerThree" style="width: 20%; height:140px; background-image: url(images/wood.jpg); color: white; line-height: 100px;float: left">
	</div>

	
	<div id='cssmenu' style="width: 20%; float:left; height:558px; background-image: url(images/wood.jpg)">	
	
<ul>  
		<li><a href='#'><span>Home</span></a></li>
 
		<li class='has-sub'><a href='#'><span>Add Item</span></a>
      
		<ul>
         
			<li><a href='html/AddBookAdmin.html'><span>Book</span></a></li>
         
			<li><a href='html/AddCdAdmin.html'><span>Cd</span></a></li>
         
			<li><a href='html/AddMagazineAdmin.html'><span>Magazine</span></a></li>
         
			<li class='last'><a href='html/AddJournalAdmin.html'><span>Journal</span></a></li>
      
		</ul>
		</li>
   
		<li><a href='html/Search.jsp'><span>Search Item</span></a></li>
   
   		<li><a href='html/Delete.jsp'><span>Delete Item</span></a></li>
   
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
			<li><a href='html/LibrarianNeel.html'><span>Make Reservation</span></a></li>  
			<li><a href='html/CancelReservationLib.html'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='html/viewReservationList.html'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='last'><a href='jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
		<li class='last'><a href='html/index.html'><span>Renew Item</span></a></li>
		
		<li class='last'><a href='LostServlet?req=viewAll'><span>View Lost/Stolen Report</span></a></li>
		
		<li class='last'><a href='jsp/viewAdminReview.jsp'><span>View Review</span></a></li>
		
		<li class='last'><a href='html/Searchdefaulterslist.html'><span>View Defaulter List</span></a></li>
   
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
         
			<li><a href='html/AddAdmin.html'><span>Add Admin</span></a></li>
         
			<li><a href='html/AddLibrarian.html'><span>Add Librarian</span></a></li>
         
			<li class='last'><a href='html/DeleteUser.jsp'><span>Delete User</span></a></li>
      
		</ul></li>
					
</ul>
	
</div>
	
	<div id="filler" style="float:left; width: 5%; background-color:black; height: 438px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
	<div id="content" style="float: left; width:55%; background-color:black; height: 438px; overflow: auto;">	
	<br><br><br><center>


<form action="neel?result=ReserveLib" method="post" onsubmit="return checkAllFields()">
<table border="1" style="width:300px">

 <% for(int i=0; i<reservationlist.size(); i++)
{
		AdvancedReservationBean advResvBean = reservationlist.get(i);
		System.out.println("Sizefgnfgnj: " + reservationlist.size());
		String categoryB = advResvBean.getCategory();
		System.out.println("category is "+category);
if (categoryB.equalsIgnoreCase("BOOK"))
{
	int count=0;
	if(reservationlist.size()>0 && count==0)
	{%>

	<tr>
	 					<td>Select</td>
	 					<td>ItemId</td>
	 					<td>Title</td>
	 					<td>ISBN</td>
	  					<td>Subject</td>
	  					<td>Author</td> 
	  					<td>Item Status</td>
						</tr>
						<%count++;
	 }
	%>
 					
					 <tr> 
					 <!--<td><input type='radio' name='selectItem' value="<%= advResvBean.getItemName()%>"></td> -->
					 <td><input type='radio' name='selectItem' value="<%= advResvBean.getIsbn()%>"></td> 
					 <td><%=advResvBean.getItemId()%></td> 
					 <td><%=advResvBean.getItemName()%></td> 
					 <td><%=advResvBean.getIsbn()%></td> 
					 <td><%=advResvBean.getSubject()%></td> 
					 <td><%=advResvBean.getAuthorName()%></td> 
					 <td><%=advResvBean.getItemStatus()%></td>
					 </tr> 			
<%
}// if

else if (categoryB.equalsIgnoreCase("CD"))
{ %>
<tr>
 					<td>Select</td>
 					<td>ItemId</td>
 					<td>Title</td>
 					<td>UPC</td>
  					<td>Subject</td>
  					<td>Publication</td> 
  					<td>Item Status</td>
					</tr>
					<tr> 
					 <td><input type='radio' name='selectItem' value="<%=advResvBean.getIsbn()%>"></td> 
					 <td><%=advResvBean.getItemId()%></td> 
					 <td><%=advResvBean.getItemName()%></td> 
					 <td><%=advResvBean.getIsbn()%></td>
					 <td><%=advResvBean.getSubject()%></td> 
					 <td><%=advResvBean.getPublication()%></td> 
					 <td><%=advResvBean.getItemStatus()%></td>
					 </tr> 

<% 
}//first else if

else if (categoryB.equalsIgnoreCase("MAGAZINE"))
{ %>
<tr>
 					<td>Select</td>
 					<td>ItemId</td>
 					<td>Title</td>
  					<td>ISBN</td>
  					<td>VolumeNumber</td> 
  					<td>Item Status</td>
					</tr>
					<tr> 
					 <td><input type='radio' name='selectItem' value="<%=advResvBean.getIsbn()%>"></td> 
					 <td><%=advResvBean.getItemId()%></td> 
					 <td><%=advResvBean.getItemName()%></td> 
					  <td><%=advResvBean.getIsbn()%></td> 
					 <td><%=advResvBean.getVolumeNumber()%></td> 
					 <td><%=advResvBean.getItemStatus()%></td>
					 </tr> 

<% 
}//second else if
else if (categoryB.equalsIgnoreCase("JOURNAL"))
{ %>
<tr>
 					<td>Select</td>
 					<td>ItemId</td>
 					<td>Title</td>
 					<td>ISBN</td>
  					<td>VolumeNumber</td> 
  					<td>Item Status</td>
					</tr>
					<tr> 
					 <td><input type='radio' name='selectItem' value="<%=advResvBean.getIsbn()%>"></td> 
					 <td><%=advResvBean.getItemId()%></td> 
					 <td><%=advResvBean.getItemName()%></td> 
					 <td><%=advResvBean.getIsbn()%></td> 
					 <td><%=advResvBean.getVolumeNumber()%></td> 
					 <td><%=advResvBean.getItemStatus()%></td>
					 </tr> 

<% 
}//third else if
}//for
%>
</table> <br><br>

User Id:<font color="red">*</font> &nbsp;&nbsp; <input type="text" name="userId" id="userIdId"><div id="userIdDiv"></div><br><br><!--
Item Id:<font color="red">*</font>&nbsp;&nbsp; <input type="text" name="itemId" id="itemIdId"><div id="itemIdDiv"></div><br><br>
Enter Reservation Date:<font color="red">*</font> &nbsp;&nbsp;<input type="text" name="reservationDate" id="datepicker" ><div id="reservationDateDiv"></div><br><br>
--><input type="submit" name = "Submit1" value="Reserve" >

</form>


   </center>
	</div>
<div id="login" style="float:left; width: 20%; background-color:black; height: 438px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
	<div id="fillerOne" style="float:left; width: 60%; background-color:black; height: 120px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
	<div id="fillerTwo" style="float:left; width: 20%; background-color:black; height: 120px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="footerOne" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="footerTwo" style="float:left; width: 60%; background-color:black; height: 30px; color:white; background-image: url(images/wood.jpg)">
	</div>

	<div id="footerThree" style="float:left; width: 20%; background-color:black; height: 30px; color:white; background-image: url(images/wood.jpg)">
	</div>
	
</body>


</html>