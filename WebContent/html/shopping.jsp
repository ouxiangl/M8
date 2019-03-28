<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shopping</title>
	<style>
		.labImg{
			text-align: center;
			display: inline-block;
		}
		.imagen{
			display: block;
		}
		.center{
			margin: 0 auto; 
			width: max-content;
			text-align: center;
		}
		.name{
			width: max-content;
			display: inline-block;
			background-color: #e6e64c;
			margin-right: 10px;
		}
		.areaText{
			max-width: 500px;
			max-height: 250px;
		}
	</style>
</head>
<body>
	<div class="center" style="background-color: red;">
		<h1>FORM Products Available</h1>
	</div>
	<form action="../shop" id="formulario">
	    <div class="center">
	    	<div>
	    		<label class="labImg"><img class="imagen" src="img/1.png"><input type="checkbox" name="img" value="1"></label>
	    		<label class="labImg"><img class="imagen" src="img/2.png"><input type="checkbox" name="img" value="2"></label>
	    		<label class="labImg"><img class="imagen" src="img/3.png"><input type="checkbox" name="img" value="3"></label>
	    		<label class="labImg"><img class="imagen" src="img/4.png"><input type="checkbox" name="img" value="4"></label>
	    		<label class="labImg"><img class="imagen" src="img/5.png"><input type="checkbox" name="img" value="5"></label>
	    	</div>
	    	<div style="background-color: #e6e64c; text-align: center;">
	    		<h2 class="center">Payment Mode:</h2>
	    		<label style="float: left;">Cash<input type="radio" name="payment" value="cash"></label>
	    		<label>Credit Card<input type="radio" name="payment" value="credit" checked></label>
	    		<label style="float: right;">PayPal<input type="radio" name="payment" value="paypal"></label>
	    	</div>
	    	<div>
	    		<p class="name"> NICK: ${sessionScope.usuario}</p><br/>
	    		
	    		<p class="name"> QUANTITY </p><select name="quantity">
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="5">5</option>
											  </select><br/>
				<p class="name"> Comments</p><textarea class="areaText" form ="formulario" name="texto" id="taid" cols="35" wrap="soft" ></textarea>
	    	</div>
	    	<input type="submit" value="Send" style="margin: 0 auto;">
	    	<input type="reset" value="Del">
	    </div>
	</form>
	<div class="center" style="background-color: #ffcc99;">
		<h1>Credits</h1>
	</div>
</body>
</html>