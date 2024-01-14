<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Evolution de la Douleur</title>
	<!--  <script src="${pageContext.request.contextPath}/node_modules/chart.js/dist/Chart.min.js"></script> 
	-->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<!-- Include moment.js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	
	<!--Ajoutez le script de html2canvas à votre page HTML  -->
	
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
	
	<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PainCare</title>
    <!--vendor css ================================================== -->
    <link rel="stylesheet" type="text/css" href="css/vendor.css">

    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />

    <!--Bootstrap ================================================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

    <!-- Style Sheet ================================================== -->
    <link rel="stylesheet" type="text/css" href="styles.css">

    <!-- Google Fonts ================================================== -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,700;1,300&family=Roboto:wght@300;400;700&display=swap"
        rel="stylesheet">
     
     <link rel="icon" type="image" href="images/logoapp.png">


    <!-- script ================================================== -->
    <script src="js/modernizr.js"></script>
    <style>
        .responsive-image {
      width: 100%; /* Set the image width to 100% of its container */
      height: auto;}

      .navbar .navbar-brand img {
        width: 70px;
        height: auto;
        border-radius: 25px;
      }
      #services {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;}
      
      </style>
    

</head>
    <!-- Vous devez inclure ces bibliothèques pour que les graphiques fonctionnent -->
    
    <style>
        .chart-container {
            width: 80%;
            max-width: 400px; /* Adjust the maximum width as needed */
            margin: auto;
        }
        .chart-line-container {
            width: 80%;
            max-width: 800px; /* Adjust the maximum width as needed */
            margin: auto;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-white navbar-light container-fluid py-3 position-fixed " style="font-family: Arial, Helvetica, sans-serif;background-color: white; box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.1);">
        <div class="container" style="margin-top: -10px; margin-bottom: -10px; ">
            <a class="navbar-brand" href="home.jsp"><img src="images/logoapp.png" alt="logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
                aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"
                        aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav align-items-center justify-content-end flex-grow-1 pe-3" >
                        <li class="nav-item">
                            <a class="nav-link text-uppercase   px-3" aria-current="page"
                                href="home.jsp">Accueil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="SuiviSymptomForm.jsp">Suivi</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="blogServlet?action=view">Communauté</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="calendrier.html">Calendrier</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="about.jsp">A propos de nous</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="addAlarm.jsp">Alarme</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="${pageContext.request.contextPath}/userProfile?action=view">Mon Profil</a>
                        </li>
                        <li class="nav-item">
                           <a href="logoutServlet" ><button type="button" class="btn btn-primary ms-md-3"> log Out</button></a>
                        </li>                        
                        <li class="nav-item" style="margin-left: 20px;">
                            <a href="${pageContext.request.contextPath}/userProfile?action=delete">
                            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                            </svg>
                        </a>
                        </li>


                        
                    </ul>
                              
                      
                    </div>
                    
                </div>
            </div>
        </div>
    </nav>
    
<div style="margin-top:120px; ">

<div id="capture-container">
	<div class="container ">
    <h1 class="justify-items-center text-center ">Evolution de la Douleur</h1>
	</div>
    <!-- 1. Moyenne de l'intensité de la douleur -->
    <div style="padding-left:50px ;margin-top:50px">
    <h3 style="color:black !important; ">Moyenne de l'intensité de la douleur</h3>
    <p>Moyenne: ${averageIntensity}</p>
    </div>
    <!-- 2. Intensité en fonction de la date (Graphique en ligne) -->
    <div style="padding-left:50px ;margin-top:50px">
    <h3 style="color:black !important;">Intensité en fonction de la date</h3>
    <!-- Include the chart here with data from the server -->
    <!-- Canvas pour le graphique en ligne -->
	</div>
	
    <div class="chart-line-container">
            <canvas id="intensityChart"></canvas>
    </div>
   


