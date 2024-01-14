<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.JAVA.Beans.User" %>

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
     <script src="https://apis.google.com/js/platform.js" async defer></script>


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
      @import url('https://fonts.googleapis.com/css2?family=Dancing+Script:wght@600&family=Montserrat:wght@600&family=Mr+Dafoe&display=swap');
      
      
      .navbar {
            transition: background-color 0.3s ease-in-out;
        }

        .bg-transparent {
            background-color: transparent !important;
        }

        .bg-white {
            background-color: white !important;
        }
      </style>
    

</head>

<body data-bs-spy="scroll" data-bs-target="#header-nav" tabindex="0">


    <nav class="navbar navbar-expand-lg bg-transparent navbar-light container-fluid py-3 position-fixed">
        <div class="container" style="margin-top: -10px; margin-bottom: -10px;">
            
            <a class="navbar-brand" href="acceuil.jsp"><img src="images/logoapp.png" alt="logo"></a>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar">
                
            </button>

                    <div class="d-flex mt-5 mt-lg-0 ps-lg-3 align-items-center justify-content-center ">
                        
                        <a href="login.jsp"type="button" class="btn btn-primary ms-md-3"> Log In </a>
                    </div>
                </div>
            
    </nav>

    <section id="hero" class="position-relative overflow-hidden py-4 responsive-image" style="background: url(images/background1.png); ">
        <!-- <div class="pattern-overlay pattern-right position-absolute">
            <img src="images/pattern-hero.png" alt="pattern">
        </div> -->
        <div class="container py-5 mt-5">
            <div class="row align-items-center py-5 mt-5">
                <div class="col-md-6 mb-5 mb-md-0">
                    
                    <h1 class="text-white" style="font-size: 55px; font-family:Arial, Helvetica, sans-serif;">Bienvenu sur <element style="color:#f48399">Pain</element><element style="color:#90c3be">Care</element> </h1>
                    <div class="d-flex mt-5 mt-lg-0 ps-lg-3  ">
                        
                        <button type="button" class="btn btn-primary ms-md-3" style="margin-top: 20px;"> Rejoignez-nous </button>
                    </div>

                </div>
                <div class=" col-md-5 offset-md-1">
                    <form class="hero-form p-5" action="register" method="post">
                        <div style="display: flex;  justify-content: center;">
                        <h2>Sign Up </h2>
                        </div>
                        <div style="display: flex;  justify-content: center;">
                        <h6>For PainCare</h6>
                        </div>
                        <div class="mb-3">
                            <label for="name" class="form-label mb-1" style="font-weight: bold; font-size: 20px;">Entrer votre nom: </label>
                            <input type="name" class="form-control border-0" id="name" name="name" placeholder="votre nom...">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label mb-1" style="font-weight: bold; font-size: 20px;">Entrer votre Email: </label>
                            <input type="email" class="form-control border-0" id="email" name="email" placeholder="votre email...">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label mb-1" style="font-weight: bold; font-size: 20px;">Entrer votre mot de passe: </label>
                            <input type="password" class="form-control border-0" id="password" name="password" placeholder="votre mot de passe...">
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label mb-1" style="font-weight: bold;font-size: 20px;">Confirmer votre mot de passe: </label>
                            <input type="password" class="form-control border-0" id="confirmPassword" name="confirmPassword" placeholder="confirmer votre mot de passe...">
                        </div>
                        

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg" value="S'inscrir">S'inscrire</button>
                        </div>
                        <a href="#" onclick="authentifierAvecGoogle()"><div style="display: flex;
                            justify-content: center;
                            align-items: center; color:grey; padding-top: 20px;"></div></a>
                    </form>

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

    <script>
        $(document).ready(function () {
          var navbar = $(".navbar");
          var navHeight = navbar.outerHeight();
      
          // Add an event listener for the scroll event
          $(window).scroll(function () {
            // Check the scroll position
            var scroll = $(window).scrollTop();
      
            // If the scroll position is greater than the navbar height, change the background color
            if (scroll > navHeight) {
              navbar.removeClass("bg-transparent").addClass("bg-white");
            } else {
              navbar.removeClass("bg-white").addClass("bg-transparent");
            }
          });
        });
      </script>


    // Appeler la fonction d'initialisation au chargement de la page
    initGoogleSignIn();
</script>
      


</body>

</html>