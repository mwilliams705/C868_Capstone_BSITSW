package main.Controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Controller.Util.GeneralController;
import main.DAO.*;
import main.Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Filter;

public class MainController implements Initializable {

    public TabPane mainTabPane;


    //    Customers Table
    public TableView<Customer> customers_table;
    public TableColumn<Customer,Integer> customer_id;
    public TableColumn<Customer,String> customer_name;
    public TableColumn<Customer,String> customer_address;
    public TableColumn<Customer,String> customer_zipcode;
    public TableColumn<Customer,String> customer_phone;
    public TableColumn<Customer,String> customer_division;

//    Appointments This Week Table
    public TableView<Appointment> appt_this_week_table;
    public TableColumn<Appointment,Integer> week_appt_id;
    public TableColumn<Appointment,String> week_title;
    public TableColumn<Appointment,String> week_desc;
    public TableColumn<Appointment,String> week_location;
    public TableColumn<Appointment,Integer> week_contact;
    public TableColumn<Appointment,String> week_type;
    public TableColumn<Appointment,String> week_start;
    public TableColumn<Appointment,String> week_end;
    public TableColumn<Appointment,Integer> week_customer_id;

//    Appointments This Month Table
    public TableView<Appointment> appt_this_month_table;
    public TableColumn<Appointment,Integer> month_appt_id;
    public TableColumn<Appointment,String> month_title;
    public TableColumn<Appointment,String> month_desc;
    public TableColumn<Appointment,String> month_location;
    public TableColumn<Appointment,Integer> month_contact;
    public TableColumn<Appointment,String> month_type;
    public TableColumn<Appointment,String> month_start;
    public TableColumn<Appointment,String> month_end;
    public TableColumn<Appointment,Integer> month_customer_id;

//    All Appointments
    public TableView<Appointment> appt_all_table;
    public TableColumn<Appointment,Integer> all_appt_id;
    public TableColumn<Appointment,String> all_title;
    public TableColumn<Appointment,String> all_desc;
    public TableColumn<Appointment,String> all_location;
    public TableColumn<Appointment,Integer> all_contact;
    public TableColumn<Appointment,String> all_type;
    public TableColumn<Appointment,String> all_start;
    public TableColumn<Appointment,String> all_end;
    public TableColumn<Appointment,Integer> all_customer_id;

    //    PI Case Table
    public TableView<CasePersonalInjury> pi_cases_table;
    public TableColumn<CasePersonalInjury,Integer> pi_case_id;
    public TableColumn<CasePersonalInjury,String> pi_case_title;
    public TableColumn<CasePersonalInjury,String> pi_case_desc;
    public TableColumn<CasePersonalInjury, Date> pi_case_incident_date;
    public TableColumn<CasePersonalInjury, Timestamp> pi_case_intake_date;
    public TableColumn<CasePersonalInjury,String> pi_case_opposing_party;

    //    WC Case Table
    public TableView<CaseWorkersCompensation> wc_cases_table;
    public TableColumn<CaseWorkersCompensation,Integer> wc_case_id;
    public TableColumn<CaseWorkersCompensation,String> wc_case_title;
    public TableColumn<CaseWorkersCompensation,String> wc_case_desc;
    public TableColumn<CaseWorkersCompensation,Date> wc_case_incident_date;
    public TableColumn<CaseWorkersCompensation,Timestamp> wc_case_intake_date;
    public TableColumn<CaseWorkersCompensation,String> wc_case_opposing_company;

//  Contact(ATTORNEY) Table
    public TableView<Contact> contacts_table;
    public TableColumn<Contact,Integer> contact_id;
    public TableColumn<Contact,String> contact_name;
    public TableColumn<Contact,String> contact_email;


    public Label currentUserLbl;

    private static Customer modifyCustomer;
    private static Appointment modifyAppointment;
    private static CaseWorkersCompensation modifyCaseWorkersCompensation;
    private static CasePersonalInjury modifyCasePersonalInjury;

    public Button signOutBtn;

