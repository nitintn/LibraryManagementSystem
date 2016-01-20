<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList"%>
    <%@page import="com.tcs.ilp.lms.bean.BookBean"%>
    <%@page import="com.tcs.ilp.lms.bean.MasterProfileBean"%>
	<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<head>

<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	
<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	
<link rel='stylesheet' type='text/css' href='./css/styles.css' />
	
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
	
<script type='text/javascript' src='./js/menu_jquery.js'></script>

<script type="text/javascript" src="./js/jquery.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<script type="text/javascript" src="./js/js.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="./css/signin.css"></link>
<link rel="stylesheet" href="./css/jquery.dataTables.css"></link>
<link rel="stylesheet" href="./css/jquery-ui-1.7.2.custom.css"></link>

<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>

<link rel="icon" href="http://designshack.net/favicon.ico"/>

<link rel="stylesheet" href="./css/css.css"/>

<script type="text/javascript" src="./js/DeleteItem.js"></script>
<script type="text/javascript" src="./js/Delete.js"></script>


<script type="text/javascript">
$(document).ready(function () {

	$("#example").dataTable({
		"bDestroy": true, 
		"bServerSide": true,         
		"bProcessing": true,
		"sAjaxSource": '../AdminServlet1?Page=usertable',
		"aoColumns": [{"mData":"UserId"},{"mData":"FirstName"},{"mData":"IdentityId"}]
		
	});
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

<title>Search Book</title>

<link rel="stylesheet" type="text/css" href="./css/AddAdmin.css"/>


</head>


<body>

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

	
<div id='cssmenu' style="width: 20%; float:left; height:693px; background-image: url(./images/wood.jpg)">
	
<ul>
   
		<li class='last'><a href='../AdminServlet?Page=changelog'><span>Home</span></a></li>
   
		<li class='has-sub'><a href='#'><span>Add Item</span></a>
      
		<ul>
         
			<li><a href='AddBookAdmin.html'><span>Book</span></a></li>
         
			<li><a href='AddCdAdmin.html'><span>Cd</span></a></li>
         
			<li><a href='AddMagazineAdmin.html'><span>Magazine</span></a></li>
         
			<li class='last'><a href='AddJournalAdmin.html'><span>Journal</span></a></li>
      
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
   
   		<li><a href='DeleteBook.jsp'><span>Delete Item</span></a></li>
   
		<li class='has-sub'><a href='#'><span>Master Profile</span></a>
      
		<ul>
         
			<li><a href='AddMasterProfile.html'><span>Add Master Profile</span></a></li>
         
			<li class='last'><a href='./AdminServlet1?Page=MasterProfileUpdate&isFirst=first'><span>Update Master Profile</span></a></li>
      
		</ul>
		</li>
   
		<li class='has-sub' class='active'><a href='#'><span>Staff</span></a>
      
		<ul>
         
			<li><a href='AddAdmin.html'><span>Add Admin</span></a></li>
         
			<li><a href='AddLibrarian.html'><span>Add Librarian</span></a></li>
         
			<li class='last'><a href='DeleteUser.jsp'><span>Delete User</span></a></li>
      
		</ul></li>
					
</ul>

<div id="filler" style="float:left; width: 5%; background-color:black; height: 574px; color:white; background-image: url(../images/wood.jpg)">
	</div>
	
</div>
	<div id="content" style="float: left; width:55%; background-color:black; height: 574px; color:white; overflow: auto;">	
	
		 <table id="example">
  			<thead>
    			<tr><th class="site_name">UserId</th><th class="site_name">First Name</th><th class="site_name">Identity Id</th></tr>
  			</thead>
  			<tbody>
  			</tbody>
		</table>
		<p></p>
			
	<center><div id="txtHint"></div></center>
			

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