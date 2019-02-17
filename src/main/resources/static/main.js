var height, width, bombs, ID, field, flag = 0, table;
$("#start").click(

    function(){
        var radioValue = $("input[name='sizeCode']:checked").val();
        switch(radioValue) {
        case "small":
            window.height = 8;
            window.width = 8;
            window.bombs = 10;
            break;
        case "medium":
            window.height = 16;
            window.width = 16;
            window.bombs = 40;
            break;
        case "large":
            window.height = 16;
            window.width = 30;
            window.bombs = 99;
            break;
        case "custom":
            window.height = $("#height").value;
            window.width = $("#width").value;
            window.bombs = $("#bombs").value; //TODO make it work
            break;
       }

       init();

       $.ajax({
        type: "POST",
        url: "/start",
        data: JSON.stringify({
            fieldHeight : window.height,
            fieldWidth : window.width,
            bombsCount : window.bombs,
            }),
        success: function(data){

            window.ID = data.sessionID;
            alert(window.ID);
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
    $("#playerField").remove();
    var $table = $('<table/>');
    $table.attr('id', "playerField");
    window.flag = 2;
    for (var i = 0; i < width; i++) {
        var $row = $('<tr/>');

        for (var j = 0; j < height; j++) {
            var $col = $('<td>');
            $col.data({x:i,y:j});
            $col.addClass("CLOSED");
            $col.attr('height', 15);
            $col.attr('width', 15);
            $col.attr('id',  String(i) + String(j));
            var clickType;

            $col.mousedown(function(event) {
                switch (event.which) {
                    case 1:
                        clickType = 1;  //open
                        break;
                    case 3:
                        clickType = 0; //mark
                        break;
                    default:
                    clickType = 1;
                    break;
                }
                 $.ajax({
                    type: "POST",
                    url: "/select",
                    data: JSON.stringify({
                        state : clickType,
                        x : $(event.target).data().x,
                        y : $(event.target).data().y,
                        sessionID : window.ID
                        }),
                    success: function(data){
                        render(data);
                        },
                    contentType : "application/json"
                    });
                    //return false;
            });


            $row.append($col);
        }

        $table.append($row);
    }

    $('#field').append($table);
    window.table = $table;
}


function render(data) {
    for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                           //window.table.eq(i).find('td').eq(j).removeClass(window.table.eq(i).find('td').eq(j).attr('class')).addClass(data.field[i][j]); //TODO decompose
                            $("#" + (String(i) + String(j))).removeClass($("#" + (String(i) + String(j))).attr('class')).addClass(data.field[i][j]);
                     }
    }
}

