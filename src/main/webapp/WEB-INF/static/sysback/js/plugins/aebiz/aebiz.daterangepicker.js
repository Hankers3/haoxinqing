

//  调用选择日历区间的js
$(document).ready(function() {
    var mobile = false,
        tooltipOnlyForDesktop = true,
        notifyActivatedSelector = 'button-active';
		if ($('.daterangepick').length > 0) {
        $('.daterangepick').daterangepicker(); //调用日历区间插件
    }
});