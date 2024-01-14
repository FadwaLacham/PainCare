package com.JAVA.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DownloadResultsServlet")
public class DownloadResultsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the risk parameter from the form submission
        String risk = request.getParameter("risk");

        // Retrieve other form parameters as needed
        String debutMenstruation = request.getParameter("debutMenstruation");
        int agePuberte = Integer.parseInt(request.getParameter("agePuberte"));
        String douleurRapports = request.getParameter("douleurRapports");
        String aggravation = request.getParameter("aggravation");
        int intensiteDouleursAbdominales = Integer.parseInt(request.getParameter("intensiteDouleursAbdominales"));
        String accouchee = request.getParameter("accouchee");
        String antecedentsFamiliaux = request.getParameter("antecedentsFamiliaux");
        String dureeCycle = request.getParameter("dureeCycle");
        String difficulteConception = request.getParameter("difficulteConception");
        double bmi = Double.parseDouble(request.getParameter("bmi"));
        double poids = Double.parseDouble(request.getParameter("poids"));
        double taille = Double.parseDouble(request.getParameter("taille"));
        String periodeDouleursAbdominales = request.getParameter("periodeDouleursAbdominales");
        String dureeRegles = request.getParameter("dureeRegles");
        String natureMenstruation = request.getParameter("natureMenstruation");

        // Retrieve user information from the session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("name");

        // Set the content type and headers for the download
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=diagnostic_results.txt");

        try (PrintWriter out = response.getWriter()) {
            // Write the form responses, diagnostic result, and user information to the response output stream
            out.println("\t\t\tRésultats du diagnostic");
            out.println("\n");
            out.println("\tCatégorie de risque : " + risk);
            out.println("\tDébut de menstruation : " + debutMenstruation);
            out.println("\tAge à la puberté : " + agePuberte);
            out.println("\tDouleur pendant les rapports : " + douleurRapports);
            out.println("\tAggravation : " + aggravation);
            out.println("\tIntensité des douleurs abdominales : " + intensiteDouleursAbdominales);
            out.println("\tAccouchée : " + accouchee);
            out.println("\tAntécédents familiaux : " + antecedentsFamiliaux);
            out.println("\tDurée du cycle : " + dureeCycle);
            out.println("\tDifficulté de conception : " + difficulteConception);
            out.println("\tBMI : " + bmi);
            out.println("\tPoids : " + poids);
            out.println("\tTaille : " + taille);
            out.println("\tPériode des douleurs abdominales : " + periodeDouleursAbdominales);
            out.println("\tDurée des règles : " + dureeRegles);
            out.println("\tNature des menstruations : " + natureMenstruation);

            // Add user information to the output
            out.println("\n\n\tInformations de l'utilisateur : " + username);

            // Ensure that the response is committed
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
