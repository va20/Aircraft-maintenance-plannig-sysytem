function checkTasks() {
	$.ajax({
		url : "ws/tasks",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		setTasks(data);

		_.each(data, function(item) {
			if (item.warning == "ORANGE")
				$("#" + item.idPlane).addClass("btn-orange");
			else if (item.warning == "RED")
				$("#" + item.idPlane).addClass("btn-red");
		});
	});
}

function setTasks(data) {
	var tab = {};
	
	_.each(data, function(item) {
		if (_.indexOf(_.keys(tab), item.idPlane.toString()) != -1)
			tab[item.idPlane].push(item);
		else
			tab[item.idPlane] = [item];
	});
	
	$.ajax({
		url : "ws/flights",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		_.each(data, function(flight) {
			if (_.indexOf(_.keys(tab), flight.idPlane
					.toString()) != -1) {
				_.each(tab[flight.idPlane], function(task) {
					if (task.status == "NDONE" && $.now() > task.deadline)
						task.warning = "ORANGE";										
					else if (task.status == "NDONE" && $.now() > task.deadline && (flight.depTime - $.now()) < 60000)
						task.warning = "RED";	
					
					$.ajax({
						url : "ws/tasks/" + task.id,
						type : "POST",
						contentType : "application/json",
						dataType : "json",
						data : JSON.stringify(task),

						success : function(data) {
						}
					});
				});
			}
		});
	});
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
}

$(document).ready(function() {
	getPlanes();
	checkTasks();

	$("div").on("click", ".col-lg-3", function(e) {
		var tailNumber = $(this).attr("id");
		location.href = "plane_page.html?plane=" + tailNumber;
	});
});