<script>
    // Récupérer les données du servlet et les convertir en JavaScript
	var dates = [${dates}];  // Dates are already formatted as strings in Java
    var intensities = ${intensities};

    // Configuration du graphique en ligne
    var ctx = document.getElementById('intensityChart').getContext('2d');
    var intensityChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dates,
            datasets: [{
                label: 'Intensité de la Douleur',
                data: intensities,
                borderColor: '#56cfe1',
                borderWidth: 2,
                fill: false
            }]
        },
        options: {
            scales: {
            	x: {
                    position: 'bottom'
                },
                y: {
                    type: 'linear',
                    position: 'left', 
                    suggestedMin: 0,
                    suggestedMax: 10
                }
            },
            
        }
    });
    </script>
    

	    <!-- 3. Localisation des douleurs (Graphique en secteurs - Pie chart) -->
	    <div style="padding:50px">
	    <h3 style="color:black !important;">Localisation des douleurs</h3>
	    </div>
	    <!-- Include the chart here with data from the server -->
	    <div class="chart-container">
	        <canvas id="locationChart"></canvas>
	    </div>
	
	<script>
	    // Récupérer les données du servlet et les convertir en JavaScript
	    var locationLabels = ${locationLabels};  // List of location labels from the server
	    var locationData = ${locationData};      // List of location data from the server
	
	 
	    // Configuration du graphique en secteurs (Pie chart)
	    var ctxLocation = document.getElementById('locationChart').getContext('2d');
	    var locationChart = new Chart(ctxLocation, {
	        type: 'pie',
	        data: {
	            labels: locationLabels,
	            datasets: [{
	                data: locationData,
	                backgroundColor: [
	                	'#ffadad', '#9bf6ff','#ffd6a5', '#fdffb6', '#caffbf',  '#a0c4ff', '#bdb2ff', '#ffc6ff', '#e5e5e5',
	                ],
	                borderColor: [
	                	'#ffadad', '#9bf6ff','#ffd6a5', '#fdffb6', '#caffbf',  '#a0c4ff', '#bdb2ff', '#ffc6ff', '#e5e5e5',
	                ],
	                borderWidth: 1
	            }]
	        }
	    
	    
	    });
	</script>

	   <!-- 4. Facteurs aggravants (Graphique en secteurs - Pie chart) -->
	   <div style="padding:50px">
	<h3 style="color:black !important;">Facteurs aggravants</h3>
	</div>
	<!-- Include the chart here with data from the server -->
		<div class="chart-container">
			<canvas id="aggravationChart" ></canvas>
		</div>
	<script>
	    // Récupérer les données du servlet et les convertir en JavaScript
	    var aggravationLabels = ${aggravationLabels};  // List of aggravation labels from the server
	    var aggravationData = ${aggravationData};      // List of aggravation data from the server
	
	    // Configuration du graphique en secteurs (Pie chart)
	    var ctxAggravation = document.getElementById('aggravationChart').getContext('2d');
	    var aggravationChart = new Chart(ctxAggravation, {
	        type: 'pie',
	        data: {
	            labels: aggravationLabels,  // Enclose labels in quotes
	            datasets: [{
	                data: aggravationData,
	                backgroundColor: [
	                	'#ffadad', '#9bf6ff','#ffd6a5', '#fdffb6', '#caffbf',  '#a0c4ff', '#bdb2ff', '#ffc6ff', '#e5e5e5',
	                ],
	                borderColor: [
	                	'#ffadad', '#9bf6ff','#ffd6a5', '#fdffb6', '#caffbf',  '#a0c4ff', '#bdb2ff', '#ffc6ff', '#e5e5e5',
	                ],
	                borderWidth: 1
	            }]
	        }
	    });
	</script>
	<!-- 5. Sentiments (Graphique en secteurs - Pie chart) -->
	<div style="padding:50px">
	<h3 style="color:black !important;">Sentiments</h3>
	</div>
	<!-- Include the chart here with data from the server -->
		<div class="chart-container">
			<canvas id="sentimentChart"></canvas>
		</div>


	<script>
	    // Récupérer les données du servlet et les convertir en JavaScript
	    var sentimentLabels = ${sentimentLabels};  // List of sentiment labels from the server
	    var sentimentData = ${sentimentData};      // List of sentiment data from the server
	
	    // Configuration du graphique en secteurs (Pie chart)
	    var ctxSentiment = document.getElementById('sentimentChart').getContext('2d');
	    var sentimentChart = new Chart(ctxSentiment, {
	        type: 'pie',
	        data: {
	            labels: sentimentLabels,  // Enclose labels in quotes
	            datasets: [{
	                data: sentimentData,
	                backgroundColor: [
	                	'#ffadad', '#9bf6ff','#ffd6a5', '#fdffb6', '#caffbf',  '#a0c4ff', '#bdb2ff', '#ffc6ff', '#e5e5e5',
	                ],
	                borderColor: [
	                	'#ffadad', '#9bf6ff','#ffd6a5', '#fdffb6', '#caffbf',  '#a0c4ff', '#bdb2ff', '#ffc6ff', '#e5e5e5',
	                ],
	                borderWidth: 1
	            }]
	        }
	    });
	    </script>
    </div>
		<!-- Telecharger  -->
	    <div class="container">
	 	    <button id="downloadPageButton" class="btn btn-primary btn-lg my-5" style="margin-left:530px">Télécharger la page</button>
	 	</div>
		 	
     
</div>    
        <script>

    // Récupérez la référence du bouton et de la page
    var downloadPageButton = document.getElementById('downloadPageButton');
    
    // Attachez un gestionnaire d'événements au clic du bouton
    downloadPageButton.addEventListener('click', function() {
        // Utilisez html2canvas pour prendre une capture d'écran du conteneur spécifique
        html2canvas(document.getElementById('capture-container'), { 
            scale: 2,
            height: document.getElementById('capture-container').scrollHeight
        }).then(function(canvas) {
            // Convertissez la capture d'écran en données d'URL au format PNG
            var dataUrl = canvas.toDataURL('image/png');

            // Créez un lien de téléchargement avec la capture d'écran en tant que données d'URL
            var downloadLink = document.createElement('a');
            downloadLink.href = dataUrl;
            downloadLink.download = 'page_screenshot.png';

            // Ajoutez le lien au document et déclenchez le téléchargement
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        });
    });
</script>


	 	
	 	

</body>
</html>
