$(function(){
		$(".credit").hide();	
	
	$('input[name="paymentMethod"]:radio').on("change",function(){
		if($(this).val == '1'){
			$(".credit").hide();
		}else{
			$(".credit").show();
		}
	});
	
	
	
});