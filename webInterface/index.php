<html>
    <head>
        <script type="text/javascript" src="./jquery-3.6.0.min.js"></script>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
        <style>
            #menuItem1:hover{
                background-color: green;
            }
            #menuItem2:hover{
                background-color: green;
            }
            #menuItem3:hover{
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
			    }, 5000); 
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
                                
                                file = "<div class='subItem' style='width: 100%; height: 5%; background-color: gray; color: white;' value='$file' onclick='getContent(this)'>" + file + "</div><br>";
                                files = files + file;
                            
                            }
                        });
                        $("#display").html(files);
                    }
                });
            }
            $("#closeBtb").click(function (){
                $("#screen").css("visibility","hidden");
                clearInterval(idInterval);
            });
        });
    </script>
    <body>
        <div id="div1" style="width: 100%; height: 5%; background-color: black;">
            <div id="menuItem1" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;" onclick="menuItem1Click()">Home</div>
            <!--div id="menuItem2" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;" onclick="menuItem2Click()">Xem hóa đơn</div-->
            <div id="menuItem2" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Xem hóa đơn</div>
            <div id="menuItem3" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;" onclick="menuItem3Click()">Tất cả dữ liệu</div>
            <div id="menuItem3" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;" onclick="menuItem4Click()">Về tôi</div>
        </div>

        <div id="screen" style="margin: 2% auto; width: 70%; height:70%; border: 3px solid green;">
            <div id="display" style="width: 100%; height: 95%;"></div>
            <div id='closeBtb' style='width: 20%; height: 5%; background-color: gray; color: white; float: right; text-align: center;'>Close</div>
        </div>
        <script>
            function menuItem1Click(){
                document.getElementById("screen").style.visibility = 'hidden';
            }
            function closeBtbClick(){
                document.getElementById("screen").style.visibility = 'hidden';
                
            }
            //function menuItem2Click(){
            //    var files = <!--?php 
            //        $out = array();
            //        $str = '';
            //        $dir = opendir('../bill/');
            //        while (($file = readdir($dir)) !== false) {
            //            $temp= explode('.',$file);
            //            $extension = end($temp);
            //            if($extension == "htm" or $extension == "html"){
            //                $out[] = "<div class='subItem' style='width: 100%; height: 5%; background-color: gray; color: white;' value='$file' onclick='getContent(this)'>".$file."</div><br>";
            //            }
            //        }
            //        echo json_encode($out);
            //    ?-->;
            //    var i = 0;
            //    var str = "";
            //    while(i < files.length){
            //        str = str + files[i];
            //        i = i + 1;
            //    }
            //    document.getElementById("screen").style.visibility = 'visible';                
            //    document.getElementById("display").innerHTML = str;
            //}
            function getContent(a){
                console.log(a.innerHTML);
            }
            function menuItem3Click(){
                document.getElementById("screen").style.visibility = 'hidden';
            }
            function menuItem4Click(){
                document.getElementById("screen").style.visibility = 'hidden';
            }
        </script>
    </body>
    
</html>