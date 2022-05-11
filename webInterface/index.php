<html>
    <head>
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
        </style>
    </head>
    <body>
        <div id="div1" style="width: 100%; height: 5%; background-color: black;">
            <div id="menuItem1" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Home</div>
            <div id="menuItem2" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;" onclick="menuItem1Click()">Xem hóa đơn</div>
            <div id="menuItem3" style="width: 20%; height: 100%; float: left; color: white; text-align: center; vertical-align: middle;">Tất cả dữ liệu</div>
        </div>

        <div id="screen" style="margin: auto; width: 50%; height:50%; border: 3px solid green;">
            
        </div>
        <script>
            function menuItem1Click(){
                var files = <?php 
                    $out = array();
                    $str = '';
                    $dir = opendir('../bill/');
                    while (($file = readdir($dir)) !== false) {
                        $temp= explode('.',$file);
                        $extension = end($temp);
                        if($extension == "htm" or $extension == "html"){
                            $out[] = "<div style='width: 100%; height: 5%; background-color: red'>".$file."</div><br>";
                        }
                    }
                    echo json_encode($out);
                ?>;
                var i = 0;
                var str = "";
                while(i < files.length){
                    str = str + files[i];
                    i = i + 1;
                }
                document.getElementById("screen").innerHTML = str;
            }
        </script>
    </body>
</html>