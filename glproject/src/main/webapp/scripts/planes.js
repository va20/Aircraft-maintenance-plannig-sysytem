function getPlanes() {
	$.ajax({
		url : "ws/planes",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		setPlanes(data);
	});
}

function setPlanes(data) {
	var template = _.template($("#list_planes").html());
	var plane = template({
		"item" : data
	});
	
	$("#planes").append(plane);
}

$(document).ready(function() {
	getPlanes();
});
