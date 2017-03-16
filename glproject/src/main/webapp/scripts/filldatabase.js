var firstMember = {id : 0, firstname : "Chris", lastname : "Dionisio", login : "chris", password : "pwd"}
$.ajax({
	url : "ws/login",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(firstMember),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});






/*********************************************************************************/

var plane1 = {id : 0, type : "Boeing"}
$.ajax({
	url : "ws/planes",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(plane1),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});


var plane2 = {id : 0, type : "Airbus"}
$.ajax({
	url : "ws/planes",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(plane2),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});

var plane3 = {id : 0, type : "Concorde"}
$.ajax({
	url : "ws/planes",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(plane3),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});

var plane4 = {id : 0, type : "titanic"}
$.ajax({
	url : "ws/planes",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(plane4),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});