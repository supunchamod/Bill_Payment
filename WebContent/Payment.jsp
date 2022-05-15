<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.Payment"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>

	<div class="container">
		<div class="card">
			<div class="d">
			<center>
			<h1>Payment Bill Details</h1>
			</center>
				
			</div>
			<div class="card-body">
				<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">
					Customer Name: <input id="payName" name="payName" type="text"
						class="form-control form-control-sm"> <br> 
						Payment Description: <input
						id="description" name="description" type="text"
						class="form-control form-control-sm"> <br>
						Payment Date: <input
						id="date" name="date" type="date"
						class="form-control form-control-sm"> <br> 
						Payment Price
					: <input id="price" name="price" type="text"
						class="form-control form-control-sm"> <br>
					<div class="text-right">
						<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> <input type="hidden"
							id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
					</div>
				</form>
				<div id="alertSuccess" class="alert alert-success" style="margin-top: 15px"></div>
				<div id="alertError" class="alert alert-danger" style="margin-top: 15px"></div>
				<div id="divItemsGrid" class="table-responsive">
					
				</div>
			</div>

		</div>
		//frontend
		
		
		<%
					
					
		             Payment PaymentObj = new Payment();
					
					out.print(PaymentObj.viewPayment());
					%>
	</div>
</body>
</html>