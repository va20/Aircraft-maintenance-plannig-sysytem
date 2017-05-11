function getURLParam(param) {
	var pageURL = window.location.search.substring(1);
	var variablesURL = pageURL.split('=');

	return variablesURL[1];
}

function retrieveData(ws) {
	$.ajax({
		url : ws,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		if (ws == "ws/planes")
			printPlanes(data)
		else if (ws == "ws/mpd")
			printGenericTasks(data);
		else if (ws == "ws/mro")
			printMROs(data);
	});
}

function printTasks(data) {
	var template = _.template($("#list_tasks").html());
	var task = template({
		"item" : data
	});
	$("#tasks").append(task);
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
	var tailNumber = getURLParam("plane")
	$("#planes option").html(tailNumber);

	retrieveData("ws/planes");
	retrieveData("ws/mpd");
	retrieveData("ws/mro");

	$("#add").on("click", function() {
		var id;
		$("#planes option").each(function() {
			if ($(this).html() == tailNumber) {
				id = $(this).val();
				return;
			}
		});

		var task = {
			id : Math.round(new Date().getTime() + (Math.random() * 100)),
			idPlane : id,
			idMRO : $("#mros option:selected").val(),
			deadline : moment($("#deadline").val()),
			taskNumber : $("#tasks option:selected").text(),
			type : $('input[name="radio"]:checked').val()
		};

		$.ajax({
			url : "ws/tasks",
			type : "PUT",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(task),
		}).done(function(data) {
			location.href = "plane_page.html?plane=" + tailNumber;
		});
	});
});