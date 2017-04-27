function getURLParam(param) {
    var pageURL = window.location.search.substring(1);
    var variablesURL = pageURL.split('=');

    return variablesURL[1];
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

/*function getPlaneTasks() {
    $.ajax({
        url : "ws/planes",
        type : "GET",
        dataType : "json",

        success : function(data) {
            getTasks(data);
        },
    });
}*/

function getTasks(data) {
    var plane = _.filter(data, function(item) {
        return item.tailNumber == getURLParam("plane");
    });

    $.ajax({
        url : "ws/tasks/" + plane[0].id,
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

    $("#tasks").append(task);
}


function printPlanes(data) {
	var template = _.template($("#list_planes").html());
	var plane = template({
		"item" : data
	});

	$("#planes").append(plane);
	
	//update selectpicker
	$('.selectpicker').selectpicker('refresh');
}

$(document).ready(function() {
    document.getElementById("plane_number").innerHTML = getURLParam("plane");
    //getPlaneTasks();
	getPlanes();
});