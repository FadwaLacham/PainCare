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
     <link rel="icon" type="image" href="images/logoapp.png">

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
    padding: 8px 12px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 0 8px 8px 0;
    background-color: #f1f1f1;
    cursor: pointer;
    outline: none;
}

button:hover {
    background-color: #ddd;
}
        </style>
    <script>
    function calculateBMI() {
        var poids = document.getElementById("poids").value;
        var tailleCm = document.getElementById("taille").value;

        // Convertir la taille de centimètres en mètres
        var tailleMetres = tailleCm / 100;

        if (poids && tailleMetres) {
            var bmi = (poids / (tailleMetres * tailleMetres)).toFixed(2);
            document.getElementById("bmi").value = bmi;
        }
    }
    </script>        
</head>

<body data-bs-spy="scroll" data-bs-target="#header-nav" tabindex="0">
    <header>
        <img src="images\logoapp.png" alt="Site Icon" style="height: 30px; width: 30px;">
    </header>
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
    
<div style="margin-top: 100px;">
    <div> 
        <section  class="d-flex align-items-center justify-content-center" style="margin-top: 100px;"></section>
            <div class="container position-relative" >
                <div class="row align-items-center pt-5">
                    <div class=" col-md-6 ps-md-5">
                        <img src="images/test.jpg" alt="image" class="img-fluid">
                    </div>
                    <div class="col-md-6 px-4 py-5" mar>
                        
                        <h2 class=" fw-bold display-4 mb-3">Souffrez-vous de regles douleureuses?</h2>
                        <p class="">Repondez a ce questionnaire pour evaluer votre risque d'entometriose.
                        </p>
                        
                        <a href="#qst"><button type="submit" class="btn btn-primary btn-lg">Questionnaire</button></a>
    
    
    
                    </div>
                
        </section>

    </div>
    
</div>
<div id="qst" style="margin-top: 250px;"class="d-flex justify-content-center mx-auto;">
    
