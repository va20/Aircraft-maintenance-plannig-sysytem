$(document).ready(function() {
	$('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$(this).parent().siblings().removeClass('open');
		$(this).parent().toggleClass('open');
	});

	// import button
	$("#import").on('click', function(e) {
		e.preventDefault();
		$("#upload:hidden").trigger('click');
	});

    $("#import_flights").on('click', function(e) {
        e.preventDefault();
        $("#upload_flights:hidden").trigger('click');
    });

	// upload file
	$("#upload").change(function() {
		var file = $("#upload")[0].files[0];

		if (file.name.split(".")[1] != "xlsx")
			alert("ERROR : WRONG FILE EXTENSION !");
		else {
			var fd = new FormData();
			fd.append("file", file);

			$.ajax({
				url : "ws/mpd",
				type : "PUT",
				data : fd,
				cache : false,
				processData : false,
				contentType : false,

				success : function(data) {
					alert("MPD uploaded for " + file.name.split(".")[0]);
					location.href = "plane_list.html";
				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log(xhr.status);
					console.log(xhr.responseText);
					console.log(thrownError);
				}
			});
		}
	});

	// upload flight list

	$("#upload_flights").change(function(){
		var flights_file=$("#upload_flights")[0].files[0];

		if(flights_file.name.split(".")[1] != "xlsx"){
			alert("ERROR : WRONG FILE EXTENSION !");
		}
		else {
			var fd_flights=new FormData();
			fd_flights.append("file",flights_file);

			$.ajax({
				url: "ws/flights",
				type: "PUT",
				data: fd_flights,
				cache: false,
				processData: false,
				contentType: false,
				success: function(data){
					alert("Flights list uploaded for "+flights_file.name.split(".")[0]);
					location.href= "flight_list.html";
				},
				error: function(xhr,ajaxOptions,thrownError){
                    console.log(xhr.status);
                    console.log(xhr.responseText);
                    console.log(thrownError);
				}
			});
		}
	});
});