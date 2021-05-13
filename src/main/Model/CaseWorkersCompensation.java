package main.Model;

import java.sql.Date;
import java.sql.Timestamp;

public class CaseWorkersCompensation extends Case {
    private String caseCompanyName;

    public CaseWorkersCompensation(int caseId, String caseTitle, String caseDescription, Date incidentDate, Timestamp intakeDate, int caseCustomerId, int caseContactId, String caseCompanyName) {
        super(caseId, caseTitle, caseDescription, incidentDate, intakeDate, caseCustomerId, caseContactId);
        this.caseCompanyName = caseCompanyName;
    }

    public CaseWorkersCompensation(String caseTitle, String caseDescription, Date incidentDate, int caseCustomerId, int caseContactId, String caseCompanyName) {
        super(caseTitle, caseDescription, incidentDate,  caseCustomerId, caseContactId);
        this.caseCompanyName = caseCompanyName;
    }

    public String getCaseCompanyName() {
        return caseCompanyName;
    }

    public void setCaseCompanyName(String caseCompanyName) {
        this.caseCompanyName = caseCompanyName;
    }
}

