$( ".uo-line" ).click(function() {
  var lines = document.getElementsByClassName("uo-line");
	for(var i = 0; i < lines.length; i++){
  	$(lines[i]).removeClass("uo-line-selected");
	}
  $(this).addClass("uo-line-selected");
});