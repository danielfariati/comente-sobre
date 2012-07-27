function isEnter(evt) {
   	return ((evt.keyCode || evt.which) == 13) ? true : false;
};

function showError(message) {
	if ($('#error-wrapper').length == 0) {
		$('#content').prepend('<div id="error-wrapper" class="alert alert-error"><a class="close" data-dismiss="alert" href="#">×</a><div>' + message + '</div></div>');
	} else {		
		$('#error-wrapper').append('<div>' + message + '</div>');
	}

	$('body').scrollTop();
};