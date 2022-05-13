<?php  

$con=mysqli_connect("localhost","ostus", "748596", "ostus"); 
mysqli_set_charset($con,"utf8");

  

if (mysqli_connect_errno($con))  

{  

   echo "Failed to connect to MySQL: " . mysqli_connect_error();  

}  

$number = $_POST['number'];  

$address = $_POST['address'];  

$name = $_POST['name'];

  

  

$result = mysqli_query($con,"insert into TEST1 (number,address,name) values ('$number','$address','$name')");  

  

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

         Name : <input type="text" name="name">

         Number : <input type = "text" name = "number" />

         Address : <input type = "text" name = "address" />

         <input type = "submit" />

      </form>

   

   </body>

</html>

 