package com.JAVA.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.JAVA.Beans.Diagnostic;
import com.JAVA.Beans.Diagnostic.EnumAggravation;
import com.JAVA.Beans.Diagnostic.EnumDebutMenstruation;
import com.JAVA.Beans.Diagnostic.EnumDureeCycle;
import com.JAVA.Beans.Diagnostic.EnumDureeRegles;
import com.JAVA.Beans.Diagnostic.EnumNatureMenstruation;
import com.JAVA.Beans.Diagnostic.EnumOuiNon;
import com.JAVA.Beans.Diagnostic.EnumPeriodeDouleurs;
import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.DiagnosticDAO;
import com.JAVA.DAO.DiagnosticDaoImpl;
import com.JAVA.DAO.DiagnosticDaoImpl.Risk;

@WebServlet("/DiagnosticServlet")
public class DiagnosticServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Diagnostic getDiagnosticFromRequest(HttpServletRequest request) {
        // Récupérer les réponses du formulaire
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

        // Créer un objet Diagnostic avec les réponses
        Diagnostic diagnostic = new Diagnostic();

        diagnostic.setDebutMenstruation(EnumDebutMenstruation.valueOf(debutMenstruation));
        diagnostic.setAgePuberte(agePuberte);
        diagnostic.setDouleurRapports(EnumOuiNon.valueOf(douleurRapports));
        diagnostic.setAggravation(EnumAggravation.valueOf(aggravation));
        diagnostic.setAccouchee(EnumOuiNon.valueOf(accouchee));
        diagnostic.setAntecedentsFamiliaux(EnumOuiNon.valueOf(antecedentsFamiliaux));
        diagnostic.setDureeCycle(EnumDureeCycle.valueOf(dureeCycle));
        diagnostic.setDifficulteConception(EnumOuiNon.valueOf(difficulteConception));
        diagnostic.setBmi(bmi);
        diagnostic.setPoids(poids);
        diagnostic.setTaille(taille);
        diagnostic.setIntensiteDouleursAbdominales(intensiteDouleursAbdominales);
        diagnostic.setPeriodeDouleursAbdominales(EnumPeriodeDouleurs.valueOf(periodeDouleursAbdominales));
        diagnostic.setDureeRegles(EnumDureeRegles.valueOf(dureeRegles));
        diagnostic.setNatureMenstruation(EnumNatureMenstruation.valueOf(natureMenstruation));

        return diagnostic;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtain the Diagnostic object from the request
            System.out.println("Début du traitement doPost");

            // Retrieve user information from the session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            // Check if the user is logged in
            if (user != null) {


                Diagnostic diagnostic = getDiagnosticFromRequest(request);
                
                diagnostic.setIdUser(user.getId());

                // Add data to the database
                DiagnosticDAO diagnosticDAO = new DiagnosticDaoImpl(DAOFactory.getInstance());
                diagnosticDAO.create(diagnostic);

                // Calculate the risk
                Risk risk = ((DiagnosticDaoImpl) diagnosticDAO).calculateRisk(diagnostic);

                // Set the risk in the request attribute
                request.setAttribute("risk", risk);
                // Set other attributes in the request
                request.setAttribute("agePuberte", diagnostic.getAgePuberte());
                request.setAttribute("debutMenstruation", diagnostic.getDebutMenstruation());
                request.setAttribute("douleurRapports", diagnostic.getDouleurRapports());
                request.setAttribute("aggravation", diagnostic.getAggravation());
                request.setAttribute("intensiteDouleursAbdominales", diagnostic.getIntensiteDouleursAbdominales());
                request.setAttribute("accouchee", diagnostic.getAccouchee());
                request.setAttribute("antecedentsFamiliaux", diagnostic.getAntecedentsFamiliaux());
                request.setAttribute("dureeCycle", diagnostic.getDureeCycle());
                request.setAttribute("difficulteConception", diagnostic.getDifficulteConception());
                request.setAttribute("bmi", diagnostic.getBmi());
                request.setAttribute("poids", diagnostic.getPoids());
                request.setAttribute("taille", diagnostic.getTaille());
                request.setAttribute("periodeDouleursAbdominales", diagnostic.getPeriodeDouleursAbdominales());
                request.setAttribute("dureeRegles", diagnostic.getDureeRegles());
                request.setAttribute("natureMenstruation", diagnostic.getNatureMenstruation());
                diagnostic.setIdUser(user.getId());

                // Set the Diagnostic object in the request attribute
                request.setAttribute("diagnostic", diagnostic);

                // Forward to the result page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/DiagnosticResultat.jsp");
                dispatcher.forward(request, response);
                System.out.println("Fin du traitement doPost sans erreur");
            } else {
                // User not logged in, redirect to login page
                response.sendRedirect("login.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle number format exception
            e.printStackTrace();
            request.setAttribute("error", "Erreur de format de nombre. Veuillez saisir des valeurs numériques valides.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        } catch (DAOException e) {
            // Handle DAO exception
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'accès à la base de données.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue s'est produite.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
