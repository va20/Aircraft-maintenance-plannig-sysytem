function getFlights() {
	$.ajax({
		url : "ws/flights",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printFlights(data);
	});
}

function printFlights(data) {
	var template = _.template($("#list_flights").html());
	var flight = template({
		"item" : data
	});

	$("#flights").append(flight);
}

$(document).ready(function() {
	getFlights();
});
