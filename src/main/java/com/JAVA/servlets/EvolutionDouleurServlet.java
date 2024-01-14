package com.JAVA.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.JAVA.Beans.SuiviSymptom;
import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.SuiviSymptomDaoImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class EvolutionDouleurServlet
 */
@WebServlet("/EvolutionDouleurServlet")
public class EvolutionDouleurServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        //DEBUGGING MSG
        System.out.println("User: " + user.getName() + " (ID: " + user.getId() + ")");

        SuiviSymptomDaoImpl suiviSymptomDao = new SuiviSymptomDaoImpl(DAOFactory.getInstance());

        // 1. Moyenne de l'intensité de la douleur
        double averageIntensity = suiviSymptomDao.calculateAverageIntensity(user.getId());
        
        //DEBUGGING MSG : INTENSITY AVG
        System.out.println("Average Intensity: " + averageIntensity);

        request.setAttribute("averageIntensity", averageIntensity);
       

     // 2. Intensité en fonction de la date (Graphique en ligne)
        List<SuiviSymptom> suiviSymptoms = suiviSymptomDao.getSuiviSymptomByUser(user.getId());
        List<String> dates = new ArrayList<>();
        List<Integer> intensities = new ArrayList<>();

        for (SuiviSymptom suiviSymptom : suiviSymptoms) {
            LocalDate dateRecorded = suiviSymptom.getDateRecorded();

            if (dateRecorded != null) {
                String formattedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(java.sql.Date.valueOf(dateRecorded));
                dates.add("'" + formattedDate + "'");  // Enclose the date in quotes
                intensities.add(suiviSymptom.getIntensite());
            }
        }

        request.setAttribute("dates", String.join(",", dates));  // Convert list to a comma-separated string
        request.setAttribute("intensities", intensities);




        // 3. Localisation des douleurs (Graphique en secteurs - Pie chart)
    
        Map<String, Long> locationCounts = suiviSymptomDao.getLocationCounts(user.getId());
        List<String> locations = new ArrayList<>(locationCounts.keySet());
        List<Long> counts = new ArrayList<>(locationCounts.values());

        // Enclose each location label in quotes
        List<String> quotedLocations = locations.stream().map(label -> "'" + label + "'").collect(Collectors.toList());

        request.setAttribute("locationLabels", quotedLocations);
        request.setAttribute("locationData", counts);

        // 4. Facteurs aggravants (Graphique en secteurs - Pie chart)
        Map<String, Long> aggravationCounts = suiviSymptomDao.getAggravationCounts(user.getId());
        List<String> aggravations = new ArrayList<>(aggravationCounts.keySet());
        List<Long> aggravationData = new ArrayList<>(aggravationCounts.values());
        // Enclose each agg label in quotes
        List<String> quotedAggravations = aggravations.stream().map(label -> "'" + label + "'").collect(Collectors.toList());

        request.setAttribute("aggravationLabels", quotedAggravations);
        request.setAttribute("aggravationData", aggravationData);

        // 5. Sentiments (Graphique en secteurs - Pie chart)
        Map<String, Long> sentimentCounts = suiviSymptomDao.getSentimentCounts(user.getId());
        List<String> sentiments = new ArrayList<>(sentimentCounts.keySet());
        List<Long> sentimentData = new ArrayList<>(sentimentCounts.values());

        // Enclose each sentiments label in quotes
        List<String> quotedSentiments = sentiments.stream().map(label -> "'" + label + "'").collect(Collectors.toList());

        request.setAttribute("sentimentLabels", quotedSentiments);
        request.setAttribute("sentimentData", sentimentData);

        RequestDispatcher dispatcher = request.getRequestDispatcher("evolutionDouleur.jsp");
        dispatcher.forward(request, response);
    }
}