    public TextField customerSearchField;
    public TextField caseSearchField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentUserLbl.setText(LoginController.getGlobalUser().getUserName());
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        buildTables();


        if (!Objects.requireNonNull(AppointmentDAO.isAppointmentInNext15Minutes()).isEmpty()){
            System.out.println("Appointment within the next 15 minutes!");
        }
    }

    public void buildTables(){
        FilteredList<Customer> filteredCustomerList = new FilteredList<>(Objects.requireNonNull(CustomerDAO.getAllCustomersWithDivisionAndCountries()));
        customers_table.setItems(filteredCustomerList);
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customer_name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customer_address.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customer_zipcode.setCellValueFactory(new PropertyValueFactory<>("customerZipcode"));
        customer_phone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customer_division.setCellValueFactory(new PropertyValueFactory<>("customerDivisionText"));




        FilteredList<Appointment> filteredWeeklyAppointmentsList = new FilteredList<>(Objects.requireNonNull(AppointmentDAO.getAllAppointmentsThisWeek()));
        appt_this_week_table.setItems(filteredWeeklyAppointmentsList);
        week_appt_id.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        week_title.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        week_desc.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        week_location.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        week_contact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        week_type.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        week_start.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        week_end.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        week_customer_id.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));


        FilteredList<Appointment> filteredMonthlyAppointmentList = new FilteredList<>(Objects.requireNonNull(AppointmentDAO.getAllAppointmentsThisMonth()));
        appt_this_month_table.setItems(filteredMonthlyAppointmentList);
        month_appt_id.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        month_title.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        month_desc.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        month_location.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        month_contact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        month_type.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        month_start.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        month_end.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        month_customer_id.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));


        FilteredList<Appointment> filteredAllAppointmentList = new FilteredList<>(Objects.requireNonNull(AppointmentDAO.getAllAppointments()));
        appt_all_table.setItems(filteredAllAppointmentList);
        all_appt_id.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        all_title.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        all_desc.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        all_location.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        all_contact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        all_type.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        all_start.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        all_end.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        all_customer_id.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));

        FilteredList<CasePersonalInjury> filteredPICaseList = new FilteredList<>(Objects.requireNonNull(PICaseDAO.getAllCases()));
        pi_cases_table.setItems(filteredPICaseList);
        pi_case_id.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        pi_case_title.setCellValueFactory(new PropertyValueFactory<>("caseTitle"));
        pi_case_desc.setCellValueFactory(new PropertyValueFactory<>("caseDescription"));
        pi_case_incident_date.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
        pi_case_intake_date.setCellValueFactory(new PropertyValueFactory<>("intakeDate"));
        pi_case_opposing_party.setCellValueFactory(new PropertyValueFactory<>("caseDefendantName"));

        FilteredList<CaseWorkersCompensation> filteredWCCaseList = new FilteredList<>(Objects.requireNonNull(WCCaseDAO.getAllCases()));
        wc_cases_table.setItems(filteredWCCaseList);
        wc_case_id.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        wc_case_title.setCellValueFactory(new PropertyValueFactory<>("caseTitle"));
        wc_case_desc.setCellValueFactory(new PropertyValueFactory<>("caseDescription"));
        wc_case_incident_date.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
        wc_case_intake_date.setCellValueFactory(new PropertyValueFactory<>("intakeDate"));
        wc_case_opposing_company.setCellValueFactory(new PropertyValueFactory<>("caseCompanyName"));

        FilteredList<Contact> filteredContactList = new FilteredList<>(Objects.requireNonNull(ContactDAO.getAllContacts()));
        contacts_table.setItems(filteredContactList);
        contact_id.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        contact_name.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contact_email.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));


