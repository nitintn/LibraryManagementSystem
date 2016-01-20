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
    
<%@page import="javax.security.auth.callback.LanguageCallback"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="javax.security.auth.callback.LanguageCallback"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/issueItem.js"></script>
<title></title>
<script type="text/javascript" src="../js/jquery.js"></script>
<link rel='stylesheet' type='text/css' href='css/styles.css' />
	
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
	
<script type='text/javascript' src='js/menu_jquery.js'></script>
<link rel="stylesheet" href="css/bootstrap-responsive.min.css"/>	
<link rel="stylesheet" href="css/bootstrap.min.css"/>

<script type="text/javascript" src="../js/jquery.js"></script>

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
			<li><a href='Cancel Reservation.html'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='viewReservationList.html'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='last'><a href='jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
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
   
		<li  class='has-sub'><a href='#'><span>Staff</span></a>
      
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

<br><br><br><center>

<%!
String msg=""; 
String msg1="";
String msg2="";

String msg3="";
%>

<%

 msg=(String)request.getAttribute("msg");
 msg1=(String)request.getAttribute("msg1");
 msg2=(String)request.getAttribute("msg2");
 msg3=(String)request.getAttribute("msg3");


if(msg!=null)
{
	out.println("\n");
out.println(msg);
}

if(msg1!=null)
{
	out.println("\n");
out.println("\n"+msg1);
}
if(msg2!=null)
{
	out.println("\n");
out.println("\n"+msg2);
}
if(msg3!=null)
{
	out.println("\n");
out.println("\n"+msg3);
}



%>


<%!

ArrayList<String> publ = new ArrayList<String>();
ArrayList<String> stat = new ArrayList<String>();
%>

<%

// TODO Auto-generated method stub

Statement st=null;
Connection con=null;
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d","a63d");
	st = con.createStatement();
String it = request.getParameter("iteme");
publ.clear();
stat.clear();
	
		String p = "SELECT concat(itemid,title) AS cat FROM(SELECT item.ITEMID  ,item.title ,item.STATUS  FROM ITEM INNER JOIN "+it+" ON "+it+".ITEMID = item.ITEMID )";
	ResultSet rs1 = st.executeQuery(p);
	
	while(rs1.next())
	{
		String a = rs1.getString(1);
		publ.add(a);
		
	}

	String l = "SELECT concat (itemid,status) FROM(SELECT item.ITEMID  ,item.title ,item.STATUS  FROM ITEM INNER JOIN "+it+" ON "+it+".ITEMID = item.ITEMID )";
ResultSet rs2 = st.executeQuery(l);

while(rs2.next())
{
	String b = rs2.getString(1);
	stat.add(b);
}
	
}catch(Exception e){
	
}
finally{
	if(st!=null){
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(con!=null){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

%>

<form action="html/issueItem1.jsp">
<table>
<tbody>

<tr>

<td>Search Item</td>
<td>
<select name="iteme">

<option value="select">---select---
<option value="cd">cd
<option value="book">book
<option value="magazine">magazine
<option value="journal">journal
</select>
</td>


<td>
<select name="publi">
<% 
for(int i=0;i<publ.size();i++)
{%>
<option><%=publ.get(i) %>

<%} %>

</select>
</td>

<td>
<select name="statu">
<% 
for(int i=0;i<stat.size();i++)
{%>
<option><%=stat.get(i) %>

<%} %>

</select>
</td>
<td><input type="submit" value="Search"></td>
</tr>
<tr>
</tr>
</tbody>
</table>
</form>

<form action="issueServlt" id="issueItemForm">
<table>
<tbody>

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
<td><input type="button" value="Submit"  onclick="validateIssueItemForm()"></td>
<td><input type="reset" value="Reset"></td>
<td><input type="hidden" name="page" value="issueItem"></td>
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