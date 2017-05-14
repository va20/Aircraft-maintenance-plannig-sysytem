function getPlane(id) {
	$.ajax({
		url : "ws/planes/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		$("." + id).html(data.tailNumber);

		// for plane filter
		$(".tasks").attr("data-plane", data.tailNumber);
	});
}

function getTasks() {
	$.ajax({
		url : "ws/mro/" + $.cookie("login") + "/tasks",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printTasks(data);
	});
}

function printTasks(data) {
	var template = _.template($("#list_tasks").html());
	var task = template({
		"item" : data
	});

	// Plane tailNumber
	_.each(data, function(item) {
		getPlane(item.idPlane);
	});

	$("#tasks").append(task);

	_.each(data, function(task) {
		if (task.warning == "ORANGE")
			$("#" + task.id).css("background-color", "rgba(255, 110, 0, 0.4)");
		else if (task.warning == "RED")
			$("#" + task.id).css("background-color", "rgba(255, 0, 0, 0.4)");
	});
}

$(document).ready(function() {
	getTasks();

	$("#tableTasks").on("click", "tr.tasks", function() {
		getGenericTask($(this.cells[1]).text());
	});
});