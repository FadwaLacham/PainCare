package com.JAVA.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.JAVA.Beans.Diagnostic;

import com.JAVA.Beans.Diagnostic.EnumAggravation;
import com.JAVA.Beans.Diagnostic.EnumDebutMenstruation;
import com.JAVA.Beans.Diagnostic.EnumDureeCycle;
import com.JAVA.Beans.Diagnostic.EnumDureeRegles;
import com.JAVA.Beans.Diagnostic.EnumNatureMenstruation;
import com.JAVA.Beans.Diagnostic.EnumOuiNon;
import com.JAVA.Beans.Diagnostic.EnumPeriodeDouleurs;

public class DiagnosticDaoImpl implements DiagnosticDAO {
	
	private DAOFactory daoFactory;
	
	public DiagnosticDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	// Méthode utilitaire pour fermer une connexion SQL
private void close(Connection connection) {
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e) {
            // Log the exception or handle it as needed
        }
    }
}

// Méthode utilitaire pour fermer une déclaration préparée SQL
private void close(PreparedStatement preparedStatement) {
    if (preparedStatement != null) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            // Log the exception or handle it as needed
        }
    }
}	
	
	private static Diagnostic map(ResultSet resultSet) throws SQLException {

	    Diagnostic diagnostic = new Diagnostic();
	    diagnostic.getIdUser();
	    diagnostic.setIdDiagnostic(resultSet.getInt("idDiagnostic"));
	    diagnostic.setAgePuberte(resultSet.getInt("agePuberte"));
	    diagnostic.setDebutMenstruation(EnumDebutMenstruation.valueOf(resultSet.getString("debutMenstruation")));
	    diagnostic.setDouleurRapports(EnumOuiNon.valueOf(resultSet.getString("douleurRapports")));
	    diagnostic.setAggravation(EnumAggravation.valueOf(resultSet.getString("aggravation")));
	    diagnostic.setAccouchee(EnumOuiNon.valueOf(resultSet.getString("accouchee")));
	    diagnostic.setAntecedentsFamiliaux(EnumOuiNon.valueOf(resultSet.getString("antecedentsFamiliaux")));
	    diagnostic.setDureeCycle(EnumDureeCycle.valueOf(resultSet.getString("dureeCycle")));
	    diagnostic.setDifficulteConception(EnumOuiNon.valueOf(resultSet.getString("difficulteConception")));
	    diagnostic.setBmi(resultSet.getDouble("bmi"));
	    diagnostic.setPoids(resultSet.getDouble("poids"));
	    diagnostic.setTaille(resultSet.getDouble("taille"));
	    diagnostic.setIntensiteDouleursAbdominales(resultSet.getInt("intensiteDouleursAbdominales"));
	    diagnostic.setPeriodeDouleursAbdominales(EnumPeriodeDouleurs.valueOf(resultSet.getString("periodeDouleursAbdominales")));
	    diagnostic.setDureeRegles(EnumDureeRegles.valueOf(resultSet.getString("dureeRegles")));
	    diagnostic.setNatureMenstruation(EnumNatureMenstruation.valueOf(resultSet.getString("natureMenstruation")));
	    diagnostic.setDateDiagnostic(resultSet.getDate("dateDiagnostic"));

	    return diagnostic;
	}

	
	
	public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql);
		for (int i = 0; i < objets.length; i++) {
			preparedStatement.setObject(i + 1, objets[i]);
		}
		return preparedStatement;
	}

	public void create(Diagnostic diagnostic) throws DAOException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = daoFactory.getConnection();

	        String sql = "INSERT INTO Diagnostic (idUser, agePuberte, debutMenstruation, douleurRapports, aggravation, "
	                + "accouchee, antecedentsFamiliaux, dureeCycle, difficulteConception, bmi, poids, taille, "
	                + "intensiteDouleursAbdominales, periodeDouleursAbdominales, dureeRegles, natureMenstruation, dateDiagnostic) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        preparedStatement = initRequestPrepare(connection, sql,
	                diagnostic.getIdUser(),
	                diagnostic.getAgePuberte(),
	                diagnostic.getDebutMenstruation().name(),
	                diagnostic.getDouleurRapports().name(),
	                diagnostic.getAggravation().name(),
	                diagnostic.getAccouchee().name(),
	                diagnostic.getAntecedentsFamiliaux().name(),
	                diagnostic.getDureeCycle().name(),
	                diagnostic.getDifficulteConception().name(),
	                diagnostic.getBmi(),
	                diagnostic.getPoids(),
	                diagnostic.getTaille(),
	                diagnostic.getIntensiteDouleursAbdominales(),
	                diagnostic.getPeriodeDouleursAbdominales().name(),
	                diagnostic.getDureeRegles().name(),
	                diagnostic.getNatureMenstruation().name(),
	                diagnostic.getDateDiagnostic());

	        // Execute the update (INSERT) query
	        preparedStatement.executeUpdate();

	    } catch (SQLIntegrityConstraintViolationException e) {
	        // Handle foreign key constraint violation
	        String errorMessage = "Error creating diagnostic. Foreign key constraint violation: " + e.getMessage();
	        throw new DAOException(errorMessage, e);
	    } catch (SQLException e) {
	        // Handle other SQL exceptions
	        throw new DAOException("Error creating diagnostic", e);
	    } finally {
	        close(preparedStatement);
	        close(connection);
	    }
	}

	
	/** Calcul du resultat aka le risque d'avoir une endometriose **/
	public enum Risk {

        LOW, MODERATE, HIGH
    }
	
	
	
	public Risk calculateRisk(int idDiagnostic) throws DAOException {
	    try (Connection connection = daoFactory.getConnection()) {
	        String sql = "SELECT * FROM diagnostic WHERE idDiagnostic = ?";
	        try (PreparedStatement preparedStatement = initRequestPrepare(connection, sql, idDiagnostic);
	                ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                Diagnostic diagnostic = map(resultSet);
	                return DiagnosticDaoImpl.calculateRisk(diagnostic);
	            } else {
	                throw new DAOException("Diagnostic not found for idDiagnostic: " + idDiagnostic);
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException("Error calculating risk", e);
	    }
	}


	
	public static Risk calculateRisk(Diagnostic diagnostic) {

	    int totalScore = 0;

	    // Facteurs de risque basés sur les réponses du diagnostic
	    if (diagnostic.getDifficulteConception() == EnumOuiNon.OUI) {
	        totalScore += 5;
	    }

	    // Critère 1 : Age de la puberté
	    int agePuberte = diagnostic.getAgePuberte();
	    if (agePuberte < 11) {
	        totalScore += 3;
	    } else if (agePuberte > 16) {
	        totalScore += 2;
	    }

	    // Critère 2 : Intensité des douleurs abdominales
	    int intensiteDouleurs = diagnostic.getIntensiteDouleursAbdominales();
	    if (intensiteDouleurs >= 7) {
	        totalScore += 4;
	    } else if (intensiteDouleurs >= 5) {
	        totalScore += 2;
	    }

	    // Critère 3 : Duree du Cycle
	    if (diagnostic.getDureeCycle() == EnumDureeCycle.MOINS_DE_27_JOURS) {
	        totalScore += 4;
	    }

	    // Critère 4 : Aggravation douleurs rapport
	    EnumAggravation aggravation = diagnostic.getAggravation();
	    if (aggravation == EnumAggravation.Miction) {
	        totalScore += 4;
	    }

	    // Définition des seuils pour les catégories de risque
	    int lowRiskThreshold = 8;
	    int moderateRiskThreshold = 15;

	    // Attribuez une catégorie de risque en fonction du score total
	    if (totalScore < lowRiskThreshold) {
	        return Risk.LOW;
	    } else if (totalScore < moderateRiskThreshold) {
	        return Risk.MODERATE;
	    } else {
	        return Risk.HIGH;
	    }
	}

	   @Override
	    public Diagnostic getById(int idDiagnostic) throws DAOException {
	        String sql = "SELECT * FROM diagnostic WHERE idDiagnostic = ?";
	        try (Connection connection = daoFactory.getConnection();
	                PreparedStatement preparedStatement = initRequestPrepare(connection, sql, idDiagnostic);
	                ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                return map(resultSet);
	            } else {
	                throw new DAOException("Diagnostic not found for idDiagnostic: " + idDiagnostic);
	            }
	        } catch (SQLException e) {
	            throw new DAOException("Error getting diagnostic by ID", e);
	        }
	    }


	@Override
	public List<Diagnostic> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub

	}


	

	@Override
	public void update(com.JAVA.Beans.Diagnostic diagnostic) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
