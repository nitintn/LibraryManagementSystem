<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList"%>
    <%@page import="com.tcs.ilp.lms.bean.JournalBean"%>
    <%@page import="com.tcs.ilp.lms.bean.MasterProfileBean"%>
	<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<head>

<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	
<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	
<link rel='stylesheet' type='text/css' href='../css/styles.css' />
	
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
	
<script type='text/javascript' src='../js/menu_jquery.js'></script>

<script type="text/javascript" src="../js/jquery.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<script type="text/javascript" src="../js/js.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="../css/signin.css"></link>
<link rel="stylesheet" href="../css/jquery.dataTables.css"></link>
<link rel="stylesheet" href="../css/jquery-ui-1.7.2.custom.css"></link>

<link rel="shortcut icon" href="http://designshack.net/favicon.ico"/>

<link rel="icon" href="http://designshack.net/favicon.ico"/>

<link rel="stylesheet" href="../css/css.css"/>



<script type="text/javascript">
$(document).ready(function () {

	$("#example1").dataTable({
		"bDestroy": true, 
		"bServerSide": true,         
		"bProcessing": true,
		"sAjaxSource": '../LibrarianServlet?Page=datatable3',
		"aoColumns": [{"mData":"ItemId"},{"mData":"Title"},{"mData":"VolumeNo"},{"mData":"SubscriptionType"},{"mData":"cost"}]
		
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

<title>Search Journal</title>

<link rel="stylesheet" type="text/css" href="../css/AddAdmin.css"/>


</head>


<body>

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

	<div id='cssmenu' style="width: 20%; float:left; height:558px; background-image: url(images/wood.jpg)">	
	<ul>
		<li class='last'><a href='LibrarianServlet?Page=changelog'><span>Home</span></a></li>
		<li class='has-sub'><a href='#'><span>Add Item</span></a>     
		<ul>
			<li><a href='AddBookLibrarian.html'><span>Book</span></a></li>
			<li><a href='AddCdLibrarian.html'><span>Cd</span></a></li> 
			<li><a href='AddMagazineLibrarian.html'><span>Magazine</span></a></li>         
			<li class='last'><a href='AddJournalLibrarian.html'><span>Journal</span></a></li>   
		</ul>
		</li>
		
		<li class='has-sub active'><a href='#'><span>Search Item</span></a>		
		<ul>
			<li><a href='LibrarianSearchBook.jsp'><span> Search Book</span></a></li>
			<li><a href='LibrarianSearchCd.jsp'><span> Search Cd</span></a></li>
			<li><a href='LibrarianSearchMagazine.jsp'><span>Search Magazine</span></a></li>
			<li class='last'><a href='LibrarianSearchJournal.jsp'><span>Search Journal</span></a></li>
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
			<li><a href='#'><span>Make Reservation</span></a></li>  
			<li><a href='#'><span>Cancel Reservation</span></a></li>        
			<li class='last'><a href='#'>View Reservation List<span></span></a></li>      
		</ul>
		</li>   
		
		<li class='last'><a href='jsp/ReturnItem.jsp'><span>Return Item</span></a></li>
   		
		<li class='last'><a href='index.html'><span>Renew Item</span></a></li>
		
		<li class='last'><a href='LostServlet?req=viewAll'><span>View Lost/Stolen Report</span></a></li>
		
		<li class='last'><a href='jsp/viewLibrarianReview.jsp'><span>View Review</span></a></li>
		
		<li class='last'><a href='jsp/LibrarianSearchdefaulterslist.jsp'><span>Search Defaulter List</span></a></li>
		
		<li class='last'><a href='jsp/LibrarianViewDefaulterslist.jsp'><span>View Defaulter List</span></a></li>
   
   		<li><a href='jsp/rackHomeAdd.jsp'><span>Add Item to Rack</span></a></li>   		
   		
		<li><a href='jsp/rackHomeView.jsp'><span>View Rack</span></a></li>

	</ul>	
	</div>

<div id="filler" style="float:left; width: 5%; background-color:black; height: 438px; color:white; background-image: url(../images/wood.jpg)">
</div>
	<div id="content" style="float: left; width:55%; background-color:black; height: 438px; color:white; overflow: auto;">	
	
		 <table id="example1">
  			<thead>
    			<tr><th class="site_name">ItemId</th><th class="site_name">Title</th><th class="site_name">VolumeNo</th><th class="site_name">SubscriptionType</th><th class="site_name">Cost</th></tr>
  			</thead>
  			<tbody>
  			</tbody>
		</table>
			

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