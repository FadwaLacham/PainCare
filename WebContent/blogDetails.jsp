<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    </div>

    <section id="about">
        <div class="container py-5">
            <div class="row align-items-center py-5">
                <div class=" col-md-6 ps-md-5">
                    <img src="data:image/jpeg;base64,${blog.pictureBase64}" alt="image" class="img-fluid">
                </div>
                <div class="col-md-6 px-4 py-5" mar>
                    <h6 class="">${blog.user.name}</h6>
                    <h2 class=" fw-bold display-4 mb-3">${blog.title}</h2>
                    <p class="">${blog.description}
                    </p>
                    
              



                </div>
            </div>
        </div>
    </section>
    <div class="comments p-5 m-5">

        <h4 class="comments-count" style="margin-top: -150px !important;"></h4>

        
        <c:forEach var="comment" items="${comments}">  
          <div id="comment-1" class="comment p-2 mb-4" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);">
            <div class="d-flex">
              <div>
                
                <h5><a href="">${comment.user.name}</a> </h5>
                <time datetime="2020-01-01">${comment.commentDate}</time>
                <p>
                    ${comment.content}
                </p>

            <c:if test="${comment.user.id eq sessionScope.user.id}"> 
                <form action="${pageContext.request.contextPath}/commentServlet" method="post" >
                    <div class="d-flex align-items-center">
                        <p class="m-0">Supprimer</p>
                        <input type="hidden" name="action" value="deleteComment">
                        <input type="hidden" name="commentId" value="${comment.commentId}">
                    <button type="submit" style="background: none; border: none; margin-right: 10px;" onclick="deleteItem()">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                        </svg>
                    </button>
                    </div>
                </form>
            </c:if>
              </div>
            </div>
          </div>
        </c:forEach>


         <c:if test="${not empty newComment}"> 
          <div id="comment-1" class="comment p-2 mb-4" style="box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);">
            <div class="d-flex">
              <div>
                
                <h5><a href="">${newComment.user.name}</a> </h5>
                <time datetime="2020-01-01">${newComment.commentDate}</time>
                <p>
                    ${newComment.content}
                </p>

            <c:if test="${newComment.user.id eq sessionScope.user.id}"> 
                <form action="${pageContext.request.contextPath}/commentServlet" method="post" >
                    <div class="d-flex align-items-center">
                        <p class="m-0">Supprimer</p>
                        <input type="hidden" name="action" value="deleteComment">
                        <input type="hidden" name="commentId" value="${newComment.commentId}">
                    <button type="submit" style="background: none; border: none; margin-right: 10px;" onclick="deleteItem()">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                        </svg>
                    </button>
                    </div>
                </form>
            </c:if>
              </div>
            </div>
          </div>
        </c:if>  

          <!-- End comment #1 -->

          

          <div class="reply-form" >
          <h4 class="com">Ajouter un commentaire </h4>
          <form action="${pageContext.request.contextPath}/commentServlet" method="post">
            <input type="hidden" name="action" value="addComment">
            <input type="hidden" name="blogId" value="${blog.blogId}">           
            <div class="row align-items-center justify-content-center">
                <div class="col form-group">
                    <textarea id="content" name="content"  rows="4" class="form-control" placeholder="Ajouter un commentaire..." required></textarea>
                </div>
                <div class="col-auto text-center">
                    <button type="submit" class="btn btn-primary m-2" style="box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2); ">Publier</button>
                </div>
            </div>
        

          </form>

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
    <script>
    function shareBlog(blogId) {
        var blogDescriptionElement = document.getElementById('blogDescription_' + blogId);
        var blogDescription = blogDescriptionElement.innerText;

        if (navigator.share) {
            navigator.share({
                title: blog.title,
                text: blogDescription,
                url: window.location.href
            })
            .then(() => console.log('Partage réussi'))
            .catch((error) => console.error('Erreur de partage:', error));
        } else {
            console.log('L\'API de partage n\'est pas prise en charge sur ce navigateur.');
            // Mettez en œuvre une alternative si l'API de partage n'est pas prise en charge
        }
    }
</script>


</body>

</html>