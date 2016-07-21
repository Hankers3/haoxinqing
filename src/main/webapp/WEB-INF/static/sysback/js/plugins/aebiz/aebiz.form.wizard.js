// Wizard 表单多步的切换调用插件js
	$(function(){
    if ($(".form-wizard").length > 0) {
        $(".form-wizard").formwizard({
            formPluginEnabled: true,
            validationEnabled: true,
            focusFirstInput: false,
            disableUIStyles: true,
            validationOptions: {
                errorElement: 'span',
                errorClass: 'help-block has-error',
                errorPlacement: function(error, element) {
                    if (element.parents("label").length > 0) {
                        element.parents("label").after(error);
                    } else {
                        element.after(error);
                    }
                },
                highlight: function(label) {
                    $(label).closest('.form-group').removeClass('has-error has-success').addClass('has-error');
                    console.log('aaa');
                },
                success: function(label) {
                    label.addClass('valid').closest('.form-group').removeClass('has-error has-success').addClass('has-success');
                }
            },
            formOptions: {
                success: function(data) {
                    alert("Response: \n\n" + data.say);
                },
                dataType: 'json',
                resetForm: true
            }
        });
    }
  });