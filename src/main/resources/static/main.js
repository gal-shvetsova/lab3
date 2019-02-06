
        $("#start").click(

function(){
       var radioValue = $("input[name='sizeCode']:checked").val();
       var height, width;
       switch(radioValue) {
        case "small":
        height = 8;
        width = 8;
        break;
        case "medium":
        height = 16;
        width = 16;
        break;
        case "large":
        height = 16;
        width = 30;
        break;
        case "custom":
        height = $("#height").value;
        width = $("#width").value;
        break;
       }
       $.ajax({
         type: "POST",
         url: "/start",
         data: JSON.stringify({
           fieldHeight : height,
           fieldWidth : width,
           bombsCount : $("#bombs").value,
           playerName : $("#player").value
           }),
           success: function(data){
          $('.menu').hide(400);
           },
         contentType : "application/json"
       });}

    );


        $("#back").click(

function(){

       $.ajax({
           success: function(data){
        //    $('.menu').css('style.display', 'block');
          // $('.game').css('style.display', 'none');
           },
       });}

    );




