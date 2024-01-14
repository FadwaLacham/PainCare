package com.JAVA.servlets;

import com.JAVA.Beans.Blog;
import com.JAVA.Beans.Comment;
import com.JAVA.Beans.User;
import com.JAVA.DAO.BlogDAO;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@WebServlet("/blogServlet")
@MultipartConfig // To handle requests with files (Part)
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BlogDAO blogDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Assuming you have a DAOFactory class to create DAO instances
        blogDAO = DAOFactory.getInstance().getBlogDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "view"; // Default action
        }

        switch (action) {
            case "view":
                viewBlog(request, response, user);
                break;
            case "create":
                showCreateForm(request, response, user);
                break;
            case "search":
                searchBlogs(request, response, user);
                break;
            case "viewDetails":
                viewBlogWithComments(request, response, user);
                break;
            case "update":
                showUpdateForm(request, response, user);
                break;
            default:
                viewBlog(request, response, user);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateBlog(request, response, user);
        } else if ("search".equals(action)) {
            searchBlogs(request, response, user);
        } else if ("create".equals(action)) {
            createBlog(request, response, user);
        }else if ("delete".equals(action)) {
        	deleteBlog(request, response ,user);
        }
}

    private void createBlog(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part picturePart = request.getPart("picture");

        Blog newBlog = new Blog();
        newBlog.setTitle(title);
        newBlog.setDescription(description);
        newBlog.setUser(user);

        if (picturePart != null && picturePart.getSize() > 0) {
            byte[] pictureBytes = new byte[(int) picturePart.getSize()];
            picturePart.getInputStream().read(pictureBytes);
            newBlog.setPicture(pictureBytes);
        }

        blogDAO.addBlog(newBlog);

        response.sendRedirect(request.getContextPath() + "/blogServlet?action=viewAll");
    }
    
    private void viewBlog(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            List<Blog> allBlogs = blogDAO.getAllBlogs();

            // Convert pictures to Base64
            for (Blog blog : allBlogs) {
                byte[] pictureData = blog.getPicture();
                if (pictureData != null) {
                    String pictureBase64 = Base64.getEncoder().encodeToString(pictureData);
                    blog.setPictureBase64(pictureBase64);
                } else {
                    blog.setPictureBase64(""); // Set an empty string or handle it according to your needs
                }
            }

            request.setAttribute("allBlogs", allBlogs);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }



    private void deleteBlog(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        System.out.println("Inside deleteBlog method");

        String blogIdParameter = request.getParameter("blogId");
        System.out.println("blogIdParameter: " + blogIdParameter);

        if (blogIdParameter != null && !blogIdParameter.isEmpty()) {
            int blogId = Integer.parseInt(blogIdParameter);
            System.out.println("Deleting blog with ID: " + blogId);

            try {
                // Supprimer le blog directement
                blogDAO.deleteBlog(blogId);
                System.out.println("Blog deleted successfully");
            } catch (DAOException e) {
                e.printStackTrace();
                System.out.println("Exception occurred");
                // Handle exception or log it
            }
        } else {
            System.out.println("blogId is null or empty");
        }

        // Rediriger vers la page de vue des blogs
        response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
    }



    private void updateBlog(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part picturePart = request.getPart("picture");

        // Assurez-vous que le paramètre blogId est présent dans la requête
        String blogIdParameter = request.getParameter("blogId");

        if (blogIdParameter != null && !blogIdParameter.isEmpty()) {
            try {
                int blogId = Integer.parseInt(blogIdParameter);

                // Récupérez le blog spécifique que l'utilisateur souhaite mettre à jour
                Blog blogToUpdate = blogDAO.getBlogById(blogId);

                // Vérifiez si l'utilisateur est le propriétaire du blog
                if (blogToUpdate != null && blogToUpdate.getUser().getId() == user.getId()) {
                    // Mettez à jour les attributs du blog
                    blogToUpdate.setTitle(title != null ? title : blogToUpdate.getTitle());
                    blogToUpdate.setDescription(description != null ? description : blogToUpdate.getDescription());

                    if (picturePart != null && picturePart.getSize() > 0) {
                        try (InputStream pictureStream = picturePart.getInputStream()) {
                            byte[] pictureBytes = pictureStream.readAllBytes();
                            blogToUpdate.setPicture(pictureBytes);
                        }
                    }

                    // Effectuez la mise à jour uniquement pour le blog spécifique
                    blogDAO.updateBlog(blogToUpdate);

                    // Redirigez vers la page de vue des blogs après la mise à jour
                    response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
                    return; // Make sure to return to avoid further processing
                } else {
                    // L'utilisateur n'est pas le propriétaire du blog ou le blog n'existe pas
                    response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
                }
            } catch (NumberFormatException | IOException | DAOException e) {
                e.printStackTrace(); // Handle exception or log it
                response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
            }
        } else {
            // Gérer le cas où blogId est null ou vide
            response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
        }
    }



    private void searchBlogs(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String searchTitle = request.getParameter("searchTitle");

        try {
            List<Blog> searchResults = blogDAO.searchBlogsByTitle(searchTitle);

            // Convert pictures to Base64
            for (Blog blog : searchResults) {
                String pictureBase64 = Base64.getEncoder().encodeToString(blog.getPicture());
                blog.setPictureBase64(pictureBase64);
            }

            request.setAttribute("searchResults", searchResults);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        // You can perform any additional operations before forwarding to the create_blog_form.jsp
        request.getRequestDispatcher("create_blog.jsp").forward(request, response);
}
    
  
    private void viewBlogWithComments(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            // Récupérer l'ID du blog à partir des paramètres de la requête
            int blogId = Integer.parseInt(request.getParameter("blogId"));

            // Récupérer les détails du blog
            Blog blog = blogDAO.getBlogById(blogId);

            if (blog != null) {
                // Convertir l'image en Base64
                String pictureBase64 = Base64.getEncoder().encodeToString(blog.getPicture());
                blog.setPictureBase64(pictureBase64);

                // Récupérer les commentaires associés au blog
                List<Comment> comments = blogDAO.getCommentsByBlogId(blogId);

                // Récupérer l'utilisateur associé au blog
                User blogUser = blog.getUser();

                // Définir les attributs dans la requête
                request.setAttribute("blog", blog);
                request.setAttribute("comments", comments);
                request.setAttribute("blogUser", blogUser); // Ajout de l'utilisateur associé au blog

                // Transférer la requête à la page JSP pour afficher la vue détaillée
                request.getRequestDispatcher("blogDetails.jsp").forward(request, response);
            } else {
                // Gérer le cas où le blog n'est pas trouvé
                response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
            }
        } catch (DAOException e) {
            e.printStackTrace();
            // Gérer l'exception ou la journaliser
            response.sendRedirect(request.getContextPath() + "/blogServlet?action=view");
        }
    }
    
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
       // Retrieve the blog details based on the blogId from the request parameters
       int blogId = Integer.parseInt(request.getParameter("blogId"));
       Blog blogToUpdate = blogDAO.getBlogById(blogId);

       // Set the blog attribute in the request scope
       request.setAttribute("blog", blogToUpdate);

       // Forward the request to update_blog.jsp
       request.getRequestDispatcher("update_blog.jsp").forward(request, response);
   }




   


}
