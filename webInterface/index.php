<html>
    <head>
        <script type="text/javascript" src="./jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="./html2canvas.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.0/FileSaver.min.js"></script>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
        <style>
            body{
                margin: 0;
                background-color: #f1f1e4;
            }
            #navbar{
                width: 100%; 
                height: 5%;
                background-color: black;
                position: relative;
                margin: 0;
                padding: 0;
            }
            #menuItem1:hover{
                background-color: green;
            }
            #menuItem2:hover{
                background-color: green;
            }
            #menuItem3:hover{
                background-color: green;
                
            }
            #menuItem4:hover{
                background-color: green;
                
            }
            #screen{
                background-color: black;
                visibility: hidden;
            }

        </style>
    </head>
    <script>
        $(function(){

            var idInterval;

            $("#menuItem2").click(function (){
                $("#screen").css("visibility", "visible");
                $("#closeBtb").css("visibility", "visible");
                idInterval = window.setInterval(function(){
				    getFiles();
			    }, 1000); 

            });

            $("#backBtb").click(function (){
                idInterval = window.setInterval(function(){
				    getFiles();
                    $("#saveBtb").css("visibility","hidden");
                    $("#backBtb").css("visibility","hidden");
			    }, 1000); 
                
            });

            $("#saveBtb").click(function (){
                    html2canvas(document.querySelector("#content")).then(canvas => {
                        document.body.appendChild(canvas)
                        //canvas.toBlob(function(blob) {
                        //    window.saveAs(blob, 'my_image.jpg');
                    //});
                });
            }); 

            function getFiles(){
                $.ajax({
                    url: "http://localhost/bill/",
                    success: function(data){
                        var files = "";
                        $(data).find("*").each(function(){
                            let file = $(this).attr("href"); 
                            var extension = String(file).split(".")[1];
                            if( (String(file).split(".").length == 2 && extension == "html") || (String(file).split(".").length == 2 && extension == "htm")){
                                
                                file = "<div class='subItem' style='width: 100%; height: 5%; background-color: gray; color: white;'>" + file + "</div><br>";
                                files = files + file;
                            
                            }
                        });
                        $("#display").html(files);
                    }
                });
            }

            $(document).on("click",".subItem", function(){
                var path = "http://localhost/bill/" + $(this).text();
                $.ajax({
                    url: path,
                    cache: false,
                }).done(function(html){
                    clearInterval(idInterval);
                    $("#closeBtb").css("visibility","visible");
                    $("#saveBtb").css("visibility","visible");
                    $("#backBtb").css("visibility","visible");
                    $("#display").html(html);
                })
            });
            
            $("#closeBtb").click(function (){
                $("#saveBtb").css("visibility","hidden");
                $("#screen").css("visibility","hidden");
                $("#backBtb").css("visibility","hidden");
                $("#closeBtb").css("visibility","hidden");
                //$("#display").val("");
                clearInterval(idInterval);
            });
            $("#menuItem1").click(function (){
                $("#screen").css("visibility","hidden");
                clearInterval(idInterval);
            });
            $("#menuItem3").click(function (){
                $("#screen").css("visibility","hidden");
                clearInterval(idInterval);
            });
            $("#menuItem4").click(function (){
                $("#screen").css("visibility","hidden");
                clearInterval(idInterval);
            });
        });
    </script>
    <body>
        <div id="navbar">
            <div id="menuItem1" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Home</div>
            <div id="menuItem2" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Xem hóa đơn</div>
            <div id="menuItem3" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Tất cả dữ liệu</div>
            <div id="menuItem4" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Về tôi</div>
        </div>

        <div id="screen" style="margin: 2% auto; width: 70%; height:70%; border: 3px solid green;">
            <div id="display" style="width: 100%; height: 95%;"></div>
            <div id='closeBtb' style='width: 10%; height: 5%; background-color: gray; color: white; float: right; text-align: center;'>Close</div>
            <div id='backBtb' style='width: 10%; height: 5%; background-color: gray; color: white; float: right; text-align: center; margin-right: 1%; visibility: hidden'>Back</div>
            <div id='saveBtb' style='width: 10%; height: 5%; background-color: gray; color: white; float: right; text-align: center; margin-right: 1%; visibility: hidden'>Save</div>
        </div>
    </body>
    
</html>