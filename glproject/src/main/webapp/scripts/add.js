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

	getPlanes();
});