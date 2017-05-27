function getFlights() {
	$.ajax({
		url : "ws/flights",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printFlights(data);
	});
}

function getPlane(id) {
	$.ajax({
		url : "ws/planes/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		$("." + id).html(data.tailNumber);
	});
}

function printFlights(data) {
	var template = _.template($("#list_flights").html());
	var flight = template({
		"item" : data
	});

	_.each(data, function(item) {
		getPlane(item.idPlane);
	});

	$("#flights").append(flight);
}

$(document).ready(function() {
	getFlights();
});
