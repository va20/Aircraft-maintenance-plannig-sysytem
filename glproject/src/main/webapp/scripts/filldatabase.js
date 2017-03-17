var firstMember = {id : 0, firstname : "Chris", lastname : "Dionisio", login : "chris", password : "pwd"}
$.ajax({
	url : "ws/staff",
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

var plane1 = {id : 0, type : "Airbus A320"}
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


var plane2 = {id : 1, type : "Airbus A320"}
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

var plane3 = {id : 2, type : "Boeing 747"}
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

var plane4 = {id : 3, type : "Boeing 747"}
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

var plane5 = {id : 4, type : "Boeing 777"}
$.ajax({
	url : "ws/planes",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(plane5),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});

var plane6 = {id : 5, type : "MD-80"}
$.ajax({
	url : "ws/planes",
	type : "PUT",
	contentType : "application/json",
	dataType:  "json",
	data : JSON.stringify(plane6),

	success : function(data) {
		console.log("success");
	},
	error: function(res, stat, err) {
		console.log("error");
	}
});