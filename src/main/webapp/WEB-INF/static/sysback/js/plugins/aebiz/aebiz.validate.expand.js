jQuery.validator.addMethod("isZipCode", function(value, element) {  
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");

jQuery.validator.addMethod("mobilezh", function(value, element) {  
    var mobile = /^1\d{10}$/;
    return this.optional(element) || (mobile.test(value));
}, "请正确填写您的手机号");

jQuery.validator.addMethod("loginname", function(value, element) {  
    var loginname =/^[\w\.\_]{4,20}$/;    
    return this.optional(element) || (loginname.test(value));
}, "请输入4-20位字符(数字、字母、.、_)");
