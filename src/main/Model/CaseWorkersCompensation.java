package main.Model;

import java.sql.Timestamp;

public class CaseWorkersCompensation extends Case {
    private String caseCompanyName;

    public CaseWorkersCompensation(int caseId, String caseTitle, String caseDescription, Timestamp incidentDate, Timestamp intakeDate, int caseCustomerId, String caseCompanyName) {
        super(caseId, caseTitle, caseDescription, incidentDate, intakeDate, caseCustomerId);
        this.caseCompanyName = caseCompanyName;
    }

    public String getCaseCompanyName() {
        return caseCompanyName;
    }

    public void setCaseCompanyName(String caseCompanyName) {
        this.caseCompanyName = caseCompanyName;
    }
}

