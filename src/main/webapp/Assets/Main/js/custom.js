const msgHistory = $('.msg_history');
$('.msg_send_btn').click(() => {
	const reqQuestion = $('#msg').val();
	console.log(reqQuestion, " msg");
	$('msg').val('');
	msgHistory.append(`<div class="outgoing_msg">
					<div class="sent_msg">
					<p>${reqQuestion}</p>
				</div>
			</div>`);
		const msg = reqQuestion;
	$.ajax({
		type: "POST",
		url: "ChatGPTController",
		data: {
			msg: reqQuestion
		},
		success: (res)=>{
			console.log('RES: ', res);
			msgHistory.append(`<div class="incoming_msg">
							<div class="incoming_msg_img">
								<img src="https://ptetutorials.com/images/user-profile.png"
									alt="sunil">
							</div>
							<div class="received_msg">
								<div class="received_withd_msg">
									<p>${res}</p>
								</div>
							</div>
						</div><br/>`);
		},
		dataType:"json" 
	});
});