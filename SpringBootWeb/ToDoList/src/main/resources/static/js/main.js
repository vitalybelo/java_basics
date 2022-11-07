$(function(){

    const appendEvent = function(data){
        const eventCode = '<a href="#" class="book-link" data-id="' +
            data.id + '">' + data.text + '</a><br>';
        $('#book-list')
            .append('<div>' + eventCode + '</div>');
    };


    //Show adding book form
    $('#show-add-book-form').click(function(){
        $('#book-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#book-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting status event
    $(document).on('click', '.book-link', function(){
        const link = $(this);
        const eventId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/events/' + eventId,
            success: function(response)
            {
                const code = '<span>  cтатус задания: (' + response.done + ')</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status === 404) {
                    alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-book').click(function()
    {
        var data = $('#book-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/events/',
            data: data,
            success: function(response)
            {
                $('#book-form').css('display', 'none');
                const event = {};
                event.id = response;
                const data_arr = $('#book-form form').serializeArray();
                $.each(data_arr,function (){
                    event.text = this.value;
                });
                appendEvent(event);
            }
        });
        return false;
    });

});