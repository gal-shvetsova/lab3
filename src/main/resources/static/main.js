var height, width, bombs, ID, field, flag = 0;
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
    var $table = $('<table/>');
    window.flag = 2;
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
                        clickType = 1;  //mark
                        break;
                    default:
                        clickType = 0; //open
                }
                if (window.flag) {
                    clickType = window.flag;
                    window.flag = 0;
                    }
                 $.ajax({
                    type: "POST",
                    url: "/select",
                    data: JSON.stringify({
                        state : $(event.target).data(),
                        sessionID : window.ID
                        }),
                    success: function(data, table){
                    //render(data);
                        alert(data.state);
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
}


function render(data, myTable) {

    for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                $col.data({x:i,y:j});
               // if (data.field[i][j] == 9) {
                    table.eq(i).find('td').eq(j).addClass(data.field[i][j]);
              //  }
                //if (data.field[i][j] == 0)
                    //$col.addClass("zero");
            }
    }
}

