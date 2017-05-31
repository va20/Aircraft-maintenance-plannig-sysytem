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
		else if (task.warning == "GREEN")
		    $("#" + task.id).css("background-color", "rgba(144, 238, 144, 0.4)");
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
	
	$("#tableTasks tbody").on("click", "tr", function() {
		tn = $(this).find("td").eq(2).html();
		getGenericTask(tn);
		idTask = $(this).attr("id");
		plane = $(this).find("td").eq(0).html();
		mro = $.cookie("login");
		dl = $(this).find("td").eq(3).html();
		typeTask = $(this).find("td").eq(4).html();
		console.log(tn);
	});

	$("#done").on("click", function() {
	console.log(plane);
		console.log(idTask);

		var task = {
			id : idTask,
			idPlane : plane,
			idMro : mro,
			deadline : dl,
			taskNumber : tn,
			type : typeTask,
			status : "DONE",
			warning : "GREEN"
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
        }, 500)
			
		});
	});
});