<?php
include("../includes/connect.php");
//reg_id,category_one,type_one,existence_one,worth_one,image_one
//category_two,type_two,existence_two,worth_two,image_two
//category_three,type_three,existence_three,worth_three,image_three
    $reg_id = $_POST["reg_id"];
    $category_one = $_POST["category_one"];
    $type_one = $_POST["type_one"];
    $existence_one = $_POST["existence_one"];
    $worth_one = $_POST["worth_one"];
    $image_one = $_POST['image_one'];
    $fileimage_one = "IMG" . rand() . ".jpg";
    $category_two = $_POST["category_two"];
    $type_two = $_POST["type_two"];
    $existence_two = $_POST["existence_two"];
    $worth_two = $_POST["worth_two"];
    $image_two = $_POST['image_two'];
    $fileimage_two = "IMG" . rand() . ".jpg";
    $category_three = $_POST["category_three"];
    $type_three = $_POST["type_three"];
    $existence_three = $_POST["existence_three"];
    $worth_three = $_POST["worth_three"];
    $image_three = $_POST['image_three'];
    $fileimage_three = "IMG" . rand() . ".jpg";
    if($worth_one==0){
      if($worth_two==0){
        if(mysqli_query($db,"UPDATE collateral set category_one='No Asset',type_one=' ',existence_one=' ',
      worth_one='$worth_one',image_one=' ',category_two='No Asset',type_two='',existence_two='',
      worth_two='$worth_two',image_two='',category_three='$category_three',type_three='$type_three',existence_three='$existence_three',
      worth_three='$worth_three',image_three='$fileimage_three', status='Assessed' where reg_id='$reg_id'")){
       file_put_contents("images/" . $fileimage_three, base64_decode($image_three));
        $response["success"] = 1;
        $response["message"] = "Upload was successfully";
      }else{
          $response["success"] = 0;
          $response["message"] = "Failed to upload"; 
      }
      }elseif($worth_three==0){
        if(mysqli_query($db,"UPDATE collateral set category_one='No Asset',type_one=' ',existence_one=' ',
        worth_one='$worth_one',image_one=' ',category_two='$category_two',type_two='$type_two',existence_two='$existence_two',
        worth_two='$worth_two',image_two='$fileimage_two',category_three='No Asset',type_three='',existence_three='',
        worth_three='$worth_three',image_three='', status='Assessed' where reg_id='$reg_id'")){
          file_put_contents("images/" . $fileimage_two, base64_decode($image_two));
          $response["success"] = 1;
          $response["message"] = "Upload was successfully";
        }else{
            $response["success"] = 0;
            $response["message"] = "Failed to upload"; 
        } 
      }else{
        if(mysqli_query($db,"UPDATE collateral set category_one='No Asset',type_one=' ',existence_one=' ',
        worth_one='$worth_one',image_one=' ',category_two='$category_two',type_two='$type_two',existence_two='$existence_two',
        worth_two='$worth_two',image_two='$fileimage_two',category_three='$category_three',type_three='$type_three',existence_three='$existence_three',
        worth_three='$worth_three',image_three='$fileimage_three', status='Assessed' where reg_id='$reg_id'")){
          file_put_contents("images/" . $fileimage_two, base64_decode($image_two));
          file_put_contents("images/" . $fileimage_three, base64_decode($image_three));
          $response["success"] = 1;
          $response["message"] = "Upload was successfully";
        }else{
            $response["success"] = 0;
            $response["message"] = "Failed to upload"; 
        }
      }
      
    }elseif($worth_two==0){
      if($worth_one==0){
        if(mysqli_query($db,"UPDATE collateral set category_one='No Asset',type_one='',existence_one='',
    worth_one='$worth_one',image_one='',category_two='No Asset',type_two='',existence_two='',
    worth_two='$worth_two',image_two='',category_three='$category_three',type_three='$type_three',existence_three='$existence_three',
    worth_three='$worth_three',image_three='$fileimage_three', status='Assessed' where reg_id='$reg_id'")){
      file_put_contents("images/" . $fileimage_three, base64_decode($image_three));
      $response["success"] = 1;
      $response["message"] = "Upload was successfully";
    }else{
        $response["success"] = 0;
        $response["message"] = "Failed to upload"; 
    }
      }elseif($worth_three==0){
        if(mysqli_query($db,"UPDATE collateral set category_one='$category_one',type_one='$type_one',existence_one='$existence_one',
        worth_one='$worth_one',image_one='$fileimage_one',category_two='No Asset',type_two='',existence_two='',
        worth_two='$worth_two',image_two='',category_three='No Asset',type_three='',existence_three='',
        worth_three='$worth_three',image_three='', status='Assessed' where reg_id='$reg_id'")){
          file_put_contents("images/" . $fileimage_one, base64_decode($image_one));
          $response["success"] = 1;
          $response["message"] = "Upload was successfully";
        }else{
            $response["success"] = 0;
            $response["message"] = "Failed to upload"; 
        } 
      }else{
        if(mysqli_query($db,"UPDATE collateral set category_one='$category_one',type_one='$type_one',existence_one='$existence_one',
        worth_one='$worth_one',image_one='$fileimage_one',category_two='No Asset',type_two='',existence_two='',
        worth_two='$worth_two',image_two='',category_three='$category_three',type_three='$type_three',existence_three='$existence_three',
        worth_three='$worth_three',image_three='$fileimage_three', status='Assessed' where reg_id='$reg_id'")){
          file_put_contents("images/" . $fileimage_one, base64_decode($image_one));
          file_put_contents("images/" . $fileimage_three, base64_decode($image_three));
          $response["success"] = 1;
          $response["message"] = "Upload was successfully";
        }else{
            $response["success"] = 0;
            $response["message"] = "Failed to upload"; 
        }
      }
      
    }elseif($worth_three==0){
      if($worth_one==0){
        if(mysqli_query($db,"UPDATE collateral set category_one='No Asset',type_one='',existence_one='',
      worth_one='$worth_one',image_one='',category_two='$category_two',type_two='$type_two',existence_two='$existence_two',
      worth_two='$worth_two',image_two='$fileimage_two',category_three='No Asset',type_three='',existence_three='',
      worth_three='$worth_three',image_three='', status='Assessed' where reg_id='$reg_id'")){
        file_put_contents("images/" . $fileimage_two, base64_decode($image_two));
        $response["success"] = 1;
        $response["message"] = "Upload was successfully";
      }else{
          $response["success"] = 0;
          $response["message"] = "Failed to upload"; 
      }
      }elseif($worth_two==0){
        if(mysqli_query($db,"UPDATE collateral set category_one='$category_one',type_one='$type_one',existence_one='$existence_one',
      worth_one='$worth_one',image_one='$fileimage_one',category_two='No Asset',type_two='',existence_two='',
      worth_two='$worth_two',image_two='',category_three='No Asset',type_three='',existence_three='',
      worth_three='$worth_three',image_three='', status='Assessed' where reg_id='$reg_id'")){
        file_put_contents("images/" . $fileimage_one, base64_decode($image_one));
        $response["success"] = 1;
        $response["message"] = "Upload was successfully";
      }else{
          $response["success"] = 0;
          $response["message"] = "Failed to upload"; 
      }
      }else{
        if(mysqli_query($db,"UPDATE collateral set category_one='$category_one',type_one='$type_one',existence_one='$existence_one',
        worth_one='$worth_one',image_one='$fileimage_one',category_two='$category_two',type_two='$type_two',existence_two='$existence_two',
        worth_two='$worth_two',image_two='$fileimage_two',category_three='No Asset',type_three='',existence_three='',
        worth_three='$worth_three',image_three='', status='Assessed' where reg_id='$reg_id'")){
          file_put_contents("images/" . $fileimage_one, base64_decode($image_one));
          file_put_contents("images/" . $fileimage_two, base64_decode($image_two));
          $response["success"] = 1;
          $response["message"] = "Upload was successfully";
        }else{
            $response["success"] = 0;
            $response["message"] = "Failed to upload"; 
        }
      }
     
    }else{
      if(mysqli_query($db,"UPDATE collateral set category_one='$category_one',type_one='$type_one',existence_one='$existence_one',
      worth_one='$worth_one',image_one='$fileimage_one',category_two='$category_two',type_two='$type_two',existence_two='$existence_two',
      worth_two='$worth_two',image_two='$fileimage_two',category_three='$category_three',type_three='$type_three',existence_three='$existence_three',
      worth_three='$worth_three',image_three='$fileimage_three', status='Assessed' where reg_id='$reg_id'")){
        file_put_contents("images/" . $fileimage_one, base64_decode($image_one));
        file_put_contents("images/" . $fileimage_two, base64_decode($image_two));
        file_put_contents("images/" . $fileimage_three, base64_decode($image_three));
        $response["success"] = 1;
        $response["message"] = "Upload was successfully";
      }else{
          $response["success"] = 0;
          $response["message"] = "Failed to upload"; 
      }
    }
  
    echo json_encode($response);
    mysqli_close($db);