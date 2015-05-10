<?php 
	/*
		serverGreetings.php
	*/

	#Retrive POST parameter
	$requesterName = $_POST['name'];

	#Response 
	echo "Greetings ".$requesterName.", how you feeling today?";
?>