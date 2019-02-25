var height, width, bombs, ID, field, sizeCode, flag = false, table;
$("#start").click(

    function(){
        var radioValue = $("input[name='sizeCode']:checked").val();
        switch(radioValue) {
        case "small":
            window.height = 8;
            window.width = 8;
            window.bombs = 10;
            window.sizeCode = "small";
            break;
        case "medium":
            window.height = 16;
            window.width = 16;
            window.bombs = 40;
            window.sizeCode = "medium";
            break;
        case "large":
            window.height = 30;
            window.width = 16;
            window.bombs = 99;
            window.sizeCode = "large";
            break;
        case "custom":
            window.height = $(".height").val();
            window.width = $(".width").val();
            window.bombs = $(".bombs").val();
            window.sizeCode = "custom";
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
            $('.game').hide();
            window.sec = 0;
            window.flag = false;
            }
    );

$("#records").click(
function() {


       $.ajax({
        type: "POST",
        url: "/records",
        data: JSON.stringify({
            size : window.sizeCode,
            }),
        success: function(data){
            showRecords(data);
            $('.game').hide(400);
            $('.records').show();

            },
        contentType : "application/json"
       });
}
);

function init() {
    $("#playerField").remove();
    var $table = $('<table/>');
    $table.attr('id', "playerField");
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
                            $("#" + (String(i) + String(j))).removeClass($("#" + (String(i) + String(j))).attr('class')).addClass(data.field[i][j]);
                     }
    }
    if (window.flag == false) window.flag = true;
}

    var sec = 0;
    function pad ( val ) { return val > 9 ? val : "0" + val; }
    setInterval( function(){
        if (window.flag) sec++;
            $("#seconds").html(pad(sec));
    }, 1000);

function showRecords(data) {

    $("#playersList").remove;
    var $ol = $('<ol/>');
    $ol.attr('id', "playersList");
    for (var i = 0; i < 5; i++) {
    $ol.prepend('<li>' + data.players[i] + '</li>');
    }
        $('#table').append($ol);
        var $ol = $('<ol/>');
        $ol.attr('id', "recordsList");
        for (var i = 0; i < 5; i++) {
        $ol.prepend('<li>' + data.records[i] + '</li>');
        }
            $('#table').append($ol);  //TODO make it in table and make back button
}
