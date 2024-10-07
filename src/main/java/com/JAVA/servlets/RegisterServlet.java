package com.JAVA.servlets;

import java.io.IOException;

import com.JAVA.Beans.User;
import com.JAVA.DAO.UserDaoImpl;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    // ... (imports et autres annotations de servlet)

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            if (password.equals(confirmPassword)) {
                // Créer un objet User
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                // Utiliser le DAO pour ajouter l'utilisateur à la base de données
                UserDaoImpl userDao = new UserDaoImpl(DAOFactory.getInstance()); // Remplacez DAOFactory() par votre propre initialisation
                userDao.addUser(user);

                // Nettoyer la session (par exemple, après la déconnexion)
                // request.getSession().invalidate();

                // Stocker l'utilisateur dans la session
                request.getSession().setAttribute("user", user);

                // Ajouter un attribut de message de succès à la requête
                request.setAttribute("successMessage", "Inscription réussie !");

                // Rediriger vers la page login.jsp après un enregistrement réussi
                response.sendRedirect("login.jsp");
            } else {
                // Les mots de passe ne correspondent pas, gérer cela en conséquence
                request.setAttribute("error", "Les mots de passe ne correspondent pas");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            }
        } catch (DAOException e) {
            // Gérer les erreurs liées à la base de données
            e.printStackTrace(); // À remplacer par une gestion appropriée des erreurs
            request.setAttribute("error", "Erreur liée à la base de données : " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Gérer d'autres exceptions si nécessaire
            e.printStackTrace(); // Loguer l'exception
            request.setAttribute("error", "Une erreur inattendue s'est produite : " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }
}



