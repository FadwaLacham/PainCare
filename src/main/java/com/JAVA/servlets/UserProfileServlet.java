package com.JAVA.servlets;

import java.io.IOException;
import java.util.Base64;

import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/userProfile")
@MultipartConfig // Pour gérer les requêtes avec des fichiers (Part)
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            String action = request.getParameter("action");

            if (action == null) {
                action = "view"; // Action par défaut
            }

            switch (action) {
                case "view":
                    viewProfile(request, response, user);
                    break;
                case "update":
                    showUpdateForm(request, response, user);
                    break;
                case "delete":
                    deleteProfile(request, response, user);
                    break;
                default:
                    viewProfile(request, response, user);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            String action = request.getParameter("action");

            if ("update".equals(action)) {
                updateProfile(request, response, user);
            }
            // Ajoutez d'autres actions si nécessaire
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

 // Dans la méthode viewProfile de UserProfileServlet
 // Dans la méthode viewProfile de UserProfileServlet
    private void viewProfile(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            // Vérifier si l'utilisateur est null avant de l'utiliser
            if (user != null) {
                user = userDAO.getUserById(user.getId());

                // Vérifier si l'image n'est pas null avant de l'encoder en Base64
                byte[] pictureBytes = user.getPicture();
                if (pictureBytes != null) {
                    String pictureBase64 = Base64.getEncoder().encodeToString(pictureBytes);
                    user.setPictureBase64(pictureBase64);
                }

                request.setAttribute("userProfile", user);
                request.getRequestDispatcher("user_view_profile.jsp").forward(request, response);
            } else {
                // Gérer le cas où user est null (par exemple, rediriger vers une page d'erreur)
                response.sendRedirect("error.jsp");
            }
        } catch (DAOException e) {
            e.printStackTrace();
            // Gérer les erreurs liées à la base de données
            response.sendRedirect("error.jsp");
        }
    }


    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            user = userDAO.getUserById(user.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.setAttribute("userProfile", user);
        request.getRequestDispatcher("user_update_profile.jsp").forward(request, response);
    }

    private void deleteProfile(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            System.out.println("Delete Profile method called.");
            userDAO.deleteUser(user.getId());
            // Invalidez la session après la suppression du profil
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("userDeleteError", "An error occurred while deleting the profile.");
            viewProfile(request, response, user);
        }
    }
    
 
    private void updateProfile(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Part picturePart = request.getPart("picture");

        user.setName(name);
        user.setEmail(email);

        if (picturePart != null && picturePart.getSize() > 0) {
            byte[] pictureBytes = new byte[(int) picturePart.getSize()];
            picturePart.getInputStream().read(pictureBytes);
            user.setPicture(pictureBytes);
        }

        try {
            userDAO.updateUser(user);
            request.setAttribute("userUpdateSuccess", "true");
            viewProfile(request, response, user);
        } catch (DAOException e) {
            request.setAttribute("userError", "An error occurred while updating the profile.");
            showUpdateForm(request, response, user);
        }
    }
}

