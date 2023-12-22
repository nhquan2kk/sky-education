var myToastEl = document.getElementById('myToastEl')
var myToast = bootstrap.Toast.getOrCreateInstance(myToastEl) // Returns a Bootstrap toast instance
console.log('MYTOAST: ', myToastEl);
var content = document.getElementById('msgSuccess').innerHTML;
console.log('content: ', content);
if (content.length > 0 && content === 'Send email successfully!') {
	myToast.show();
}
console.log('myTOast : ', myToast)