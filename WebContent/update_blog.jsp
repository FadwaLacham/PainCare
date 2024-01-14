<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Blog</title>
    <!-- Add your stylesheets or any other required resources here -->
</head>
<body>
    <div class="container mt-3">
        <!-- Your form for updating the blog -->
        <form action="blogServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="blogId" value="${param.blogId}">
            
            <!-- Include the necessary input fields for updating the blog -->
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="${blog.title}">
            
            <label for="description">Description:</label>
            <textarea id="description" name="description">${blog.description}</textarea>
            
            <!-- Display the existing picture if it exists -->
            <c:if test="${not empty blog.pictureBase64}">
                <img src="data:image/jpeg;base64,${blog.pictureBase64}" alt="Current Picture">
            </c:if>

            <label for="picture">New Picture:</label>
            <input type="file" id="picture" name="picture">
            
            <!-- Include any other input fields as needed -->

            <button type="submit">Update Blog</button>
        </form>
    </div>
    <!-- Include your scripts or any other required resources here -->
</body>
</html>
