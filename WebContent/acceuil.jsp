<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      #hero {
            position: relative;
            overflow: hidden;
            height: 100vh;
        }

        .fade-in-container {
            opacity: 0;
            transition: opacity 0.8s ease-in-out;
        }

        .fade-in-content {
            opacity: 1;
        
        }
      </style>
    

</head>

<body data-bs-spy="scroll" data-bs-target="#header-nav" tabindex="0">


    <nav class="navbar navbar-expand-lg bg-white navbar-light container-fluid py-3 position-fixed " style="background-color: transparent !important; ">
        <div class="container" style="margin-top: -10px; margin-bottom: -10px; ">
            <a class="navbar-brand" href="acceuil.jsp"><img src="images/logoapp.png" alt="logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar">
                <span class="navbar-toggler-icon"></span>
        </button>

                    <div class="d-flex mt-5 mt-lg-0 ps-lg-3 align-items-center justify-content-center ">
                        <button type="button" class="btn btn-primary ms-md-3"> <a href="login.jsp" style="color:white">Log In</a> </button>

                        <button type="button" class="btn btn-primary ms-md-3"> <a href="register.jsp" style="color:white">Register</a> </button>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    
    <section id="hero" class="position-relative overflow-hidden py-4 responsive-image" style="background: url(images/background1.png); background-size: cover; height: 100vh;">
        <!-- <div class="pattern-overlay pattern-right position-absolute">
            <img src="images/pattern-hero.png" alt="pattern">
        </div> -->
        <div class="container py-5 mt-5 fade-in-container" id="fadeInSection">
            <div class="row align-items-center py-5 mt-5 fade-in-content">
                <div class="col-md-8 mt-20 mb-5">
                    <h1 class="text-white" style="font-size: 45px; margin-top: 90px; font-family: Arial, Helvetica, sans-serif;">Découvrez des solutions de santé personnalisées pour votre  <element style="color: #f48399">confort</element> &   <element style="color:#90c3be">soulagement</element>... </h1>
                    <div class="d-flex mt-5 mt-lg-0 ps-lg-3">
                        <a href="register.jsp" type="button" class="btn btn-primary ms-md-3" style="margin-top: 20px;"> Rejoignez-nous </a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="footer">
    
        <div class="container">
            <footer class="d-flex align-items-center pt-4 text-center">
                <div class="col-md-6 mx-auto">
                    <p>© 2024 PainCare - All rights reserved</p>
                </div>

            </footer>
        </div>
    </section>

    

    

    <!-- script ================================================== -->
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/script.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.7/dist/iconify-icon.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <!-- Your script for adding the fade-in effect when the page loads -->
    <script>
        $(document).ready(function () {
            var fadeInSection = $("#fadeInSection");

            // Trigger the fade-in effect when the page is fully loaded
            fadeInSection.addClass("fade-in-content");
        });
    </script>
</body>

</html>