package main.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class CasePersonalInjury extends Case{
    private String caseDefendantName;

    public CasePersonalInjury(int caseId, String caseTitle, String caseDescription, Timestamp incidentDate, Timestamp intakeDate, int caseCustomerId, String caseDefendantName) {
        super(caseId, caseTitle, caseDescription, incidentDate, intakeDate, caseCustomerId);
        this.caseDefendantName = caseDefendantName;
    }

    public String getCaseDefendantName() {
        return caseDefendantName;
    }

    public void setCaseDefendantName(String caseDefendantName) {
        this.caseDefendantName = caseDefendantName;
    }
}
