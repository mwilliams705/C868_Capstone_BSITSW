package main.Model;

import java.sql.Timestamp;

public abstract class Case {
    private int caseId;
    private String caseTitle;
    private String caseDescription;
    private Timestamp incidentDate;
    private Timestamp intakeDate;
    private int caseCustomerId;

    public Case(int caseId, String caseTitle, String caseDescription, Timestamp incidentDate, Timestamp intakeDate, int caseCustomerId) {
        this.caseId = caseId;
        this.caseTitle = caseTitle;
        this.caseDescription = caseDescription;
        this.incidentDate = incidentDate;
        this.intakeDate = intakeDate;
        this.caseCustomerId = caseCustomerId;
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

    public Timestamp getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Timestamp incidentDate) {
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
}