//    ==================================================================================================================
//    ==================Search==========================================================================================
//    ==================================================================================================================

        //        Customer Search
        customerSearchField.textProperty().addListener((observable,oldVal, newVal) -> {
            filteredCustomerList.setPredicate(customer -> {
                if (newVal==null || newVal.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newVal.toLowerCase(Locale.ROOT);

                if (customer.getCustomerName().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)){
                    return true;
                }
                if (customer.getCustomerPhone().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)){
                    return true;
                }
                if (String.valueOf(customer.getCustomerId()).contains(lowerCaseFilter)){
                    return true;
                }
                else return false;

            });
        });
        SortedList<Customer> sortedData = new SortedList<>(filteredCustomerList);
        sortedData.comparatorProperty().bind(customers_table.comparatorProperty());
        customers_table.setItems(sortedData);

//        Personal Injury Case Search
        caseSearchField.textProperty().addListener((observable,oldVal, newVal) -> {
            filteredPICaseList.setPredicate(casePersonalInjury -> {
                if (newVal==null || newVal.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newVal.toLowerCase(Locale.ROOT);

                if (casePersonalInjury.getCaseTitle().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)){
                    return true;
                }
                if (String.valueOf(casePersonalInjury.getCaseId()).contains(lowerCaseFilter)){
                    return true;
                }

                return false;

            });
        });
        SortedList<CasePersonalInjury> sortedPIData = new SortedList<>(filteredPICaseList);
        sortedPIData.comparatorProperty().bind(pi_cases_table.comparatorProperty());
        pi_cases_table.setItems(sortedPIData);

//        Workers Comp Case Search
        caseSearchField.textProperty().addListener((observable,oldVal, newVal) -> {
            filteredWCCaseList.setPredicate(caseWorkersCompensation -> {
                if (newVal==null || newVal.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newVal.toLowerCase(Locale.ROOT);

                if (caseWorkersCompensation.getCaseTitle().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)){
                    return true;
                }
                if (String.valueOf(caseWorkersCompensation.getCaseId()).contains(lowerCaseFilter)){
                    return true;
                }

                return false;

            });
        });
        SortedList<CaseWorkersCompensation> sortedWCData = new SortedList<>(filteredWCCaseList);
        sortedWCData.comparatorProperty().bind(wc_cases_table.comparatorProperty());
        wc_cases_table.setItems(sortedWCData);

    }


//    ==================================================================================================================
//    ==================Customers=======================================================================================
//    ==================================================================================================================
    public void addCustomer(ActionEvent actionEvent) throws IOException {
        modifyCustomer = null;
//        GeneralController.changePage(actionEvent,"CustomerForm");
        GeneralController.addCloseableTabWithCustomerFormViewAndMoveTo(mainTabPane,"Add Customer", "CustomerForm");

    }

    public void updateCustomer(ActionEvent actionEvent) throws IOException {
        modifyCustomer = customers_table.getSelectionModel().getSelectedItem();
//        GeneralController.changePage(actionEvent,"CustomerForm");
        GeneralController.addCloseableTabWithCustomerFormViewAndMoveTo(mainTabPane,modifyCustomer.getCustomerId()+" | "+modifyCustomer.getCustomerName(), "CustomerForm");

    }

    public void deleteCustomer(ActionEvent actionEvent) {
        modifyCustomer = customers_table.getSelectionModel().getSelectedItem();

        Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Customer","Continue Deleting Customer?:", modifyCustomer.getCustomerId()+" | "+modifyCustomer.getCustomerName());
        Optional<ButtonType> confirm = confirmDelete.showAndWait();
        if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
            CustomerDAO.deleteCustomer(modifyCustomer.getCustomerId());
            customers_table.setItems(CustomerDAO.getAllCustomersWithDivisionAndCountries());

        }

    }



