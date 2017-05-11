function getURLParam(param) {
	var pageURL = window.location.search.substring(1);
	var variablesURL = pageURL.split('=');
	return variablesURL[1];
}

function getMRO(id) {
	$.ajax({
		url : "ws/mro/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		$("." + id).html(data.name);
	});
}

function getPlaneTasks() {
	$.ajax({
		url : "ws/planes",
		type : "GET",
		dataType : "json",
	}).done(function(data) {
		getTasks(data);
	});
}

function getTasks(data) {
	var plane = _.filter(data, function(item) {
		return item.tailNumber == getURLParam("plane");
	});

	planeId = plane[0].id;

	$.ajax({
		url : "ws/planes/" + planeId + "/tasks",
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

	// MRO name
	_.each(data, function(item) {
		getMRO(item.idMRO);
	});

	$("#tasks").append(task);

	_.each(data, function(task) {
		if (task.warning == "ORANGE")
			$("#" + task.id).css("background-color", "rgba(255, 110, 0, 0.4)");
		else if (task.warning == "RED")
			$("#" + task.id).css("background-color", "rgba(255, 0, 0, 0.4)");
	});
}

function deleteTask(idTask) {
	$.ajax({
		url : "ws/tasks/" + idTask,
		type : "DELETE",
		dataType : "json",
		seccess : setTimeout(function() {
			location.reload(true);
		}, 400)
	});
}

$(document).ready(
		function() {
			$("#plane_number").html(getURLParam("plane"));

			getPlaneTasks();

			// edit page
			$("#tasks").on(
					"click",
					"a.btn-warning",
					function() {
						var id = $(this).attr("id");
						location.href = "edit_task.html?plane="
								+ $("#plane_number").html() + "&task=" + id;
					});

			// delete
			$("#tasks").on("click", "a.btn-danger", function() {
				var idTask = $(this).attr("id");
				if (confirm("Are you sure ?")) {
					deleteTask(idTask);
				}
			});

			// add page
			$("#add").click(function() {
				location.href = "add_task.html?plane=" + getURLParam("plane");
			});
		});