//var check = $("input[type='checkbox']");
//check.click(function(){
//  $("p").toggle();
//  alert($("#post_visibility").val);
//});

var switchStatus = false;
$("#postVisibility").on('change', function() {
	if ($(this).is(':checked')) {
		// 체크 여부 확인
		switchStatus = $(this).is(':checked');
		alert(switchStatus);// To verify
	} else {
		switchStatus = $(this).is(':checked');
		alert(switchStatus);// To verify
	}
	$("#postVisibility").val(switchStatus);
});
