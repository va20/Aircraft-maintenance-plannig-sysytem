function getPlane(id) {
	$.ajax({
		url : "ws/planes/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		$("." + id).html(data.tailNumber);
	});
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

function getTasks() {
	$.ajax({
		url : "ws/tasks",
		type : "GET",
		dataType : "json"
	}).done(
			function(data) {
				printTasks(data);

				// pagination
				Pagination("#tablePaging",
						{
							itemsCount : data.length,
							onPageChange : function(paging) {
								var start = paging.pageSize
										* (paging.currentPage - 1), end = start
										+ paging.pageSize, $rows = $(
										"#tableTasks").find(".tasks");

								$rows.hide();

								for (var i = start; i < end; i++)
									$rows.eq(i).show();
							}
						});
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

	// MRO name
	_.each(data, function(item) {
		getMRO(item.idMRO);
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
	var template = _.template($("#genericTaskModal").html());
	var genericTask = template({
		"item" : data
	});

	$("#genericTaskListInfo").append(genericTask);
}

function filters() {
	$.ajax({
		url : "ws/planes",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		var tab = _.uniq(_.map(data, function(item) {
			return item.tailNumber;
		}));

		var template = _.template($("#listPlanes").html());
		var plane = template({
			"item" : tab
		});

		$("#selectPlanes").append(plane);
		$(".selectpicker").selectpicker('refresh');
	});
}

$(document).ready(function() {
	getTasks();

	filters();
	$("#selectPlanes").change(function() {
		var $rows = $("#tableTasks").children('tbody').children('tr');

		if ($("#selectPlanes option:selected").val() == "default") {
			$.each($rows, function() {
				$(this).show();
			});

		} else {
			$.each($rows, function() {
				var match = false;
				$(this).children('td').each(function() {
					if ($(this).text() == $("#selectPlanes").val())
						match = true;
				});

				if (match)
					$(this).show();
				else
					$(this).hide();
			});
		}
	});

	// retrieve taskNumber
	$("#tableTasks").on("click", "tr.tasks", function() {
		getGenericTask($(this.cells[1]).text());
	});

	// remove <li> in modal when hidden
	$("#genericTaskInfo").on("hidden.bs.modal", function() {
		$(".modal-body > li").remove();
	})

	// edit page
	$("#tasks").on("click", "a.btn-info", function() {
		location.href = "edit_task.html";
	});

	// confirm delete
	$("#tasks").on("click", "a.btn-danger", function() {
		var idTask = $(this).attr("id");
		if (confirm("Are you sure ?")) {
			deleteTask(idTask);
		}
	});

	// add button
	$("#add").click(function() {
		location.href = "add_task.html";
	});
});