//    ==================================================================================================================
//    ==================Appointments====================================================================================
//    ==================================================================================================================
    public void addAppointment(ActionEvent actionEvent) throws IOException {
        modifyAppointment = null;
        GeneralController.addCloseableTabWithAppointmentFormViewAndMoveTo(mainTabPane,"New Appointment","AppointmentForm");
    }

    public void updateAppointment(ActionEvent actionEvent)throws IOException {
        try {
            modifyAppointment = appt_all_table.getSelectionModel().getSelectedItem();
            GeneralController.addCloseableTabWithAppointmentFormViewAndMoveTo(mainTabPane,"Appointment for " +modifyAppointment.getApptCustomerId(),"AppointmentForm");
        }catch (NullPointerException n1){
            try {
                modifyAppointment = appt_this_month_table.getSelectionModel().getSelectedItem();
                GeneralController.addCloseableTabWithAppointmentFormViewAndMoveTo(mainTabPane,"Appointment for " +modifyAppointment.getApptCustomerId(),"AppointmentForm");
            }catch (NullPointerException n2){
                try {
                    modifyAppointment = appt_this_week_table.getSelectionModel().getSelectedItem();
                    GeneralController.addCloseableTabWithAppointmentFormViewAndMoveTo(mainTabPane,"Appointment for " +modifyAppointment.getApptCustomerId(),"AppointmentForm");
                }catch (NullPointerException n3){
                    n3.printStackTrace();
                }
            }
        }
    }

    public void deleteAppointment(ActionEvent actionEvent) {

        try {
            modifyAppointment = appt_all_table.getSelectionModel().getSelectedItem();
            System.out.println(modifyAppointment.getApptTitle());

            Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Appointment","Continue Deleting Appointment?:", modifyAppointment.getApptTitle()+" | "+modifyAppointment.getApptStart()+" - "+modifyAppointment.getApptEnd());
            Optional<ButtonType> confirm = confirmDelete.showAndWait();
            if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                AppointmentDAO.deleteAppointment(modifyAppointment.getApptId());
                appt_all_table.setItems(AppointmentDAO.getAllAppointments());

                Alert onDelete = GeneralController.alertUser(Alert.AlertType.INFORMATION,"Appointment Deleted", "Deletion Confirmed", modifyAppointment.getApptId() +" | "+modifyAppointment.getApptType());
                onDelete.showAndWait();
            }
        }catch (NullPointerException n1){
            try {
                modifyAppointment = appt_this_month_table.getSelectionModel().getSelectedItem();
                System.out.println(modifyAppointment.getApptTitle());

                Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Appointment","Continue Deleting Appointment?:", modifyAppointment.getApptTitle()+" | "+modifyAppointment.getApptStart()+" - "+modifyAppointment.getApptEnd());
                Optional<ButtonType> confirm = confirmDelete.showAndWait();
                if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                    AppointmentDAO.deleteAppointment(modifyAppointment.getApptId());
                    appt_this_month_table.setItems(AppointmentDAO.getAllAppointmentsThisMonth());
                }
            }catch (NullPointerException n2){
                try {
                    modifyAppointment = appt_this_week_table.getSelectionModel().getSelectedItem();
                    System.out.println(modifyAppointment.getApptTitle());

                    Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Appointment","Continue Deleting Appointment?:", modifyAppointment.getApptTitle()+" | "+modifyAppointment.getApptStart()+" - "+modifyAppointment.getApptEnd());
                    Optional<ButtonType> confirm = confirmDelete.showAndWait();
                    if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                        AppointmentDAO.deleteAppointment(modifyAppointment.getApptId());
                        appt_this_week_table.setItems(AppointmentDAO.getAllAppointmentsThisWeek());
                    }
                }catch (NullPointerException n3){
                    n3.printStackTrace();
                }
            }
        }



    }



