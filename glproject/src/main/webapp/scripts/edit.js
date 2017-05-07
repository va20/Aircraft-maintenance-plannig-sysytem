function getURLParam(param) {
	var pageURL = window.location.search.substring(1);
	var variablesURL = pageURL.split('&');

	if (param == "planeTailNumber")
		return variablesURL[0].split('=')[1];
	else if (param == "planeId")
		return variablesURL[1].split('=')[1];

	return variablesURL[2].split('=')[1];
}

function fillFields() {
	var tailNumber = getURLParam("planeTailNumber");
	var planeId = getURLParam("planeId");
	var taskId = getURLParam("task");

	$.ajax({
		url : "ws/tasks/" + planeId + "/" + taskId,
		type : "GET",
		dataType : "json"
	}).done(function(data) {

        $("#planes").val(data.idPlane);
		$("#tasks").val(data.taskNumber);
		$("#mros").val(data.idMRO);
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
	$("#plane_number").html(getURLParam("planeTailNumber"));

    retrieveData("ws/planes");
    retrieveData("ws/mpd");
    retrieveData("ws/mro");
    // **** UPDATE AU LIEU DE ADD ICI **** //

    fillFields();
	$("#add").click(function() {
		var task = {
			id : new Date().getUTCMilliseconds(),
			idPlane : $("#planes option:selected").val(),
			idMRO : $("#mros option:selected").val(),
			deadline : moment($("#deadline").val()),
			taskNumber : $("#tasks option:selected").text(),
			type : $('input[name="radio"]:checked').val()
		};

		console.log(JSON.stringify(task));

		$.ajax({
			url : "ws/tasks",
			type : "POST",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(task),

			success : function(data) {
				var tailNumber = $("#planes option:selected").text();
				location.href = "plane_page.html?plane=" + tailNumber;
			},
			error : function(res, stat, err) {
				alert("ERROR PUT TASK");
			}
		});
	});
});