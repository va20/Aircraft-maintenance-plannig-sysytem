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

	console.log(plane);
	$("#planes").append(plane);
}

$(document).ready(function() {
	getPlanes();

	$("div").on("click", "a.btn", function(e) {
		var tailNumber =  $(this).attr("id");
		console.log(tailNumber);
		location.href = "plane_page.html?plane=" + tailNumber;
	});
});
