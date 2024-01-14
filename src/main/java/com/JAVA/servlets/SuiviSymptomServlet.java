package com.JAVA.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import com.JAVA.Beans.SuiviSymptom;
import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.SuiviSymptomDaoImpl;
import com.JAVA.Utils.ConversionUtils;

/**
 * Servlet implementation class SuiviSymptomServlet
 */
@WebServlet("/SuiviSymptomServlet")
public class SuiviSymptomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the HttpSession
        HttpSession session = request.getSession();

        // Retrieve the User object from the session
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            // Handle the case when the user is not in the session (not logged in)
            response.sendRedirect("login.jsp"); // Redirect to the login page or handle as appropriate
            return;
        }

        

		// Récupérer les données du formulaire
		String[] localisations = request.getParameterValues("localisation");
		String[] aggravations = request.getParameterValues("aggravationDouleur");
		int intensite = Integer.parseInt(request.getParameter("intensite"));
		String[] sentiments = request.getParameterValues("sentiments");
		String[] symptoms = request.getParameterValues("symptom");
		int idUser = user.getId();
		// Créer un objet SuiviSymptom avec les données du formulaire
		SuiviSymptom suiviSymptom = new SuiviSymptom();
		suiviSymptom.setIdUser(idUser);
		suiviSymptom.setLocalisation(ConversionUtils.convertStringToLocalisations(localisations));
		suiviSymptom.setAggravationDouleur(ConversionUtils.convertStringToAggravations(aggravations));
		suiviSymptom.setIntensite(intensite);
		suiviSymptom.setSentiments(ConversionUtils.convertStringToSentiments(sentiments));
		suiviSymptom.setSymptom(ConversionUtils.convertStringToSymptoms(symptoms));
		suiviSymptom.setDateRecorded(LocalDate.now());

		// Appeler la méthode addSuiviSymptoms pour ajouter les données à la base de données
		SuiviSymptomDaoImpl suiviDAO = new SuiviSymptomDaoImpl(DAOFactory.getInstance());

		try {
			System.out.println("Debug Information - User ID: " + idUser);
			System.out.println("Debug Information - User Object: " + user.toString());
		    System.out.println("Debug Information - localisations: " + Arrays.toString(localisations));
		    System.out.println("Debug Information - aggravations: " + Arrays.toString(aggravations));
		    System.out.println("Debug Information - intensite: " + intensite);
		    System.out.println("Debug Information - sentiments: " + Arrays.toString(sentiments));
		    System.out.println("Debug Information - symptoms: " + Arrays.toString(symptoms));

		    suiviDAO.addSuiviSymptoms(suiviSymptom);
		    // Ajouter un attribut à la requête pour afficher un message dans la page de confirmation
		    request.setAttribute("successMessage", "Suivi ajouté avec succès!");

		    // Forward to the confirmation page
		    request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (DAOException e) {
			e.printStackTrace();
		    request.setAttribute("errorMessage", "Error adding SuiviSymptom to the database: " + e.getMessage());
		    request.getRequestDispatcher("error.jsp").forward(request, response);
		}
    }
    
}
