var height, width, bombs, ID, field, sizeCode, flag = "start", table, customFlag = false;

var preloaded_images = [];

var images_to_preload = [
    "closed.png",
    "diemined.png",
    "eight.png",
    "five.png",
    "flaged.png",
    "four.png",
    "mined.png",
    "notmined.png",
    "one.png",
    "question.png",
    "seven.png",
    "six.png",
    "three.png",
    "two.png",
    "zero.png",
    "d0.png",
    "d1.png",
    "d2.png",
    "d3.png",
    "d4.png",
    "d5.png",
    "d6.png",
    "d7.png",
    "d8.png",
    "d9.png",

];

function preload_images() {
	for (var i = 0; i<images_to_preload.length; i++) {
		preloaded_images[i] = new Image();
		preloaded_images[i].src = "media/" + images_to_preload[i];
	}
}

preload_images();

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
            $('.game').show(400);
            },
        contentType : "application/json"
       });

       }
    );


$("#back").click(

function(){
            $('.menu').show(400);
            $('.game').hide(400);
            window.sec = 0;
            window.flag = "start";
            }
    );

$("#backGame").click(

function(){
            $('.game').show(400);
            $('.records').hide(400);
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
            $('.records').show(400);
            },
        contentType : "application/json"
       });
}
);

$("#restart").click(
function() {
        window.sec = 0;

        init();
       $.ajax({
        type: "POST",
        url: "/start",
        data: JSON.stringify({
            size : window.sizeCode,
            fieldHeight : window.height,
            fieldWidth : window.width,
            bombsCount : window.bombs,
            }),
        success: function(data){
                window.ID = data.sessionID;
            },
        contentType : "application/json"
       });
       window.flag = "start";
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
            $col.attr('height', 24);
            $col.attr('width', 24);
            $col.attr('id',  String(i*height + j));
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
                if (window.flag == "game" || window.flag == "start"){
                 $.ajax({
                    type: "POST",
                    url: "/select",
                    data: JSON.stringify({
                        state : clickType,
                        x : $(event.target).data().x,
                        y : $(event.target).data().y,
                        sessionID : window.ID,
                        time : window.sec
                        }),
                    success: function(data){
                        render(data);

                        },
                    contentType : "application/json"
                    });
                    }

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
                            $("#" + (String(i*height + j))).removeClass($("#" + (String(i*height + j))).attr('class')).addClass(data.field[i][j]);
                     }
    }
    window.flag = data.state;
    if (data.state == "beat") win();
}

//timer

    var sec = 0;
    function pad ( val ) { return val > 9 ? val : "0" + val; }
    setInterval( function(){
        if (window.flag == "game") sec++;
        var hun = Math.floor(sec / 100);
        var ten = Math.floor((sec - hun*100) / 10);
        var one = Math.floor(sec - hun*100 - ten*10);
          $("#hundred").removeClass($("#hundred").attr('class')).addClass("d" + String(hun));
          $("#ten").removeClass($("#ten").attr('class')).addClass("d" + String(ten));
          $("#one").removeClass($("#one").attr('class')).addClass("d" + String(one));
    }, 1000);


function showRecords(data) {
    $("#playersList").remove();
    var $table = $('<table/>');
    $table.attr('id', "playersList");
    for (var i = 0; i < 5; i++) {
        var $row = $('<tr/>');
        var $col = $('<td>' + String(data.players[i]) + '<td/>');
        $row.append($col);
        var $col = $('<td>' + String(data.records[i]) + '<td/>');
        $row.append($col);
        $table.append($row);
    }
        $('#table').append($table);
}

function win(){
    name = prompt("Enter your name", "Player");
    window.flag = "start";
              $.ajax({
                        type: "POST",
                        url: "/newrecord",
                        data: JSON.stringify({
                            player : name,
                            time : window.sec,
                            size : window.sizeCode

                            }),

                        contentType : "application/json"
                        });
                        }



$('input:radio[id=custom]').on('change', function () {
    $(".customSize").show(400);
});

$('input:radio[id=small]').on('change', function () {
     $(".customSize").hide(400);
     });

     $('input:radio[id=medium]').on('change', function () {
          $(".customSize").hide(400);
          });

          $('input:radio[id=large]').on('change', function () {
               $(".customSize").hide(400);
               });
