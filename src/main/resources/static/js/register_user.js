$(function(){
	alert("jquery動いてます");
	$("#address-search").on("click",function(){
		AjaxZip3.zip2addr('inputZipcode','','inputAddress','inputAddress');
	});
	
});
