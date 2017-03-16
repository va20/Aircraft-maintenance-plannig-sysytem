$(function() {
	$('#username, #password').on('input',function() {
		if($('#username').val() != "" && $('#password').val() != "") {

			$("#submit").click(function() {
				var login = $('#username').val();
				var password = $('#password').val();
				
				$.ajax({
					url : "ws/login/"+login+"/"+password,
					type : "POST",
					dataType : "json",

					success : function(data) {
						if(data) {
							$("#span_con").html("Correct data");
							console.log("ouiii");
						} else {
							$("#span_con").html("Incorrect data");
						}
					},

					error : function(resultat, statut, erreur) {
								alert("Error");
							}
				});
			});
		} else {
			console.log("remplir les champs svp");
		}
	});
});