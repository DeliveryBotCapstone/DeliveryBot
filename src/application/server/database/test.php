<?php  

$con=mysqli_connect("13.209.74.128","ostus","748596","ostus"); 
mysqli_select_db( $con, "ostus");

 

mysqli_set_charset($con,"utf8");

  

if (mysqli_connect_errno($con))  

{  

   echo "Failed to connect to MySQL: " . mysqli_connect_error();  

}  

$name = $_POST['name'];  

$address = $_POST['address'];  

  

  

$result = mysqli_query($con,"insert into TEST1 (name,address) values ('$name','$address')");  

  

  if($result){  

    echo 'success';  

  }  

  else{  

    echo 'failure';  

  }  

  

  

mysqli_close($con);  

?> 

 

<html>

   <body>

   

      <form action = "<?php $_PHP_SELF ?>" method = "POST">

         Name: <input type = "text" name = "name" />

         Address: <input type = "text" name = "address" />

         <input type = "submit" />

      </form>

   

   </body>

</html>

 