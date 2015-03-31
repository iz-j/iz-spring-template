$(function() {
	$('#btn-get').click(function() {
		$.ajax({
			type : 'GET',
			url : '/sample/fragment'
		}).done(function(data) {
			$('#get-result').append('<li>' + data + '</li>');
		});
	});
});