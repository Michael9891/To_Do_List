<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!-- Java bean bonus -->
<jsp:useBean id="name" class="com.tables.Users" scope="session"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
	<head>
		<title>HOME</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    	<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
    	<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    	<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"rel = "stylesheet">
      <!--CSS for diajog -->
      <style>
      .row{
      		white-space: nowrap;
    overflow: hidden;
    width: 10px;
    height: 30px;
    text-overflow: ellipsis;
      	}
        .ui-widget-header,.ui-state-default .ui-button {
            background:#6A5ACD;
            border: 1px solid #b9cd6d;
            color: #FFFFFF;
            font-weight: bold;
         }
			.ui-widget-overlay {
		   background: #000000;
		   opacity: 0.8;
		 }
      </style>

		<script type = "text/javascript">
		<!--auto load table of tasks from Jersey and create rows and append them to table  -->
			function loadtable() {
				var userName = '<%= session.getAttribute("userName") %>';
		    	$.ajax({
		            type: "GET",
		            url: "rest/gettable/get",
		            dataType: "json",
		            data: {userName:userName},
		            success: function(msg) {
		                var row = "";
		                $.each(msg, function(i, item) {
		                    row+='<tr data-tuser="'+item.id+'">'+'<td class="row">'+item.task+'</td >'+'<td class="row">'+item.description+'</td>'+'<td class="row">'+item.date+'</td>'+'<td><a href="#" class="edit" >edit</a></td>'+'<td><a href="#" class="deleteRow" >delete</a></td>'+'</tr>';
		                });
		            	$('#table').append(row);
		            },
		            error:function(msg) {
		   				alert(msg);
		            }
		        });
		 	}
	        $(function() {
	            $( "#dialog-1" ).dialog({
	               autoOpen: false,
	               modal:true
	               
	            });
	            <!--  Open dialof for update task. Get fields from table of tasks and put them into fields of dialog-->
	            $( '#table tbody' ).on('click','.edit',(function(e) {
	            	var task = $(this).closest("tr").find("td:nth-child(1)").html();
	            	var descript = $(this).closest("tr").find("td:nth-child(2)").html();
	            	var date = $(this).closest("tr").find("td:nth-child(3)").html();
	            	var indexOfrow =  $(this).closest("tr").index();
	            	$("#dlg-task").val(task);
	            	$("#dlg-description").val(descript);
	            	$("#dlg-date").val(date);
	            	$("#row").val(indexOfrow);
	            	e.preventDefault();
	               $( "#dialog-1" ).dialog( "open" );
	            }));
	         });
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			<!--Create new task,get fields from New Task form passing them to Jersey for save in database and append to table  -->
		    $('#add').submit(function(e) {
		    	e.preventDefault();
		        var date = $("#date").val();
		        var task = $("#task").val();
		        var description = $("#description").val();
		        var method = $(this).attr("method");
				$.ajax({
					  type: method,
					  url: "rest/gettable/new",
					  data: {task:task,description:description,date:date},
			            success: function(id) {
			            	var row = "";
			            	row+='<tr data-tuser="'+id+'">'+'<td>'+task+'</td>'+'<td>'+description+'</td>'+'<td>'+date+'</td>'+'<td><a href="#" class="edit" >edit</a></td>'+'<td><a href="#" class="deleteRow" >delete</a></td>'+'</tr>';
			            	$('#table').append(row);
			            },
			            error:function() {
			   				alert('error');
			            }
		        });
		    });
		    $('#update').submit(function(e) {
		    	<!--Update task,get parameters from dialog form passing them to server Jersey fo update and update row of table  -->
		    	e.preventDefault();
		        var date = $("#dlg-date").val();
		        var task = $("#dlg-task").val();
		        var description = $("#dlg-description").val();
		        var row = $("#row").val();
		        var a = parseInt(row);
		        var taskid = $("tr:eq( "+a+" )").attr("data-tuser");
		        var method = $(this).attr("method");
				$.ajax({
					  type: method,
					  url: "rest/gettable/update",
					  data: {taskid:taskid,task:task,description:description,date:date},
			            success: function() {
			            	$("tr:eq( "+a+" )").find("td:nth-child(1)").html(task);
					        $("tr:eq( "+a+" )").find("td:nth-child(2)").html(description);
					        $("tr:eq( "+a+" )").find("td:nth-child(3)").html(date);
			            },
			            error:function() {
			   				alert('error');
			            }
		        });
			     $('#dialog-1').dialog('close');
		    });
		    $('#table tbody').on("click",'.deleteRow',function() {
		    	<!--Delete task frome server by id that saved in "data-tuser" atrribute in row and delete from table  -->
		        var tr = $(this).closest('tr');
		        tr.css("background-color","#FF3700");
		        var taskid = tr.attr("data-tuser");
		        $.ajax({
					  type: 'POST',
					  url: "rest/gettable/delete",
					  data: {taskid:taskid},
			            success: function() {
			            	 tr.fadeOut(400, function(){
			 		            tr.remove();
			 		        });
			            },
			            error:function() {
			   				alert('error');
			            }
		        });
		       
		        return false;
		    });
		});
		</script>
