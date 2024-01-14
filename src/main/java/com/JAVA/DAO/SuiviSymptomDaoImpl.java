package com.JAVA.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.JAVA.Utils.ConversionUtils;

import com.JAVA.Beans.SuiviSymptom;

/**
 * 
 */
public class SuiviSymptomDaoImpl implements SuiviSymptomDAO {
	
	
	private DAOFactory daoFactory;
	
	public SuiviSymptomDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	private static SuiviSymptom map(ResultSet resultSet) throws SQLException {
        SuiviSymptom suiviSymptom = new SuiviSymptom();

        suiviSymptom.setIdSuivi(resultSet.getInt("idSuivi"));
        suiviSymptom.setIdUser(resultSet.getInt("idUser"));
        suiviSymptom.setLocalisation(ConversionUtils.convertStringToLocalisations(resultSet.getString("localisation").split(",")));
        suiviSymptom.setAggravationDouleur(ConversionUtils.convertStringToAggravations(resultSet.getString("aggravationDouleur").split(",")));
        suiviSymptom.setIntensite(resultSet.getInt("intensite"));
        suiviSymptom.setSentiments(ConversionUtils.convertStringToSentiments(resultSet.getString("sentiments").split(",")));
        suiviSymptom.setDateRecorded(resultSet.getDate("dateRecorded").toLocalDate());
        suiviSymptom.setSymptom(ConversionUtils.convertStringToSymptoms(resultSet.getString("symptom").split(",")));



        // Assuming there is a column "symptoms" in your database, and you need to map it as well.
        // This could involve retrieving a list of symptoms associated with the suiviSymptom's ID.
        // For example, suiviSymptom.setSymptoms(mapSymptoms(resultSet.getInt("idSuivi")));

        return suiviSymptom;
    }

	    
	    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets)
				throws SQLException {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			for (int i = 0; i < objets.length; i++) {
				preparedStatement.setObject(i + 1, objets[i]);
			}
			return preparedStatement;
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
	    
	    private void close(AutoCloseable resource) {
	        if (resource != null) {
	            try {
	                resource.close();
	            } catch (Exception e) {
	                e.printStackTrace();  // Log or handle the exception appropriately
	            }
	        }
	    }
	    
	    //
	    public void addSuiviSymptoms(SuiviSymptom suiviSymptom) throws DAOException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = daoFactory.getConnection();

	            String insertSql = "INSERT INTO suivisymptom (idUser ,localisation, aggravationDouleur, intensite, sentiments, dateRecorded, symptom) VALUES (?,?, ?, ?, ?, ?, ?)";

	            preparedStatement = initRequestPrepare(connection, insertSql,
	                    suiviSymptom.getIdUser(),
	                    ConversionUtils.convertLocalisationsToString(suiviSymptom.getLocalisation()),
	                    ConversionUtils.convertAggravationsToString(suiviSymptom.getAggravationDouleur()),
	                    suiviSymptom.getIntensite(),
	                    ConversionUtils.convertSentimentsToString(suiviSymptom.getSentiments()),
	                    java.sql.Date.valueOf(suiviSymptom.getDateRecorded()),
	                    ConversionUtils.convertSymptomsToString(suiviSymptom.getSymptom()));

	            // Execute the update (INSERT) query
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            throw new DAOException("Error adding SuiviSymptom to the database", e);
	        } finally {
	            close(preparedStatement);
	            close(connection);
	        }
	    }

	    
	    
	    

	    @Override
	    public List<SuiviSymptom> getSuiviSymptomByUser(int idUser) throws DAOException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<SuiviSymptom> suiviSymptoms = new ArrayList<>();

	        try {
	            connection = daoFactory.getConnection();

	            String selectSql = "SELECT * FROM suivisymptom WHERE idUser = ?";
	            preparedStatement = initRequestPrepare(connection, selectSql, idUser);

	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                SuiviSymptom suiviSymptom = map(resultSet);
	                suiviSymptoms.add(suiviSymptom);
	            }

	        } catch (SQLException e) {
	            throw new DAOException("Error retrieving SuiviSymptom records for user with ID: " + idUser, e);
	        } finally {
	            close(resultSet);
	            close(preparedStatement);
	            close(connection);
	        }

	        return suiviSymptoms;
	    }
	    
	    
	   //methods pour la chart 
	    public double calculateAverageIntensity(int userId) throws DAOException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = daoFactory.getConnection();

	            String query = "SELECT AVG(intensite) AS average_intensity FROM suivisymptom WHERE idUser = ?";
	            System.out.println("Query: " + query);  // Add this line
	            preparedStatement = initRequestPrepare(connection, query, userId);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                return resultSet.getDouble("average_intensity");
	            }

	        } catch (SQLException e) {
	            throw new DAOException("Error calculating average intensity", e);
	        } finally {
	        	close(resultSet);  // Close the ResultSet first
	            close(preparedStatement);
	            close(connection);
	        }

	        return 0; // Default return value if no result is found
	    }
	    
	    
	    
	   // Method to get counts of aggravations for a given user

	    
	    public Map<String, Long> getAggravationCounts(int userId) throws DAOException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = daoFactory.getConnection();

	            String query = "SELECT aggravationDouleur FROM suivisymptom WHERE idUser = ?";
	            preparedStatement = initRequestPrepare(connection, query, userId);
	            resultSet = preparedStatement.executeQuery();

	            Map<String, Long> aggravationCounts = new HashMap<>();

	            while (resultSet.next()) {
	                String[] aggravations = resultSet.getString("aggravationDouleur").split(",");
	                for (String aggravation : aggravations) {
	                    aggravationCounts.put(aggravation, aggravationCounts.getOrDefault(aggravation, 0L) + 1);
	                }
	            }

	            return aggravationCounts;

	        } catch (SQLException e) {
	            throw new DAOException("Error getting aggravation counts", e);
	        } finally {
	            close(resultSet);
	            close(preparedStatement);
	            close(connection);
	        }
	    }
	    

	    /**
	     *  Method to get counts of locations for a given user
	     * @param idUser
	     * @return Map<String, Long>
	     * @throws DAOException
	     */
		public Map<String, Long> getLocationCounts(int idUser) throws DAOException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = daoFactory.getConnection();

	            String query = "SELECT localisation FROM suivisymptom WHERE idUser = ?";
	            preparedStatement = initRequestPrepare(connection, query,  idUser);
	            resultSet = preparedStatement.executeQuery();

	            Map<String, Long> locationCounts = new HashMap<>();

	            while (resultSet.next()) {
	                String[] localisations = resultSet.getString("localisation").split(",");
	                for (String localisation : localisations) {
	                	locationCounts.put(localisation, locationCounts.getOrDefault(localisation, 0L) + 1);
	                }
	            }

	            return locationCounts;

	        }catch (SQLException e) {
	            throw new DAOException("Error getting aggravation counts", e);
	        } finally {
	            close(resultSet);
	            close(preparedStatement);
	            close(connection);
	        }
	    
		}
		
		/** 
		 * Method to get counts of sentiments for a given user
		 * 
		 **/
		public Map<String, Long> getSentimentCounts(int userId) throws DAOException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = daoFactory.getConnection();

	            String query = "SELECT sentiments FROM suivisymptom WHERE idUser = ?";
	            preparedStatement = initRequestPrepare(connection, query, userId);
	            resultSet = preparedStatement.executeQuery();

	            Map<String, Long> sentimentCounts = new HashMap<>();

	            while (resultSet.next()) {
	                String[] sentiments = resultSet.getString("sentiments").split(",");
	                for (String sentiment : sentiments) {
	                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0L) + 1);
	                }
	            }

	            return sentimentCounts;

	        } catch (SQLException e) {
	            throw new DAOException("Error getting sentiment counts", e);
	        } finally {
	            close(resultSet);
	            close(preparedStatement);
	            close(connection);
	        }
	    }


	@Override
	public void updateSuiviSymptom(SuiviSymptom suiviSymptom) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSuiviSymptom(int idSuivi) throws DAOException {
		// TODO Auto-generated method stub

	}

	

}
