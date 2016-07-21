 // tag-input
 $(document).ready(function() {
    if ($(".tagsinput").length > 0) {
        $('.tagsinput').each(function(e) {
            $(this).tagsInput({
                width: 'auto',
                height: 'auto'
            });
        });
    }
});