//    ==================================================================================================================
//    ==================Cases===========================================================================================
//    ==================================================================================================================
    public void addCase(ActionEvent actionEvent) throws IOException{
        modifyCasePersonalInjury = null;
        modifyCaseWorkersCompensation = null;
        GeneralController.addCloseableTabWithCaseFormViewAndMoveTo(mainTabPane,"New Case", "CaseForm");

    }


    public void updateCase(ActionEvent actionEvent) throws IOException{

        try {
            modifyCaseWorkersCompensation = wc_cases_table.getSelectionModel().getSelectedItem();
            System.out.println(modifyCaseWorkersCompensation.getCaseDescription());
            modifyCasePersonalInjury = null;
            GeneralController.addCloseableTabWithCaseFormViewAndMoveTo(mainTabPane,modifyCaseWorkersCompensation.getCaseTitle(),"CaseForm");
        }catch (NullPointerException n){
            try {
                modifyCasePersonalInjury = pi_cases_table.getSelectionModel().getSelectedItem();
                System.out.println(modifyCasePersonalInjury.getCaseDescription());
                modifyCaseWorkersCompensation = null;
                GeneralController.addCloseableTabWithCaseFormViewAndMoveTo(mainTabPane,modifyCasePersonalInjury.getCaseTitle(),"CaseForm");
            }catch (NullPointerException n1){
                Alert a = GeneralController.alertUser(Alert.AlertType.ERROR,"Error", "No Case Selected.", "Please choose a case from the table to proceed");
                a.showAndWait();
            }
        }
    }

    public void deleteCase(ActionEvent actionEvent) {
        try {
            modifyCaseWorkersCompensation = wc_cases_table.getSelectionModel().getSelectedItem();
            Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Case","Continue Deleting WC Case?", modifyCaseWorkersCompensation.getCaseTitle());
            Optional<ButtonType> confirm = confirmDelete.showAndWait();
            if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                WCCaseDAO.delete(modifyCaseWorkersCompensation.getCaseId());
                wc_cases_table.setItems(WCCaseDAO.getAllCases());

                Alert onDelete = GeneralController.alertUser(Alert.AlertType.INFORMATION,"Case Deleted", "Deletion Confirmed", modifyCaseWorkersCompensation.getCaseTitle());
                onDelete.showAndWait();
            }
        }catch (NullPointerException n){
            try {
                modifyCasePersonalInjury = pi_cases_table.getSelectionModel().getSelectedItem();
                Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Case","Continue Deleting PI Case?", modifyCasePersonalInjury.getCaseTitle());
                Optional<ButtonType> confirm = confirmDelete.showAndWait();
                if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                    PICaseDAO.delete(modifyCasePersonalInjury.getCaseId());
                    pi_cases_table.setItems(PICaseDAO.getAllCases());

                    Alert onDelete = GeneralController.alertUser(Alert.AlertType.INFORMATION,"Case Deleted", "Deletion Confirmed", modifyCasePersonalInjury.getCaseTitle());
                    onDelete.showAndWait();
                }
            }catch (NullPointerException n1){
                Alert onDelete = GeneralController.alertUser(Alert.AlertType.ERROR,"Error", "No Case Selected", "Select a case and try again");
                onDelete.showAndWait();
            }
        }
    }


//    ==================================================================================================================
//    ==================Getters & Setters===============================================================================
//    ==================================================================================================================
    public static Customer getModifyCustomer() {
        return modifyCustomer;
    }

    public static Appointment getModifyAppointment() {
        return modifyAppointment;
    }

    public static CaseWorkersCompensation getModifyCaseWorkersCompensation() {
        return modifyCaseWorkersCompensation;
    }

    public static CasePersonalInjury getModifyCasePersonalInjury() {
        return modifyCasePersonalInjury;
    }

    public void openApptsByTypeReport(ActionEvent actionEvent) throws IOException {
        GeneralController.addCloseableTabWithReportFormViewAndMoveTo(mainTabPane,"Appointments By Type","ReportOne");
    }

    public void openContactScheduleReport(ActionEvent actionEvent) throws IOException {
        GeneralController.addCloseableTabWithReportFormViewAndMoveTo(mainTabPane,"Attorney Schedules","ReportTwo");
    }
    /**
     * Button action to open a new tab in the Main view tab pane with the 'Total Appointments by Type' report.
     * @param actionEvent
     * @throws IOException
     */
    public void OpenTotalApptsByCustomer(ActionEvent actionEvent) throws IOException {
        GeneralController.addCloseableTabWithReportFormViewAndMoveTo(mainTabPane,"Total Appts. By Customer","ReportThree");
    }




    public void signout(ActionEvent actionEvent) throws IOException {
        GeneralController.changePage(actionEvent,"DuckLawLoginForm");

    }


}
