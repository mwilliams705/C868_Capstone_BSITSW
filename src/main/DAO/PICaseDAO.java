package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Controller.LoginController;
import main.Model.CasePersonalInjury;
import main.Util.DBConnector;
import main.Util.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PICaseDAO {

    public static ObservableList<CasePersonalInjury> getAllCases(){
        String getStatement = "select Case_ID, case_title, case_desc, incident_date, intake_date, Customer_Id, Contact_Id, opposing_party from pi_cases;";
        CasePersonalInjury casePersonalInjuryAllResult;
        ObservableList<CasePersonalInjury> allCases = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                casePersonalInjuryAllResult = new CasePersonalInjury(
                        rs.getInt("Case_ID"),
                        rs.getString("case_title"),
                        rs.getString("case_desc"),
                        rs.getDate("incident_date"),
                        rs.getTimestamp("intake_date"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("Contact_ID"),
                        rs.getString("opposing_party")
                );
                allCases.add(casePersonalInjuryAllResult);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allCases;
    }

    public static void add(CasePersonalInjury CasePersonalInjury){

        String getStatement = "insert into pi_cases(case_title, case_desc, incident_date, intake_date, Customer_ID, Contact_ID, opposing_party)\n" +
                "values (?,?,?,NOW(),?,?,?);";

        try{
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,CasePersonalInjury.getCaseTitle());
            ps.setString(2,CasePersonalInjury.getCaseDescription());
            ps.setDate(3, CasePersonalInjury.getIncidentDate());
            ps.setInt(4,CasePersonalInjury.getCaseCustomerId());
            ps.setInt(5,CasePersonalInjury.getCaseContactId());
            ps.setString(6,CasePersonalInjury.getCaseDefendantName());


            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+ " row affected.");
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public static void update(CasePersonalInjury CasePersonalInjury){
        String getStatement = "update pi_cases set CASE_TITLE = ?,\n" +
                "                    CASE_DESC = ?,\n" +
                "                    INCIDENT_DATE = ?,\n" +
                "                    CUSTOMER_ID = ?,\n" +
                "                    CONTACT_ID = ?,\n" +
                "                    OPPOSING_PARTY = ?\n" +
                "where CASE_ID = ?;";


        try{
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);

            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,CasePersonalInjury.getCaseTitle());
            ps.setString(2, CasePersonalInjury.getCaseDescription());
            ps.setDate(3,CasePersonalInjury.getIncidentDate());
            ps.setInt(4,CasePersonalInjury.getCaseCustomerId());
            ps.setInt(5,CasePersonalInjury.getCaseContactId());
            ps.setString(6,CasePersonalInjury.getCaseDefendantName());
            ps.setInt(7, CasePersonalInjury.getCaseId());

            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+ " row(s) affected.");
            }

        }catch (SQLException s){
            s.printStackTrace();
            System.out.println("Could not update Case, check PICaseDAO");
        }



    }

    public static void delete(int id){
        String getStatement = "DELETE FROM pi_cases where Case_id = ?";

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,String.valueOf(id));
            ps.execute();
            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount() + " row(s) deleted.");

            }



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("No Case deleted.");
        }
    }



}
