package com.JAVA.DAO;

import java.util.List;

import com.JAVA.Beans.SuiviSymptom;

public interface SuiviSymptomDAO {
	void addSuiviSymptoms(SuiviSymptom suiviSymptom) throws DAOException;

    List<SuiviSymptom> getSuiviSymptomByUser(int idUser) throws DAOException;
    
    void updateSuiviSymptom(SuiviSymptom suiviSymptom) throws DAOException;

    void deleteSuiviSymptom(int idSuivi) throws DAOException;



}
