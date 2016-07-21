;(function($){
	var defaults = {keyevent:"keydown"};

	/*
	 * Form注册Enter事件，实现自动回车跳转功能.
	 * $("#frm").enterAsTab({"skip":"#id1,#id3","submit":"#btnSubmit1"});
	 */
	$.fn.enterAsTab = function(options) {
		var settings = $.extend({}, defaults, options || {});
		var formelements = $(this).find("input, select").not(":button,:submit,:reset,:image,:hidden,:disabled,[readonly]").filter(":visible");
		var inputs = settings.skip ? $(formelements).not(settings.skip) : $(formelements);
		$(inputs).each(function(index, element) {
			$(element).on(settings.keyevent, settings, function(event) {
				var key = event.which;
				if (key != 13) {return true;}
				if (index < (inputs.length - 1)) {
					inputs[index + 1].focus();
					return false;
				} else if (settings.submit) {
					$(settings.submit).focus();
					return false;
				}
				return true;
			});
		});
		$(inputs).first().focus();
		return this;
	};
})(jQuery);