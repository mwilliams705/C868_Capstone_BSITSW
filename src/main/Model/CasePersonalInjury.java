package main.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class CasePersonalInjury extends Case{
    private String caseDefendantName;

    public CasePersonalInjury(int caseId, String caseTitle, String caseDescription, Date incidentDate, Timestamp intakeDate, int caseCustomerId, int caseContactId, String caseDefendantName) {
        super(caseId, caseTitle, caseDescription, incidentDate, intakeDate, caseCustomerId, caseContactId);
        this.caseDefendantName = caseDefendantName;
    }

    public CasePersonalInjury(String caseTitle, String caseDescription, Date incidentDate, int caseCustomerId, int caseContactId, String caseDefendantName) {
        super(caseTitle, caseDescription, incidentDate, caseCustomerId, caseContactId);
        this.caseDefendantName = caseDefendantName;
    }

    public String getCaseDefendantName() {
        return caseDefendantName;
    }

    public void setCaseDefendantName(String caseDefendantName) {
        this.caseDefendantName = caseDefendantName;
    }
}
