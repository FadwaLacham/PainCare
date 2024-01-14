package com.JAVA.Beans;

import java.time.LocalDate;
import java.util.List;

public class SuiviSymptom {
	private int idSuivi;
    private int idUser;
    private List<SymptomType> symptom;
    private List<LocalisationType> localisation;
    private List<AggravationType> aggravationDouleur;
    private int intensite;
    private List<SentimentType> sentiments;
    private LocalDate dateRecorded;

    // Constructeurs, getters, setters

    /**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}



	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}



	/**
	 * Constructor
	 */
	public SuiviSymptom() {
		super();
		// TODO Auto-generated constructor stub
	}


    
 /**
	 * @return the idSuivi
	 */
	public int getIdSuivi() {
		return idSuivi;
	}

	/**
	 * @param idSuivi the idSuivi to set
	 */
	public void setIdSuivi(int idSuivi) {
		this.idSuivi = idSuivi;
	}

	/**
	 * @return the symptom
	 */
	public List<SymptomType> getSymptom() {
		return symptom;
	}

	/**
	 * @param symptom the symptom to set
	 */
	public void setSymptom(List<SymptomType> symptom) {
		this.symptom = symptom;
	}

	/**
	 * @return the localisation
	 */
	public List<LocalisationType> getLocalisation() {
		return localisation;
	}

	/**
	 * @param localisation the localisation to set
	 */
	public void setLocalisation(List<LocalisationType> localisation) {
		this.localisation = localisation;
	}

	/**
	 * @return the aggravationDouleur
	 */
	public List<AggravationType> getAggravationDouleur() {
		return aggravationDouleur;
	}

	/**
	 * @param aggravationDouleur the aggravationDouleur to set
	 */
	public void setAggravationDouleur(List<AggravationType> aggravationDouleur) {
		this.aggravationDouleur = aggravationDouleur;
	}

	/**
	 * @return the intensite
	 */
	public int getIntensite() {
		return intensite;
	}

	/**
	 * @param intensite the intensite to set
	 */
	public void setIntensite(int intensite) {
		this.intensite = intensite;
	}

	/**
	 * @return the sentiments
	 */
	public List<SentimentType> getSentiments() {
		return sentiments;
	}

	/**
	 * @param sentiments the sentiments to set
	 */
	public void setSentiments(List<SentimentType> sentiments) {
		this.sentiments = sentiments;
	}

	/**
	 * @return the dateRecorded
	 */
	public LocalDate getDateRecorded() {
		return dateRecorded;
	}

	/**
	 * @param dateRecorded the dateRecorded to set
	 */
	public void setDateRecorded(LocalDate dateRecorded) {
		this.dateRecorded = dateRecorded;
	}



	// Enum pour le type de symptôme
    public enum SymptomType {
        CRAMPES,
        MAUX_DE_TETE,
        FATIGUE,
        SEINS_SENSIBLES,
        ACNE,
        BALLONEMENT,
        ENVIE_DE_MANGER,
        VOMISSEMENT,
        DIARHEE
        
    }

    // Enum pour le type de localisation
    public enum LocalisationType {
        ABDOMEN,
        DOS,
        TETE,
        POITRINE,
        COU,
        HANCHES
    }

    // Enum pour le type d'aggravation de la douleur
    public enum AggravationType {
        MANQUE_DE_SOMMEIL,
        ASSIS,
        DEBOUT,
        STRESS,
        MARCHE,
        EXERCICE,
        MICTION
    }

    // Enum pour le type de sentiment
    public enum SentimentType {
        TRISTE,
        DEPRIME,
        ANXIEUX,
        ETOURDI,
        IRRITEE,
        HEUREUSE
        
        
    }
}
