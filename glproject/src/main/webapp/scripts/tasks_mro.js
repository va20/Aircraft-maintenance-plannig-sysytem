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

	//if (data.status != "DONE") 
		$("#tasks").append(task);

	_.each(data, function(task) {
		if (task.warning == "ORANGE")
			$("#" + task.id).css("background-color", "rgba(255, 110, 0, 0.4)");
		else if (task.warning == "RED")
			$("#" + task.id).css("background-color", "rgba(255, 0, 0, 0.4)");
	});
}

function deleteTask(id) {
	$.ajax({
		url : "ws/tasks/"+ id,
		type : "DELETE",
		dataType : "json"
	}).done(function(data) {
	});
}

$(document).ready(function() {
	getTasks();

	var idTask;
	var plane;
	var mro;
	var dl;
	var tn;
	var typeTask;

	$("#tableTasks").on("click", "tr.tasks", function() {
		getGenericTask($("#taskNumber").html());
		idTask = $(this).attr("id");
		plane = $("#planeId").html();
		mro = $.cookie("login");
		dl = $("#deadline").html();
		tn = $("#taskNumber").html();
		typeTask = $("#type").html();
	});

	$("#done").on("click", function() {
		var task = {
			id : idTask,
			idPlane : plane,
			idMro : mro,
			deadline : dl,
			taskNumber : tn,
			type : typeTask,
			status : "DONE"
		}
		
		$.ajax({
			url : "ws/tasks/" + idTask,
			type : "POST",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(task)
		}).done(function(data) {
			setTimeout(function(data) {
            window.location.reload();
        },500)
			
		});
	});
});