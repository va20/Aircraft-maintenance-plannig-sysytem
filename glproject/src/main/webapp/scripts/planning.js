var tab = {};

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

function getMRO(id) {
	$.ajax({
		url : "ws/mro/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		$("." + id).html(data.name);

		// for mro filter
		$(".tasks").attr("data-mro", data.name);
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

	// MRO name
	_.each(data, function(item) {
		getMRO(item.idMro);
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

function deleteTask(idTask) {
	$.ajax({
		url : "ws/tasks/" + idTask,
		type : "DELETE",
		dataType : "json",

		success : setTimeout(function() {
			location.reload(true);
		}, 400)
	});
}

function filtering(select) {
	$(select).change(function() {
		var $rows = $("#tableTasks").children('tbody').children('tr');

		if ($(select + " option:selected").val() == "default") {
			$.each($rows, function() {
				$(this).show();
			});

		} else {
			$.each($rows, function() {
				var match = false;
				$(this).children('td').each(function() {
					if ($(this).text() == $(select).val())
						match = true;
				});

				if (match)
					$(this).show();
				else
					$(this).hide();
			});
		}
	});
}

function planesFilter() {
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

		$("#plane-filter").append(plane);
		$(".selectpicker").selectpicker('refresh');

		filtering("#plane-filter");
	});
}

function mroFilter() {
	$.ajax({
		url : "ws/mro",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		var tab = _.uniq(_.map(data, function(item) {
			return item.name;
		}));

		var template = _.template($("#listMROs").html());
		var mro = template({
			"item" : tab
		});

		$("#mro-filter").append(mro);
		$(".selectpicker").selectpicker('refresh');

		filtering("#mro-filter");
	});
}

$(document).ready(
		function() {
			getTasks();

			planesFilter();
			mroFilter();

			// edit page
			$("#tasks").on(
					"click",
					"a.btn-warning",
					function() {
						var id = $(this).attr("id");
						var planeId = $("#plane").attr("class");
						location.href = "edit_task.html?plane="
								+ $("#plane_number").html() + "&task=" + id;
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