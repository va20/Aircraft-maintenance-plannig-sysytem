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
}

$(document).ready(function() {
	getPlanes();

	var tab = [];
	var orange = [];

	$.ajax({
		url : "ws/tasks",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		tab = _.filter(data, function(item) {
			return item.warning != "NONE";
		});

		_.each(tab, function(item) {
			if (item.warning == "ORANGE") {
				$("#" + item.idPlane).addClass("btn-orange");
			} else if (item.warning == "RED") {
				$("#" + item.idPlane).addClass("btn-red");
			}
		});
	});

	$("div").on("click", ".col-lg-3", function(e) {
		var tailNumber = $(this).attr("id");
		location.href = "plane_page.html?plane=" + tailNumber;
	});
});