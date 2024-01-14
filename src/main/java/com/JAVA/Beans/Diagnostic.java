package com.JAVA.Beans;
import java.util.Date;

public class Diagnostic {

    private int idDiagnostic;
    private int idUser;
    private int agePuberte;
    private EnumDebutMenstruation debutMenstruation;
    private EnumOuiNon douleurRapports;
    private EnumAggravation aggravation;
    private EnumOuiNon accouchee;
    private EnumOuiNon antecedentsFamiliaux;
    private EnumDureeCycle dureeCycle;
    private EnumOuiNon difficulteConception;
    private double bmi;
    private double poids;
    private double taille;
    private int intensiteDouleursAbdominales;
    private EnumPeriodeDouleurs periodeDouleursAbdominales;
    private EnumDureeRegles dureeRegles;
    private EnumNatureMenstruation natureMenstruation;
    private Date dateDiagnostic;
    private User user;

    // Constructeurs, getters, setters
	/**
	 * 
	 */
	public Diagnostic() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	

	/**
	 * @return the idDiagnostic
	 */
	public int getIdDiagnostic() {
		return idDiagnostic;
	}




	/**
	 * @param idDiagnostic the idDiagnostic to set
	 */
	public void setIdDiagnostic(int idDiagnostic) {
		this.idDiagnostic = idDiagnostic;
	}




	/**
	 * @return the agePuberte
	 */
	public int getAgePuberte() {
		return agePuberte;
	}

	/**
	 * @param agePuberte the agePuberte to set
	 */
	public void setAgePuberte(int agePuberte) {
		this.agePuberte = agePuberte;
	}

	/**
	 * @return the debutMenstruation
	 */
	public EnumDebutMenstruation getDebutMenstruation() {
		return debutMenstruation;
	}

	/**
	 * @param debutMenstruation the debutMenstruation to set
	 */
	public void setDebutMenstruation(EnumDebutMenstruation debutMenstruation) {
		this.debutMenstruation = debutMenstruation;
	}

	/**
	 * @return the douleurRapports
	 */
	public EnumOuiNon getDouleurRapports() {
		return douleurRapports;
	}

	/**
	 * @param douleurRapports the douleurRapports to set
	 */
	public void setDouleurRapports(EnumOuiNon douleurRapports) {
		this.douleurRapports = douleurRapports;
	}

	/**
	 * @return the aggravation
	 */
	public EnumAggravation getAggravation() {
		return aggravation;
	}

	/**
	 * @param enumAggravation the aggravation to set
	 */
	public void setAggravation(EnumAggravation enumAggravation) {
		this.aggravation = enumAggravation;
	}

	/**
	 * @return the accouchee
	 */
	public EnumOuiNon getAccouchee() {
		return accouchee;
	}

	/**
	 * @param accouchee the accouchee to set
	 */
	public void setAccouchee(EnumOuiNon accouchee) {
		this.accouchee = accouchee;
	}

	/**
	 * @return the antecedentsFamiliaux
	 */
	public EnumOuiNon getAntecedentsFamiliaux() {
		return antecedentsFamiliaux;
	}

	/**
	 * @param antecedentsFamiliaux the antecedentsFamiliaux to set
	 */
	public void setAntecedentsFamiliaux(EnumOuiNon antecedentsFamiliaux) {
		this.antecedentsFamiliaux = antecedentsFamiliaux;
	}

	/**
	 * @return the dureeCycle
	 */
	public EnumDureeCycle getDureeCycle() {
		return dureeCycle;
	}

	/**
	 * @param dureeCycle the dureeCycle to set
	 */
	public void setDureeCycle(EnumDureeCycle dureeCycle) {
		this.dureeCycle = dureeCycle;
	}

	/**
	 * @return the difficulteConception
	 */
	public EnumOuiNon getDifficulteConception() {
		return difficulteConception;
	}

	/**
	 * @param difficulteConception the difficulteConception to set
	 */
	public void setDifficulteConception(EnumOuiNon difficulteConception) {
		this.difficulteConception = difficulteConception;
	}

	/**
	 * @return the bmi
	 */
	public double getBmi() {
		return bmi;
	}

	/**
	 * @param bmi the bmi to set
	 */
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	/**
	 * @return the poids
	 */
	public double getPoids() {
		return poids;
	}

	/**
	 * @param poids the poids to set
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}

	/**
	 * @return the taille
	 */
	public double getTaille() {
		return taille;
	}

	/**
	 * @param taille the taille to set
	 */
	public void setTaille(double taille) {
		this.taille = taille;
	}

	/**
	 * @return the intensiteDouleursAbdominales
	 */
	public int getIntensiteDouleursAbdominales() {
		return intensiteDouleursAbdominales;
	}

	/**
	 * @param intensiteDouleursAbdominales the intensiteDouleursAbdominales to set
	 */
	public void setIntensiteDouleursAbdominales(int intensiteDouleursAbdominales) {
		this.intensiteDouleursAbdominales = intensiteDouleursAbdominales;
	}

	/**
	 * @return the periodeDouleursAbdominales
	 */
	public EnumPeriodeDouleurs getPeriodeDouleursAbdominales() {
		return periodeDouleursAbdominales;
	}

	/**
	 * @param periodeDouleursAbdominales the periodeDouleursAbdominales to set
	 */
	public void setPeriodeDouleursAbdominales(EnumPeriodeDouleurs periodeDouleursAbdominales) {
		this.periodeDouleursAbdominales = periodeDouleursAbdominales;
	}

	/**
	 * @return the dureeRegles
	 */
	public EnumDureeRegles getDureeRegles() {
		return dureeRegles;
	}

	/**
	 * @param dureeRegles the dureeRegles to set
	 */
	public void setDureeRegles(EnumDureeRegles dureeRegles) {
		this.dureeRegles = dureeRegles;
	}

	/**
	 * @return the natureMenstruation
	 */
	public EnumNatureMenstruation getNatureMenstruation() {
		return natureMenstruation;
	}

	/**
	 * @param natureMenstruation the natureMenstruation to set
	 */
	public void setNatureMenstruation(EnumNatureMenstruation natureMenstruation) {
		this.natureMenstruation = natureMenstruation;
	}

	/**
	 * @return the dateDiagnostic
	 */
	public Date getDateDiagnostic() {
		return dateDiagnostic;
	}

	/**
	 * @param dateDiagnostic the dateDiagnostic to set
	 */
	public void setDateDiagnostic(Date dateDiagnostic) {
		this.dateDiagnostic = dateDiagnostic;
	}
	
	  public int getIdUser() {
		return idUser;
	}




	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}

	// Enumérations utilisées dans le bean
    public enum EnumDebutMenstruation {
        AVANT_11_ANS, APRES_11_ANS
    }

    public enum EnumOuiNon {
        OUI, NON
    }

    public enum EnumDureeCycle {
        MOINS_DE_27_JOURS, PLUS_DE_27_JOURS, PAS_SUR
    }

    public enum EnumPeriodeDouleurs {
        LIER_AUX_PERIODES_MENSTRUELLES, LIER_AUX_OVULATIONS, SANS_LIEN_AVEC_LA_PERIODE_MENTRUELLE_OU_LA_PERIODE_OVULATION
    }

    public enum EnumDureeRegles {
        PAS_SUR, MOINS_DE_7_JOURS, _7_JOURS_OU_PLUS
    }

    public enum EnumNatureMenstruation {
        ABONDANTES, MODEREES, LEGERES
    }
    public enum EnumAggravation{
    	Mvt_Intestinaux, Vessie,Orgasme,Miction,Aucune
    }
}