(function($) {
	$(document).ready(
			function() {
				$('ul.dropdown-menu [data-toggle=dropdown]').on('click',
						function(event) {
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

				// upload file
				$("#upload").change(function() {
					var file = $("#upload")[0].files[0];

					if (file.name.split(".")[1] != "xlsx")
						alert("ERROR : WRONG FILE EXTENSION !");
					else {
						var fd = new FormData();
						fd.append("file", file);
						console.log(fd);
						console.log(file);

						$.ajax({
							url : "ws/mpd",
							type : "POST",
							data : fd,
							processData : false,
							contentType : false,

							success : function(data) {
								alert("MPD uploaded for " + file.name);
							},
							error : function(data) {
								alert("ERROR !!!");
							}
						});
					}
				});
			});
})(jQuery);