<!-- ----------------------- STYLES----------------------------------- -->
 <jsp:include page="styles.jsp"></jsp:include>
<!--------------------------------HEADER------------------------------- -->
 <jsp:include page="head.jsp"></jsp:include>
 <br><br>
</head>
 <!-- Body load table when page loading -->
<body onload="loadtable();">
<!--Form dialog for update task---------------------------- -->
  <div id = "dialog-1" >
         Update task
    <form id="update" class="bs-container" method="POST">
		<p style="color: black;">Date</p>
		<input type="date" id="dlg-date"/>
		<p style="color: black;">Task<p>
		<input type="text" type="text"  id="dlg-task" />
		<p style="color: black;">Description<p>
		<input type="text" type="text" id="dlg-description" />
		<input type="hidden" type="text" id="row" />
		<button class="btn btn-default full-width" type="submit" class="btn btn-default"><span class="glyphicon glyphicon-floppy-save" ></span> Save</button>
	</form>
  </div>
<!-- ----------------Form for create new task --------------------------------   -->
<form id="add" method="POST" >
<div class="jumbotron">
  <div class="container">
    <span class="glyphicon glyphicon-list-alt"></span>
    <br><br><br><br><h1  style="text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;font-size: 400%; ">T<span style="color: yellow;">o </span>D<span style="color: blue;">o</span> List</h1>
   		<!--  <h2>WELCOME<br><br>T<span style="color: yellow;">o </span>D<span style="color: blue;">o</span> List</h2> -->
	 <div class="box">
			<input type="date" id="date" value="2017-01-01"/ >
	   	    <input type="text" id="task"  placeholder="task"/>
            <input type="text" id="description" placeholder="description" />
            <button type="submit"  class="btn btn-default full-width">NEW TASK   <span class="glyphicon glyphicon-hand-up" ></span></button>
	 </div>			
   </div>
</div>
</form>
<!--  ------------------------------------------------------- -->	
<jsp:include page="styles.jsp"></jsp:include>

<!-----------------------------  TABLE OF TASKS          ---------------- --> 
 <div class="jumbotron2">
  <div class="container2" >
   <div class="box2">
	<div id="dtab" >
	 <table id="table" border="3"  style = "background-color:DarkBlue; table-layout: fixed; width:375px;" >
		<tr >
   			<th class="row" height="30px" >Task</th>
   			<th class="row"  >Description</th>
   			<th class="row" height="30px" >Date</th>
   			<th class="row" height="30px" style ="table-layout: fixed; width:5px;" >Edit</th>
   			<th class="row" height="30px" style ="table-layout: fixed; width:7px;" >Delete</th>
   		 </tr>	
	  </table>
    </div>
    </div>
  </div>
</div>

<!-- Java bean uses--------------------------------- -->
	<jsp:setProperty name="name" property="userName" value="${user.userName}"/>
	<h3 style='color:black'><jsp:getProperty name="name" property="userName" /> ,Hello from Java Bean </h3>	
</body>
</html>