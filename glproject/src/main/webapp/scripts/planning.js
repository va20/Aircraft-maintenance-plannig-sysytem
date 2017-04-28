function getTasks() {
	$.ajax({
		url : "ws/tasks",
		type : "GET",
		dataType : "json"
	}).done(function(data) {
		printTasks(data);
	});
}

function printTasks(data) {
	var template = _.template($("#list_tasks").html());
	var task = template({
		"item" : data
	});
	$("#tasks").append(task);
}

function deleteTask(idTask) {
    $.ajax({
		url : "ws/tasks/" + idTask,
		type : "DELETE",
		dataType : "json"
	}).done(function() {
        location.reload(true);
    });
}

$(document).ready(function() {
	getTasks();
    $("#tasks").on("click", "a.btn-danger", function() {
        var idTask = $(this).attr("id");
        if (confirm("Are you sure ?")) {
            deleteTask(idTask);
        }
	});

	$("#add").click(function() {
		location.href = "add_task.html";
	});
});