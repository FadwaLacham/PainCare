<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.15.0/font/bootstrap-icons.css">
     <link rel="icon" type="image" href="images/logoapp.png">
    <!-- script ================================================== -->
    <script src="js/modernizr.js"></script>
    <style>
    .responsive-image {
        width: 100%; 
        height: auto;}
  
        .navbar .navbar-brand img {
          width: 70px;
          height: auto;
          border-radius: 25px;
        }
        .search-container {
    display: flex;
    margin-right: 10px;
}

input[type="text"] {
    padding-left: 8px ;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 8px 0 0 8px;
    border-right: none;
    outline: none;
}

button {
    padding: 8px 20px;
    font-size: 16px;
    border: 1px solid #e6e5e5;
    border-radius: 8px ;
    background-color: #f1f1f1;
    cursor: pointer;
    outline: none;
}

button:hover {
    background-color: #ddd;
}

 .label{
    font-size: 20px;
    font-weight: bold;
    margin-right: 10px;
    margin-left: 10px;
    margin-bottom: 10px;
}
        </style>
</head>

<body data-bs-spy="scroll" data-bs-target="#header-nav" tabindex="0">
    
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
                            <a class="nav-link text-uppercase  px-3" href="calendrier.jsp">Calendrier</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="about.jsp">A propos de nous</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-uppercase  px-3" href="alarm.jsp">Alarme</a>
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
    
    </div>



<div class="container">
    <div style="margin-top: 150px;" class="align-items-center justify-content-center " style="margin-left: 50px !important;">
        <div class="container text-center" style="margin-top: 120px; font-weight: 900; ">
            <h1>Faire le Test</h1>
            <hr>
        </div>
        <div class="row align-items-center ">
            <div class=" col-md-6 ps-md-5">
                <img src="images/testsuivi.jpg" alt="image" class="img-fluid" width="570" height="570">
            </div>
            <div class="col-md-6 px-4 py-5" mar>
    <form action="SuiviSymptomServlet" method="post"  style="background-color: #f5f5f5; padding:20px; border-radius: 20px; width:650px !important" >
        <!-- Localisation -->
    <label class="label" for="localisation" >Localisation:</label>
    <input type="checkbox" name="localisation" value="ABDOMEN" id="abdomen"> <label for="abdomen">Abdomen</label>
    <input type="checkbox" name="localisation" value="DOS" id="dos"> <label for="dos">Dos</label>
    <input type="checkbox" name="localisation" value="TETE" id="tete"> <label for="tete">Tête</label>
    <input type="checkbox" name="localisation" value="POITRINE" id="poitrine"> <label for="poitrine">Poitrine</label>
    <input type="checkbox" name="localisation" value="COU" id="cou"> <label for="cou">Cou</label>
    <input type="checkbox" name="localisation" value="HANCHES" id="hanches"> <label for="hanches">Hanches</label>
    
    <br>
    
    
         <!-- Aggravation Douleur -->
    <label for="aggravationDouleur" class="label">Aggravation Douleur:</label>
    <input type="checkbox" name="aggravationDouleur" value="MANQUE_DE_SOMMEIL" id="manque_de_sommeil"> <label for="manque_de_sommeil">Manque de Sommeil</label>
    <input type="checkbox" name="aggravationDouleur" value="ASSIS" id="assis"> <label for="assis">Assis</label>
    <input type="checkbox" name="aggravationDouleur" value="DEBOUT" id="debout"> <label for="debout">Debout</label>
    <input type="checkbox" name="aggravationDouleur" value="STRESS" id="stress"> <label for="stress">Stress</label>
    <input type="checkbox" name="aggravationDouleur" value="MARCHE" id="marche"> <label for="marche">Marche</label>
    <input type="checkbox" name="aggravationDouleur" value="EXERCICE" id="exercice"> <label for="exercice">Exercice</label>
    <input type="checkbox" name="aggravationDouleur" value="MICTION" id="miction"> <label for="miction">Miction</label>
    <!-- Add other aggravation options as needed -->
    
    <br>
    
    
         <!-- Intensite -->
         <label for="intensite" class="label">Intensite:</label>
         <input type="number" name="intensite" id="intensite" required>
    
         <br>
    
         <!-- Sentiments -->
    <label for="sentiments" class="label">Sentiments:</label>
    <input type="checkbox" name="sentiments" value="TRISTE" id="triste"> <label for="triste">Triste</label>
    <input type="checkbox" name="sentiments" value="DEPRIME" id="deprime"> <label for="deprime">Déprimée</label>
    <input type="checkbox" name="sentiments" value="ANXIEUX" id="anxieux"> <label for="anxieux">Anxieuse</label>
    <input type="checkbox" name="sentiments" value="ETOURDI" id="etourdi"> <label for="etourdi">Étourdi</label>
    <input type="checkbox" name="sentiments" value="IRRITEE" id="irritee"> <label for="irritee">Irritée</label>
    <input type="checkbox" name="sentiments" value="HEUREUSE" id="heureuse"> <label for="heureuse">Heureuse</label>
    <!-- Add other sentiment options as needed -->
    
    <br>
    
    
         <!-- Symptoms -->
    <label for="symptom" class="label">Symptoms:</label>
    <input type="checkbox" name="symptom" value="CRAMPES" id="crampes"> <label for="crampes">Crampes</label>
    <input type="checkbox" name="symptom" value="MAUX_DE_TETE" id="maux_de_tete"> <label for="maux_de_tete">Maux de Tête</label>
    <input type="checkbox" name="symptom" value="FATIGUE" id="fatigue"> <label for="fatigue">Fatigue</label>
    <input type="checkbox" name="symptom" value="SEINS_SENSIBLES" id="seins_sensibles"> <label for="seins_sensibles">Seins Sensibles</label>
    <input type="checkbox" name="symptom" value="ACNE" id="acne"> <label for="acne">Acné</label>
    <input type="checkbox" name="symptom" value="BALLONEMENT" id="ballonnement"> <label for="ballonnement">Ballonnement</label>
    <input type="checkbox" name="symptom" value="ENVIE_DE_MANGER" id="envie_de_manger"> <label for="envie_de_manger">Envie de Manger</label>
    <input type="checkbox" name="symptom" value="VOMISSEMENT" id="vomissement"> <label for="vomissement">Vomissement</label>
    <input type="checkbox" name="symptom" value="DIARHEE" id="diarhee"> <label for="diarhee">Diarrhée</label>
    <!-- Add other symptom options as needed -->
    
    <br>
    
    
         <!-- Add other fields as needed -->
    
         <br>
    
         <button type="submit" class="btn-primary btn btn-lg text-center align-items-center justify-content-center">Enregistrer</button>
     </form>
    </div>
        </div>
    </div>
</div> 
       
    <div> 
        <section id="footer">
            
            <div class="container">
                <footer class="d-flex align-items-center pt-4 text-center">
                    <div class="col-md-6 mx-auto" style="margin-top: 100px;">
                        <p>© 2024 PainCare - All rights reserved</p>
                    </div>
        
                </footer>
            </div>
        </section>
        </div> 

    <!-- script ================================================== -->
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/script.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.7/dist/iconify-icon.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <script>
        // Prevent dropdown from closing when checkbox is clicked
        $(document).ready(function() {
            $('#checkboxDropdown').on('click.bs.dropdown', function (e) {
                e.stopPropagation();
            });
        });
    </script>

</body>

</html>