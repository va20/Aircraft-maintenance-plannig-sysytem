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
			$("#tableTasks").hide();
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

function deleteTask(idTask) {
	$.ajax({
		url : "ws/tasks/" + idTask,
		type : "DELETE",
		dataType : "json"
	}).done(function() {
		location.reload(true);
	});
}

$(document).ready(function() {
	document.getElementById("plane_number").innerHTML = getURLParam("plane");
	getPlaneTasks();

	$("#tasks").on("click", "a.btn-danger", function() {
		var idTask = $(this).attr("id");
		if (confirm("Are you sure ?")) {
            deleteTask(idTask);
        }
    });

	$("#tasks").on("click", "a.btn-info", function () {
        var taskNumber =  $(this).attr("id");
        console.log(taskNumber);
        location.href = "add_task.html?plane="+ getURLParam("plane")+"&taskNumber="+ taskNumber;
    });

	$("#add").click(function() {
		location.href = "add_task.html?plane=" + getURLParam("plane");
	});
});