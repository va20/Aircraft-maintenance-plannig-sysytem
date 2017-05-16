function getURLParam(param) {
	var pageURL = window.location.search.substring(1);
	var variablesURL = pageURL.split('&');

	if (param == "plane")
		return variablesURL[0].split('=')[1];

	return variablesURL[1].split('=')[1];
}

function fillFields() {
	var tailNumber = getURLParam("plane");
	var taskId = getURLParam("task");

	$.ajax({
		url : "ws/tasks/" + taskId,
		type : "GET",
		dataType : "json"
	}).done(function(data) {

		$("#planes").val(data.idPlane);
		$("#tasks").val(data.taskNumber);
		$("#mros").val(data.idMro);
		$("#deadline").val(data.deadline);
		$(".selectpicker").selectpicker('refresh');

		if (data.type == "base")
			$("#radio2").prop('checked', true);
		else if (data.type == "inline")
			$("#radio1").prop('checked', true);
	});
}

function retrieveData(ws) {
	$.ajax({
		url : ws,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		if (ws == "ws/planes")
			printPlanes(data);
		else if (ws == "ws/mpd")
			printGenericTasks(data);
		else if (ws == "ws/mro")
			printMROs(data);
	});
}

function printPlanes(data) {
	var template = _.template($("#listPlanes").html());
	var plane = template({
		"item" : data
	});

	$("#planes").append(plane);
	$(".selectpicker").selectpicker('refresh');
}

function printGenericTasks(data) {
	var template = _.template($("#listTasks").html());
	var genericTask = template({
		"item" : data
	});

	$("#tasks").append(genericTask);
	$(".selectpicker").selectpicker('refresh');
}

function printMROs(data) {
	var template = _.template($("#listMROs").html());
	var plane = template({
		"item" : data
	});

	$("#mros").append(plane);
	$(".selectpicker").selectpicker('refresh');
}

$(document).ready(function() {
	$("#plane_number").html(getURLParam("plane"));

	retrieveData("ws/mpd");
	retrieveData("ws/mro");
	retrieveData("ws/planes");

	setTimeout(function() {
		fillFields();
	}, 100);

	$("#edit").click(function() {
		var idTask = getURLParam("task");

		var task = {
			id : idTask,
			idPlane : $("#planes option:selected").val(),
			idMro : $("#mros option:selected").val(),
			deadline : moment($("#deadline").val()).valueOf(),
			taskNumber : $("#tasks option:selected").text(),
			type : $('input[name="radio"]:checked').val()
		};

		$.ajax({
			url : "ws/tasks/" + idTask,
			type : "POST",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(task),

			success : function(data) {
				//var tailNumber = $("#planes option:selected").text();
				location.href = "maintenance_planning.html";
			},
			error : function(res, stat, err) {
				alert("ERROR PUT TASK");
			}
		});
	});
});