package com.JAVA.DAO;

import java.util.List;

import com.JAVA.Beans.Diagnostic;
import com.JAVA.DAO.DiagnosticDaoImpl.Risk;

public interface DiagnosticDAO {
    public void create(Diagnostic diagnostic) throws DAOException;
    
	Diagnostic getById(int id) throws DAOException;
    
	List<Diagnostic> getAll() throws DAOException;
	
    public static Risk calculateRisk(Diagnostic diagnostic) {
		// TODO Auto-generated method stub
		return null;
	}
    public Risk calculateRisk(int idUser) throws DAOException ;

	void update(Diagnostic diagnostic) throws DAOException;
	
	void delete(int id) throws DAOException;
    
}