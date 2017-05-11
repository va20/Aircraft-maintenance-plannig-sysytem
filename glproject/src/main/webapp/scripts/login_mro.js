localStorage.clear();

$('#username, #password').on('input', function() {
	if ($('#username').val() == "" || $('#password').val() == "")
		$('#submit').prop("disabled", true);
	// + COLORIER CHAMPS NON REMPLIS EN ROUGE PAR EXEMPLE
	else
		$('#submit').prop("disabled", false);
});

$('#submit').click(function() {
	var login = $('#username').val();
	var password = $('#password').val();
	console.log(login + " " + password);

	$.ajax({
		url : "ws/mro/" + login + '/' + password,
		type : "POST",
		dataType : "json",

		success : function(data) {
			console.log(data);
			if (data) {
				$.cookie("login", login);
				$.cookie("mroId", data.id);
				localStorage.setItem(login, password);
				location.href = "/homepage_mro.html";
			} else
				alert("Password is wrong");
		},

		error : function(resultat, statut, erreur) {
			alert("The specified login does not exist");
		}
	});
});