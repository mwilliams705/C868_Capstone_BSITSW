package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Model.Report;
import main.Util.DBConnector;
import main.Util.DBQuery;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportTwoController implements Initializable {

    public TableView<Report> JH_Schedule_Table;
    public TableColumn<Report,String> JHcontactName;
    public TableColumn<Report,String> JHappointmentId;
    public TableColumn<Report,String> JHappointmentTitle;
    public TableColumn<Report,String> JHappointmentDesc;
    public TableColumn<Report,String> JHappointmentStart;
    public TableColumn<Report,String> JHappointmentEnd;
    public TableColumn<Report,String> JHcustomerId;

    public TableView<Report> DG_Schedule_Table;
    public TableColumn<Report,String> DGcontactName;
    public TableColumn<Report,String> DGappointmentId;
    public TableColumn<Report,String> DGappointmentTitle;
    public TableColumn<Report,String> DGappointmentDesc;
    public TableColumn<Report,String> DGappointmentStart;
    public TableColumn<Report,String> DGappointmentEnd;
    public TableColumn<Report,String> DGcustomerId;

    public TableView<Report> LL_Schedule_Table;
    public TableColumn<Report,String> LLcontactName;
    public TableColumn<Report,String> LLappointmentId;
    public TableColumn<Report,String> LLappointmentTitle;
    public TableColumn<Report,String> LLappointmentDesc;
    public TableColumn<Report,String> LLappointmentStart;
    public TableColumn<Report,String> LLappointmentEnd;
    public TableColumn<Report,String> LLcustomerId;

    public TableView<Report> JN_Schedule_Table;
    public TableColumn<Report,String> JNcontactName;
    public TableColumn<Report,String> JNappointmentId;
    public TableColumn<Report,String> JNappointmentTitle;
    public TableColumn<Report,String> JNappointmentDesc;
    public TableColumn<Report,String> JNappointmentStart;
    public TableColumn<Report,String> JNappointmentEnd;
    public TableColumn<Report,String> JNcustomerId;

    public TableView<Report> AC_Schedule_Table;
    public TableColumn<Report,String> ACcontactName;
    public TableColumn<Report,String> ACappointmentId;
    public TableColumn<Report,String> ACappointmentTitle;
    public TableColumn<Report,String> ACappointmentDesc;
    public TableColumn<Report,String> ACappointmentStart;
    public TableColumn<Report,String> ACappointmentEnd;
    public TableColumn<Report,String> ACcustomerId;

    public TableView<Report> MW_Schedule_Table;
    public TableColumn<Report,String> MWcontactName;
    public TableColumn<Report,String> MWappointmentId;
    public TableColumn<Report,String> MWappointmentTitle;
    public TableColumn<Report,String> MWappointmentDesc;
    public TableColumn<Report,String> MWappointmentStart;
    public TableColumn<Report,String> MWappointmentEnd;
    public TableColumn<Report,String> MWcustomerId;

    public TableView<Report> LS_Schedule_Table;
    public TableColumn<Report,String> LScontactName;
    public TableColumn<Report,String> LSappointmentId;
    public TableColumn<Report,String> LSappointmentTitle;
    public TableColumn<Report,String> LSappointmentDesc;
    public TableColumn<Report,String> LSappointmentStart;
    public TableColumn<Report,String> LSappointmentEnd;
    public TableColumn<Report,String> LScustomerId;

    public TableView<Report> DF_Schedule_Table;
    public TableColumn<Report,String> DFcontactName;
    public TableColumn<Report,String> DFappointmentId;
    public TableColumn<Report,String> DFappointmentTitle;
    public TableColumn<Report,String> DFappointmentDesc;
    public TableColumn<Report,String> DFappointmentStart;
    public TableColumn<Report,String> DFappointmentEnd;
    public TableColumn<Report,String> DFcustomerId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildJHScheduleReportTable();
        buildDGScheduleReportTable();
        buildLLScheduleReportTable();
        buildJNScheduleReportTable();
        buildACScheduleReportTable();
        buildMWScheduleReportTable();
        buildLSScheduleReportTable();
        buildDFScheduleReportTable();
    }

    public void buildJHScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 1 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        JH_Schedule_Table.setItems(reportFilteredList);
        JHcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        JHappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        JHappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        JHappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        JHappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        JHappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        JHcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildDGScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 2 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        DG_Schedule_Table.setItems(reportFilteredList);
        DGcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        DGappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        DGappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        DGappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        DGappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        DGappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        DGcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildLLScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 3 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        LL_Schedule_Table.setItems(reportFilteredList);
        LLcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        LLappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        LLappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        LLappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        LLappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        LLappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        LLcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildJNScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 4 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        JN_Schedule_Table.setItems(reportFilteredList);
        JNcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        JNappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        JNappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        JNappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        JNappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        JNappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        JNcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildACScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 5 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        AC_Schedule_Table.setItems(reportFilteredList);
        ACcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        ACappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        ACappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        ACappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        ACappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        ACappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        ACcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildMWScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 6 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        MW_Schedule_Table.setItems(reportFilteredList);
        MWcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        MWappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        MWappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        MWappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        MWappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        MWappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        MWcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildLSScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 7 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        LS_Schedule_Table.setItems(reportFilteredList);
        LScontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        LSappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        LSappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        LSappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        LSappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        LSappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        LScustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }
    public void buildDFScheduleReportTable(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 8 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnector.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        DF_Schedule_Table.setItems(reportFilteredList);
        DFcontactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        DFappointmentId.setCellValueFactory(new PropertyValueFactory<>("var2"));
        DFappointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        DFappointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        DFappointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        DFappointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        DFcustomerId.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }


}
