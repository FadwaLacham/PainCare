<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Share</title>
</head>
<body>
    <h2>Share on Instagram and Facebook</h2>
    
    <div class="blog">
        <h3><a href="blogServlet?action=viewDetails&blogId=${blog.blogId}">${blog.title}</a></h3>

        <div class="blog-info">
            <p class="user-info">Created by ${blog.user.name}</p>
            <p class="user-info">Date: ${blog.publicationDate}</p>
        </div>

        <img src="data:image/jpeg;base64,${blog.pictureBase64}" alt="Blog Image">
        <p id="blogDescription_${blog.blogId}">${blog.description}</p>
        
    </div>

    <!-- Boutons de partage -->
    <a href="https://www.facebook.com/sharer/sharer.php?u=${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/blogServlet?action=viewDetails&blogId=${blog.blogId}" target="_blank" rel="noopener noreferrer">
        Share on Facebook
    </a>

    <!-- Pour Instagram, vous pouvez donner à l'utilisateur la possibilité de télécharger l'image -->
    <a href="data:image/jpeg;base64,${blog.pictureBase64}" download="blog_image.jpg">
        Download Image for Instagram
    </a>
</body>
</html>
