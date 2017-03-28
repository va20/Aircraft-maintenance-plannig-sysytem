$('#username, #password').on('input', function() {
	if($('#username').val() == "" || $('#password').val() == "")
		$('#submit').prop("disabled", true);
		// + COLORIER CHAMPS NON REMPLIS EN ROUGE PAR EXEMPLE
	else 
		$('#submit').prop("disabled", false);
});


$('#submit').click(function() {
	var login = $('#username').val();
	var password = $('#password').val();
	console.log(login+" "+password);
	
	$.ajax({
		url : "ws/staff/"+login+'/'+password,
		type : "POST",
		dataType : "json",

		success : function(data) {
			console.log(data);
			
			if(data) {
				$.cookie('name', login);
				location.href = "/plane_list.html";
			} else
				$("#span_con").html("ERREUR CHAMPS!!");			
		},

		error : function(resultat, statut, erreur) {
			alert("ERROR !!!");
		}
	});
});