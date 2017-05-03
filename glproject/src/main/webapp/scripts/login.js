$(document).ready(function() {
	localStorage.clear();

	$('#username, #password').on('input', function() {
		if ($('#username').val() == "" || $('#password').val() == "")
			$('#submit').prop("disabled", true);
		else
			$('#submit').prop("disabled", false);
	});

	$('#submit').click(function() {
		var login = $("#username").val();
		var password = $("#password").val();

		$.ajax({
			url : "ws/staffs/" + login + '/' + password,
			type : "POST",
			dataType : "json",

			success : function(data) {
				if (data) {
					$.cookie('name', login);
					localStorage.setItem(login, password);
					location.href = "/plane_list.html";
				} else
					alert("Password is wrong");
			},

			error : function(resultat, statut, erreur) {
				alert("The specified login does not exist");
			}
		});
	});
});