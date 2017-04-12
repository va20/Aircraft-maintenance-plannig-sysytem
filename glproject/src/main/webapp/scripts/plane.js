function getURLParam(param) {
	var pageURL = window.location.search.substring(1);
	var variablesURL = pageURL.split('=');

	return variablesURL[1];
}

function getPlaneTasks() {
	$.ajax({
		url : "ws/planes",
		type : "GET",
		dataType : "json",

		success : function(data) {
			getTasks(data);
		},
	});
}

function getTasks(data) {
	var plane = _.filter(data, function(item) {
		return item.tailNumber == getURLParam("plane");
	});

	$.ajax({
		url : "ws/tasks/" + plane[0].id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		if (data.length == 0) {
			$("#table_tasks").hide();
			$("#search").hide();
			$("#info").show();
		} else {
			$("#info").hide();
			printTasks(data);
		}
	});
}

function printTasks(data) {
	var template = _.template($("#list_tasks").html());
	var task = template({
		"item" : data
	});

	$("#tasks").append(task);
}

$(document).ready(function() {
	document.getElementById("plane_number").innerHTML = getURLParam("plane");
	getPlaneTasks();
});