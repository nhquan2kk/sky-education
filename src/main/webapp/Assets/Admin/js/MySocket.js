var socket = io.connect('http://localhost:9095');
console.log('socket: ', socket);
const dropMenu = $('.dropdown-menu-socket');
$('.count').hide();
socket
	.on(
		'connect',
		function() {
			console.log("INIT SUCCESS", socket);
		});
$('#notificationDropdown').click(() => {
	$('.count').hide();
})
socket.on('chatevent', function(data) {
	$('.count').show();
	console.log('data: ', data);
	dropMenu.html(`<a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                <div class="preview-icon bg-info">
                  <i class="ti-user mx-0"></i>
                </div>
              </div>
              <div class="preview-item-content">
                <h6 class="preview-subject font-weight-normal"><a href="AdminSendGmailController?email=${data.email}&subject=${data.subject}&body=${data.body}">New message</a></h6>
              </div>
            </a>`);
});

socket.on('disconnect', function() {
	
});

function sendDisconnect() {
	socket.disconnect();
}

function sendMessage() {
	const email = $('#email').val();
	const subject = $('#subject').val();
	var myContent = tinymce.get("summernote").getContent();
	const content = $('#summernote').val();
	console.log(myContent, ' content: ', content);
	console.log('email: ', email);
	console.log('subject: ', subject);
	var message = $('#msg').val();
	$('#msg').val(' ');

	var jsonObject = {
		email: email,
		subject: subject,
		content: content
	};

	socket.emit('chatevent', jsonObject);
}
