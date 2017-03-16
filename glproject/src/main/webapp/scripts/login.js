$('#username, #password').on('input', function() {
	if($('#username').val() == "" || $('#password').val() == "")
		$('#submit').prop("disabled", true);
		// + COLORIER CHAMPS NON REMPLIS EN ROUGE PAR EXEMPLE
	else 
		$('#submit').prop("disabled", false);


$('#submit').click(function() {
	var login = $('#username').val();
	var password = $('#password').val();

	$.ajax({
		url : "ws/login/"+login+'/'+password,
		type : "POST",
		dataType : "json",

		success : function(data) {
			if(data) {
				alert(data);
				$("#span_con").html("good!!");
				console.log("ouii");
			} else {
				alert(data);
				console.log("noon");
			}
		},

		error : function(resultat, statut, erreur) {
			alert("Can't contact servor ");
		}
	});
});