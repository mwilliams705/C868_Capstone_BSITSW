package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Controller.LoginController;
import main.Model.CaseWorkersCompensation;
import main.Util.DBConnector;
import main.Util.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WCCaseDAO {

    public static ObservableList<CaseWorkersCompensation> getAllCases(){
        String getStatement = "select Case_ID, case_title, case_desc, incident_date, intake_date, Customer_Id, Contact_Id, opposing_company from wc_cases;";
        CaseWorkersCompensation CaseWorkersCompensationAllResult;
        ObservableList<CaseWorkersCompensation> allCases = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                CaseWorkersCompensationAllResult = new CaseWorkersCompensation(
                        rs.getInt("Case_ID"),
                        rs.getString("case_title"),
                        rs.getString("case_desc"),
                        rs.getDate("incident_date"),
                        rs.getTimestamp("intake_date"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("Contact_ID"),
                        rs.getString("opposing_company")
                );
                allCases.add(CaseWorkersCompensationAllResult);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allCases;
    }

    public static void add(CaseWorkersCompensation CaseWorkersCompensation){

        String getStatement = "insert into wc_cases(case_title, case_desc, incident_date, intake_date, Customer_ID, Contact_ID, opposing_company)\n" +
                "values (?,?,?,NOW(),?,?,?);";

        try{
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,CaseWorkersCompensation.getCaseTitle());
            ps.setString(2,CaseWorkersCompensation.getCaseDescription());
            ps.setDate(3, CaseWorkersCompensation.getIncidentDate());
            ps.setInt(4,CaseWorkersCompensation.getCaseCustomerId());
            ps.setInt(5,CaseWorkersCompensation.getCaseContactId());
            ps.setString(6,CaseWorkersCompensation.getCaseCompanyName());

            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+ " row affected.");
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public static void update(CaseWorkersCompensation CaseWorkersCompensation){
        String getStatement = "update wc_cases set CASE_TITLE = ?,\n" +
                "                    CASE_DESC = ?,\n" +
                "                    INCIDENT_DATE = ?,\n" +
                "                    CUSTOMER_ID = ?,\n" +
                "                    CONTACT_ID = ?,\n" +
                "                    OPPOSING_COMPANY = ?\n" +
                "where CASE_ID = ?;";


        try{
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);

            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,CaseWorkersCompensation.getCaseTitle());
            ps.setString(2, CaseWorkersCompensation.getCaseDescription());
            ps.setDate(3,CaseWorkersCompensation.getIncidentDate());
            ps.setInt(4,CaseWorkersCompensation.getCaseCustomerId());
            ps.setInt(5,CaseWorkersCompensation.getCaseContactId());
            ps.setString(6,CaseWorkersCompensation.getCaseCompanyName());
            ps.setInt(7, CaseWorkersCompensation.getCaseId());

            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+ " row(s) affected.");
            }

        }catch (SQLException s){
            s.printStackTrace();
            System.out.println("Could not update Case, check WCCaseDAO");
        }



    }

    public static void delete(int id){
        String getStatement = "DELETE FROM wc_cases where Case_id = ?";

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
