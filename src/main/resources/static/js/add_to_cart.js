$(document).ready(function(){
	$('#btnAddToCart').on('click',function(e){
		addToCart();
	});
});


function addToCart(){
	quantity = $('#quantity' + productId).val();
	url = contextPath + "/cart/add/" + productId + "/" + quantity;
	
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr){
			xhr.setRequestHeader("abc","0123456214");
		}
		
	})
}