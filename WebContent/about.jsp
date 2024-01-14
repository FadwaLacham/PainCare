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
    


    <section id="faq" style="margin-top: 120px;">
        <h2 class="text-center fw-bold display-4 mb-5">A propos de nous </h2>
        <div class="row align-items-center justify-content-center" style="margin-top: -50px;">
            <div class=" col-md-6 ps-md-5">
                <img src="images/about.jpg" alt="image" class="img-fluid" width="550px" height="550" style="margin-left: 50px;">
            </div>
        <div class=" col-md-6 ps-md-5" >
            <div class="accordion col-md-10 " id="accordionPanelsStayOpenExample" style="border-radius: 10px;">

                <div class="accordion-item mt-3" style="box-shadow: 5px 5px  10px rgba(0, 0, 0, 0.2);">
                    <h5 class="accordion-header border-0" id="panelsStayOpen-headingOne">
                        <button class="accordion-button fs-5" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
                            aria-controls="panelsStayOpen-collapseOne">
                            Quelle est la mission de la solution m-Health PainCare ?

                        </button>
                    </h5>
                    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
                        <div class="accordion-body">
                            <p>La mission de la solution m-Health PainCare est de fournir des ressources complètes et une assistance aux femmes qui luttent contre l'endométriose. Nous visons à offrir un suivi personnalisé des symptômes, des documents éducatifs et une communauté de soutien pour améliorer le bien-être global et la qualité de vie des femmes atteintes d'endométriose.

                            </p>
                        </div>
                    </div>
                </div>

                <div class="accordion-item mt-3" style="box-shadow: 5px 5px  10px rgba(0, 0, 0, 0.2);">
                    <h2 class="accordion-header" id="panelsStayOpen-headingTwo" >
                        <button class="accordion-button fs-5 collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false"
                            aria-controls="panelsStayOpen-collapseTwo">
                            Quels sont les principaux objectifs de PainCare ?
                        </button>
                    </h2>
                    <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse"
                        aria-labelledby="panelsStayOpen-headingTwo">
                        <div class="accordion-body">
                            <p>PainCare vise à donner aux femmes atteintes d'endométriose un outil convivial et accessible pour la gestion de la douleur, les informations et le soutien émotionnel. Notre objectif est d'autonomiser ces femmes, y compris celles qui soupçonnent d'avoir la condition, afin qu'elles puissent prendre le contrôle de leur situation et avancer vers une vie plus heureuse et plus saine.</p>
                        </div>
                    </div>
                </div>

                <div class="accordion-item mt-3" style="box-shadow: 5px 5px  10px rgba(0, 0, 0, 0.2);">
                    <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                        <button class="accordion-button fs-5 collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false"
                            aria-controls="panelsStayOpen-collapseThree">
                            Qui peut bénéficier de PainCare ?
                        </button>
                    </h2>
                    <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse"
                        aria-labelledby="panelsStayOpen-headingThree">
                        <div class="accordion-body">
                            <p>PainCare est conçu pour aider toutes les femmes luttant contre l'endométriose, y compris celles qui soupçonnent d'avoir la condition. Nous sommes là pour fournir un soutien complet, des informations et des outils de gestion de la douleur, contribuant ainsi à améliorer le bien-être global de nos utilisatrices.
                            </p>
                        </div>
                    </div>
                </div>

                
                <div class="accordion-item mt-3"style="box-shadow: 5px 5px  10px rgba(0, 0, 0, 0.2);">
                    <h2 class="accordion-header" id="panelsStayOpen-headingFour">
                        <button class="accordion-button fs-5 collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseFour" aria-expanded="false"
                            aria-controls="panelsStayOpen-collapseFour">
                            Comment PainCare aide-t-il les femmes à prendre le contrôle de leur condition ?
                        </button>
                    </h2>
                    <div id="panelsStayOpen-collapseFour" class="accordion-collapse collapse"
                        aria-labelledby="panelsStayOpen-headingFour">
                        <div class="accordion-body">
                            <p>PainCare donne aux femmes les moyens de prendre le contrôle de leur condition en leur fournissant des outils de suivi personnalisé, des informations éducatives et un accès à une communauté de soutien. En mettant ces ressources à leur disposition, nous encourageons les femmes à avancer vers une vie plus heureuse et plus saine, malgré les défis de l'endométriose.</p>
                        </div>
                    </div>
                </div>





            </div>

        </div>
        </div>
    </section>

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


</body>

</html>