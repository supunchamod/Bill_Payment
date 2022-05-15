$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();

});

function validatePaymentForm() {
	// CODE
	if ($("#payName").val().trim() == "") {
		return "Insert Customer Name.";
	}

	// PRICE-------------------------------
	if ($("#description").val().trim() == "") {
		return "Insert Payment Description.";
	}
	if ($("#date").val().trim() == "") {
		return "Insert Payment Bill Date.";
	}
	
	// UNIT PRICE------------------------
	let Price = $("#price").val().trim();
	if (!$.isNumeric(Price)) {
		return "Insert Payment Price.";
	}

	return true;

}

//Save Func
function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidCustomerIDSave").val("");
	$("#formPayment")[0].reset();
}


// Save Btn
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------  
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------  
	var status = validatePaymentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------  
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "PaymentServlet",
			type: type,
			data: $("#formPayment").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onPaymentSaveComplete(response.responseText, status);
			}
		});
});


// UPDATE CLICK
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());
	$("#payName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#description").val($(this).closest("tr").find('td:eq(1)').text());
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());
	$("#price").val($(this).closest("tr").find('td:eq(3)').text());
});


//Delete Func
function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// DELETE Click
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "PaymentServlet",
			type: "DELETE",
			data: "U_id=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});
