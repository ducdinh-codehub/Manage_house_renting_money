<html>
    <head>
        <script type="text/javascript" src="./jquery-3.6.0.min.js"></script>
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
                idInterval = window.setInterval(function(){
				    getFiles();
			    }, 1000); 

            });

            function getFiles(){
                $.ajax({
                    url: "http://localhost/bill/",
                    success: function(data){
                        var files = "";
                        $(data).find("*").each(function(){
                            let file = $(this).attr("href"); 
                            var extension = String(file).split(".")[1];
                            if(extension == "html" || extension == "htm"){
                                
                                file = "<div class='subItem' style='width: 100%; height: 5%; background-color: gray; color: white;'>" + file + "</div><br>";
                                files = files + file;
                            
                            }
                        });
                        $("#display").html(files);
                    }
                });
            }

            $(document).on("click",".subItem", function(){
                alert($(this).text());
            });
            
            $("#closeBtb").click(function (){
                $("#screen").css("visibility","hidden");
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
            <div id='closeBtb' style='width: 20%; height: 5%; background-color: gray; color: white; float: right; text-align: center;'>Close</div>
        </div>
    </body>
    
</html>