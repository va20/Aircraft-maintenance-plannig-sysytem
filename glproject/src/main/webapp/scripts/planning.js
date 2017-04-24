function getTasks() {
	$.ajax({
		url : "ws/tasks",
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

	$("#tasks").append(task);
}

function deleteTask(idTask) {
	$.ajax({
		url : "ws/tasks/" + idTask,
		type : "DELETE",
		dataType : "json",

		success : function(data) {
			getTasks(data);
		}
	});
}

function getGenericTask(taskNumber) {
	$.ajax({
		url : "ws/mpd/" + taskNumber,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printGenericTask(data);
	});
}

function printGenericTask(data) {
	console.log(JSON.stringify(data));
	var template = _.template($("#genericTaskModal").html());
	var genericTask = template({
		"item" : data
	});

	$("#genericTaskListInfo").append(genericTask);
}

$(document).ready(function() {
	getTasks();

	// retrieve taskNumber
	$("#tableTasks").on("click", "tr", function() {
		getGenericTask($(this.cells[2]).text());
	});

	// remove <li> in modal when hidden
	$("#genericTaskInfo").on("hidden.bs.modal", function() {
		$(".modal-body > li").remove();
	})

	// confirm delete
	$("#tasks").on("click", "a.btn-danger", function() {
		var idTask = $(this).attr("id");
		if (confirm("Are you sure ?")) {
			deleteTask(idTask);
		}
	});

	// modal settings
	$("#genericTaskInfo").modal({
		escapeClose : true,
		clickClose : true,
		fadeDuration : 30,
		showClose : true,
		keyboard : true,
		show : false
	});

	// add button
	$("#add").click(function() {
		location.href = "add_task.html";
	});
});