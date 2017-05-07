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

	$("#genericTaskListInfo").append(genericTask);
}

$(document).ready(function() {

	// remove <li> in modal when hidden
	$("#genericTaskInfo").on("hidden.bs.modal", function() {
		$(".modal-body > li").remove();
	})

	// retrieve taskNumber
	$("#tasks").on("click", "a.btn-info", function() {
		var taskNumber = $(this).attr("id");
		getGenericTask(taskNumber);
	});
});