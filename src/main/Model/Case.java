package main.Model;

import main.Exception.ValidationException;

import java.sql.Date;
import java.sql.Timestamp;

public abstract class Case {
    private int caseId;
    private String caseTitle;
    private String caseDescription;
    private Date incidentDate;
    private Timestamp intakeDate;
    private int caseCustomerId;
    private int caseContactId;

    public Case(){

    }

    public Case(int caseId, String caseTitle, String caseDescription, Date incidentDate, Timestamp intakeDate, int caseCustomerId, int caseContactId) {
        this.caseId = caseId;
        this.caseTitle = caseTitle;
        this.caseDescription = caseDescription;
        this.incidentDate = incidentDate;
        this.intakeDate = intakeDate;
        this.caseCustomerId = caseCustomerId;
        this.caseContactId = caseContactId;
    }

    public Case(String caseTitle, String caseDescription, Date incidentDate, int caseCustomerId, int caseContactId) {
        this.caseTitle = caseTitle;
        this.caseDescription = caseDescription;
        this.incidentDate = incidentDate;
        this.caseCustomerId = caseCustomerId;
        this.caseContactId = caseContactId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Timestamp getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(Timestamp intakeDate) {
        this.intakeDate = intakeDate;
    }

    public int getCaseCustomerId() {
        return caseCustomerId;
    }

    public void setCaseCustomerId(int caseCustomerId) {
        this.caseCustomerId = caseCustomerId;
    }

    public int getCaseContactId() {
        return caseContactId;
    }

    public void setCaseContactId(int caseContactId) {
        this.caseContactId = caseContactId;
    }

    public boolean isValid() throws ValidationException {
        if (getCaseDescription().length() > 500){
            throw new ValidationException("Case description can be no more than 500 characters in length");
        }
        return true;
    }
}

