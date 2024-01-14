<%@ page import="java.nio.file.Paths, java.nio.file.Files, java.nio.charset.StandardCharsets, java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.JAVA.Beans.EndometriosisInfo" %>
<%@ page import="java.util.regex.Matcher, java.util.regex.Pattern" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! 
    private String extractValue(String input, String key) {
        String pattern = "\"" + key + "\":\\s*\"(.*?)\"";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Endometriosis Articles</title>
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
      .title2{
      font-size:30px;
      padding-bottom:20px;
      }
      .title2 :hover{
      color: #2087b4;
      text-decoration:underline;

      }
      
      </style>
    
    
    <!-- Ajoutez ici vos liens vers les feuilles de style CSS nécessaires -->
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
    <div class="container">
        <h1>Liste des articles sur l'endométriose</h1>

        <div class="row">
            <% 
                // Obtenir le chemin absolu du fichier JSON
                String jsonFilePath = application.getRealPath("scraped_articles.json");

                // Lire le contenu JSON en une chaîne
                String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);

                // Transformer la chaîne JSON en une liste d'objets Java (vous devrez créer une classe pour représenter les données)
                List<EndometriosisInfo> endometriosisInfos = new ArrayList<>();
                
                // Parsez la chaîne JSON manuellement et ajoutez les objets à la liste
                String[] articles = jsonContent.split("\\},\\s*\\{");
                for (String article : articles) {
                    // Remplacez avec la logique réelle pour extraire les valeurs du JSON
                    String title = extractValue(article, "title");
                    String link = extractValue(article, "link");
                    String description = extractValue(article, "description");
                    String image = extractValue(article, "image");

                    EndometriosisInfo info = new EndometriosisInfo();
                    info.setTitle(title);
                    info.setLink(link);
                    info.setDescription(description);
                    info.setImage(image);

                    endometriosisInfos.add(info);
                }
            %>
<div style="margin-top:100px"></div>
            <% for (EndometriosisInfo endometriosisInfo : endometriosisInfos) { %>
            
                <div style="margin-bottom:50px ;box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); padding:20px; background-color:
#Fff3f6" >
                    <article>
            
                        <div class="title2 title" >
                            <a href='<%= endometriosisInfo.getLink() %>' target="_blank"><%= endometriosisInfo.getTitle() %></a>
                        </div>
                        
                        <p class="post-category"><%= endometriosisInfo.getDescription() %></p>
                    </article>
                </div>
            <% } %>
        </div>
    </div>

    <!-- Ajoutez ici vos scripts JavaScript ou liens vers les fichiers JavaScript nécessaires -->
</body>
</html>
