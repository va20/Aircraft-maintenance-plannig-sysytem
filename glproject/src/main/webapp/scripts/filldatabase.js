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
