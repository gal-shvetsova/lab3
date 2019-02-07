        var height, width, bombs;
$("#start").click(

    function(){
        var radioValue = $("input[name='sizeCode']:checked").val();
        switch(radioValue) {
        case "small":
            height = 8;
            width = 8;
            bombs = 10;
            break;
        case "medium":
            height = 16;
            width = 16;
            bombs = 40;
            break;
        case "large":
            height = 16;
            width = 30;
            bombs = 99;
            break;
        case "custom":
            height = $("#height").value;
            width = $("#width").value;
            bombs = $("#bombs").value;
            break;
       }

       init();

       $.ajax({
        type: "POST",
        url: "/start",
        data: JSON.stringify({
            fieldHeight : height,
            fieldWidth : width,
            bombsCount : bombs,
            playerName : $("#player").value
            }),
        success: function(data){
            $('.menu').hide(400);
            $('.game').show();
            },
        contentType : "application/json"
       });
       }
    );


$("#back").click(

function(){
            $('.menu').show(400);
            $('.game').hide();}
    );

function init() {
    var $table = $('<table/>');

    for (var i = 0; i < width; i++) {
        var $row = $('<tr/>');

        for (var j = 0; j < height; j++) {
            var $col = $('<td>0<td/>');
            $col.data({x:i,y:j});
            $col.addClass("empty");
            var clickType;

            $col.mousedown(function(event) {
                switch (event.which) {
                    case 3:
                        clickType = 'mark';
                        break;
                    default:
                        clickType = 'open';
                }




                 $.ajax({
                                    type: "POST",
                                    url: "/select",
                                    data: JSON.stringify($(event.target).data()),
                                    success: function(data){
                                            //render(data);
                                        },
                                    contentType : "application/json"
                                   });
                                   return false;
            });


            $row.append($col);
        }

        $table.append($row);
    }

    $('#field').append($table);
}


function onLeftClick() {

}

