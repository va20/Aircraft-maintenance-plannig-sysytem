function getURLParam(param) {
    var pageURL = window.location.search.substring(1);
    var variablesURL = pageURL.split('&');
    if(param =="plane"){
        return variablesURL[0].split('=')[1];
    }
    return variablesURL[1].split('=')[1];

}

function getPlanes() {
	$.ajax({
		url : "ws/planes",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printPlanes(data);
	});
}

function getTask() {
    var task_id=getURLParam("task");
    var plane_id=getURLParam("plane");
	$.ajax({
		url : "ws/tasks/" +plane_id+ "/" +task_id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		if (data.length == 0) {
			$("#tableTasks").hide();
			$("#search").hide();
			$("#info").show();
		} else {
			$("#info").hide();

			$("#planes").val(data.idPlane).change();

			$("#tasks").val(data.taskNumber).change();

            $("#mros").val(data.idMRO).change();

            $("#deadline").val(data.deadline).change();

            if(data.type=="base"){
                $("#radio2").prop('checked',true);
            }
            else if(data.type=="inline"){
                $("#radio1").prop('checked',true);
            }
		}
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
    document.getElementById("plane_number").innerHTML = getURLParam("plane");
    getPlanes();
	retrieveData("ws/planes");
    retrieveData("ws/mpd");
    retrieveData("ws/mro");
    getTask();

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
			type : "PUT",
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