<form action="DiagnosticServlet" method="post" class="justify-content-center"  style="background-color: white !important; margin-top:120px">

    <div class=" mb-5" >
        <label for="agePuberte" style="margin-bottom: 10px;">Âge de la Puberté:</label>
        <input type="number" id="agePuberte" name="agePuberte" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required >
    </div>

    <div class=" mb-5" >
        <label for="debutMenstruation"style="margin-bottom: 10px;">Début de Menstruation:</label>
        <select id="debutMenstruation" name="debutMenstruation" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="AVANT_11_ANS">Avant 11 ans</option>
            <option value="APRES_11_ANS">Après 11 ans</option>
        </select>
    </div>

    <div class=" mb-5" >
        <label for="douleurRapports"style="margin-bottom: 10px;">Douleur pendant les rapports:</label>
        <select id="douleurRapports" name="douleurRapports" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="OUI">Oui</option>
            <option value="NON">Non</option>
        </select>
    </div>
    <div class=" mb-5" >
        <label for="aggravation"style="margin-bottom: 10px;">Ce qui les aggrave:</label>
        <select id="aggravation" name="aggravation" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="Mvt_Intestinaux">Mouvement Intestinaux</option>
            <option value="Vessie">La vessie pleine</option>
            <option value="Orgasme">L'orgasme</option>
            <option value="Miction">La miction(uriner)</option> 
            <option value="Aucune">Aucune des reponses</option>  
        </select>
    </div>


    <div class=" mb-5" >
        <label for="accouchee"style="margin-bottom: 10px;">Avez-vous deja accouchée?</label>
        <select id="accouchee" name="accouchee" class="form-control"style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)"  required>
            <option value="OUI">Oui</option>
            <option value="NON">Non</option>
        </select>
    </div>

    <div class=" mb-5  ">
        <label for="antecedentsFamiliaux"style="margin-bottom: 10px;">Antécédents Familiaux:</label>
        <select id="antecedentsFamiliaux" name="antecedentsFamiliaux" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="OUI">Oui</option>
            <option value="NON">Non</option>
        </select>
    </div>

    <div class=" mb-5" >
        <label for="dureeCycle"style="margin-bottom: 10px;">Durée du Cycle:</label>
        <select id="dureeCycle" name="dureeCycle" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="MOINS_DE_27_JOURS">Moins de 27 jours</option>
            <option value="PLUS_DE_27_JOURS">Plus de 27 jours</option>
            <option value="PAS_SUR">Pas sur</option>
        </select>
    </div>

    <div class=" mb-5" >
        <label for="difficulteConception"style="margin-bottom: 10px;">Difficulté de Conception:</label>
        <select id="difficulteConception" name="difficulteConception" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="OUI">Oui</option>
            <option value="NON">Non</option>
        </select>
    </div>

    <div class=" mb-5" >
        <label for="poids"style="margin-bottom: 10px;">Poids (en kg):</label>
        <input type="number" id="poids" name="poids" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required oninput="calculateBMI()">
    </div>

    <div class=" mb-5" >
        <label for="taille"style="margin-bottom: 10px;">Taille (en cm):</label>
        <input type="number" id="taille" name="taille" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required oninput="calculateBMI()">
    </div>

    <div class=" mb-5" >
        <label for="bmi"style="margin-bottom: 10px;">BMI:</label>
        <input type="number" id="bmi" name="bmi" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" readonly>
    </div>

    <div class=" mb-5" >
        <label for="intensiteDouleursAbdominales"style="margin-bottom: 10px;">Intensite des douleurs:</label>
        <input type="number" id="intensiteDouleursAbdominales" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" name="intensiteDouleursAbdominales" class="form-control" required>
    </div>


    <div class=" mb-5" >
        <label for="periodeDouleursAbdominales"style="margin-bottom: 10px;">Période Douleurs Abdominales:</label>
        <select id="periodeDouleursAbdominales" name="periodeDouleursAbdominales" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="LIER_AUX_PERIODES_MENSTRUELLES">Lier aux périodes menstruelles</option>
            <option value="LIER_AUX_OVULATIONS">Lier aux ovulations</option>
            <option value="SANS_LIEN_AVEC_LA_PERIODE_MENTRUELLE_OU_LA_PERIODE_OVULATION">Sans lien avec la période menstruelle ou la période d'ovulation</option>
        </select>
    </div>

    <div class=" mb-5" >
        <label for="dureeRegles"style="margin-bottom: 10px;">Durée des Règles:</label>
        <select id="dureeRegles" name="dureeRegles" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="PAS_SUR">Pas sûr</option>
            <option value="MOINS_DE_7_JOURS">Moins de 7 jours</option>
            <option value="_7_JOURS_OU_PLUS">7 jours ou plus</option>
        </select>
    </div>

    <div class=" mb-5" >
        <label for="natureMenstruation"style="margin-bottom: 10px;">Nature Menstruation:</label>
        <select id="natureMenstruation" name="natureMenstruation" class="form-control" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2)" required>
            <option value="ABONDANTES">Abondantes</option>
            <option value="MODEREES">Modérées</option>
            <option value="LEGERES">Légères</option>
        </select>
    </div>
</div>
    <div class="col-md-12 mb-5  text-center justify-content-center">
        <a href="resultat.html"><button type="submit" class="btn btn-primary" style="background-color:#ff879a !important">Enregistrer</button></a>
    </div>

         </form>
</div>  
    <div> 
        <section id="footer">
            
            <div class="container">
                <footer class="d-flex align-items-center pt-4 text-center">
                    <div class="col-md-6 mx-auto" style="margin-top: 500px;">
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
    <script>
        function calculateBMI() {
            var poids = document.getElementById("poids").value;
            var tailleCm = document.getElementById("taille").value;
    
            // Convertir la taille de centimètres en mètres
            var tailleMetres = tailleCm / 100;
    
            if (poids && tailleMetres) {
                var bmi = (poids / (tailleMetres * tailleMetres)).toFixed(2);
                document.getElementById("bmi").value = bmi;
            }
        }
        </script>

</body>

</html>