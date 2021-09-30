$(document).ready(function(){
	$('.minus').on('click',function(e){
		e.preventDefault();
		productId = $(this).attr("pid");
		qtyInput = $('#quantity' + productId);
		newQty = parseInt(qtyInput.val()) - 1;
		if(newQty > 0) qtyInput.val(newQty);
	});
	
	$('.plus').on('click',function(e){
		e.preventDefault();
		productId = $(this).attr("pid");
		qtyInput = $('#quantity' + productId);
		newQty = parseInt(qtyInput.val()) + 1;
		if(newQty < 20) qtyInput.val(newQty); 
	});
});
