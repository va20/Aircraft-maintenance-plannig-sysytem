function getGenericTask(taskNumber) {
	$.ajax({
		url : "ws/mpd/" + taskNumber,
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printGenericTask(data);
	});
}

function printGenericTask(data) {
	var template = _.template($("#genericTaskModal").html());
	var genericTask = template({
		"item" : data
	});

	console.log(genericTask);
	$("#genericTaskListInfo").append(genericTask);
}

$(document).ready(function() {

	// remove <li> in modal when hidden
	$("#genericTaskInfo").on("hidden.bs.modal", function() {
		$(".modal-body > li").remove();
	})

	// retrieve taskNumber
	$("#tableTasks").on("click", "tr.tasks", function() {
		getGenericTask($(this.cells[1]).text());
	});
});