package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Controller.Util.GeneralController;
import main.DAO.ContactDAO;
import main.DAO.CustomerDAO;
import main.DAO.PICaseDAO;
import main.DAO.WCCaseDAO;
import main.Exception.ValidationException;
import main.Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class CaseFormController implements Initializable {
    public Label headerLbl;
    public TextField id_textfield;
    public TextField title_textfield;
    public TextArea desc_textarea;
    public ChoiceBox<Contact> contact_choicebox;
    public RadioButton pi_radio;
    public RadioButton wc_radio;
    public DatePicker incident_datepicker;
    public Label op_or_co_label;
    public TextField op_or_co_textfield;
    public ChoiceBox<Customer> customer_choicebox;

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();

    private static CasePersonalInjury PICaseToModify;
    private static CaseWorkersCompensation WCCaseToModify;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactList.addAll(Objects.requireNonNull(ContactDAO.getAllContacts()));
        customerList.addAll(Objects.requireNonNull(CustomerDAO.getAllCustomers()));

        contact_choicebox.setItems(contactList);
        customer_choicebox.setItems(customerList);





        if (MainController.getModifyCasePersonalInjury() != null){
//            System.out.println(PICaseToModify.getCaseDescription());
            PICaseToModify = MainController.getModifyCasePersonalInjury();
            WCCaseToModify = null;
            headerLbl.setText("Update Case");
            id_textfield.setText(String.valueOf(PICaseToModify.getCaseId()));
            title_textfield.setText(PICaseToModify.getCaseTitle());
            desc_textarea.setText(PICaseToModify.getCaseDescription());
            contact_choicebox.setValue(getContactById(PICaseToModify.getCaseContactId()));
            pi_radio.setSelected(true);
            op_or_co_label.setText("Opposing Party");
            op_or_co_textfield.setText(PICaseToModify.getCaseDefendantName());
            incident_datepicker.setValue(PICaseToModify.getIncidentDate().toLocalDate());
            customer_choicebox.setValue(getCustomerById(PICaseToModify.getCaseCustomerId()));

            if (wc_radio.isSelected()){
                op_or_co_label.setText("Opposing Company");
            }
            if (pi_radio.isSelected()){
                op_or_co_label.setText("Opposing Party");
            }

        }
        if (MainController.getModifyCaseWorkersCompensation() != null){
//            System.out.println(WCCaseToModify.getCaseDescription());
            WCCaseToModify = MainController.getModifyCaseWorkersCompensation();
            PICaseToModify = null;
            headerLbl.setText("Update Case");
            id_textfield.setText(String.valueOf(WCCaseToModify.getCaseId()));
            title_textfield.setText(WCCaseToModify.getCaseTitle());
            desc_textarea.setText(WCCaseToModify.getCaseDescription());
            contact_choicebox.setValue(getContactById(WCCaseToModify.getCaseContactId()));
            wc_radio.setSelected(true);
            op_or_co_label.setText("Opposing Company");
            op_or_co_textfield.setText(WCCaseToModify.getCaseCompanyName());
            incident_datepicker.setValue(WCCaseToModify.getIncidentDate().toLocalDate());
            customer_choicebox.setValue(getCustomerById(WCCaseToModify.getCaseCustomerId()));

            if (wc_radio.isSelected()){
                op_or_co_label.setText("Opposing Company");
            }
            if (pi_radio.isSelected()){
                op_or_co_label.setText("Opposing Party");
            }

        }
        if(MainController.getModifyCaseWorkersCompensation() == null && MainController.getModifyCasePersonalInjury() == null){
            headerLbl.setText("Add Case");
            if (wc_radio.isSelected()){
                op_or_co_label.setText("Opposing Company");
            }
            if (pi_radio.isSelected()){
                op_or_co_label.setText("Opposing Party");
            }
        }

    }

    public void save(ActionEvent actionEvent) throws IOException {

        if (PICaseToModify != null){


            try {
                if (isFormComplete()){
                    if (wc_radio.isSelected()){
                        if (op_or_co_textfield.getText().equals("")){
                            op_or_co_textfield.setText("Unknown Co.");
                        }
                        CaseWorkersCompensation caseWorkersCompensation = new CaseWorkersCompensation(
                                customer_choicebox.getValue().getCustomerName() + " vs " + op_or_co_textfield.getText(),
                                desc_textarea.getText(),
                                Date.valueOf(incident_datepicker.getValue()),
                                customer_choicebox.getValue().getCustomerId(),
                                contact_choicebox.getValue().getContactId(),
                                op_or_co_textfield.getText()
                        );
                        System.out.println(caseWorkersCompensation.getCaseDescription());
                        if (caseWorkersCompensation.isValid()){
                            PICaseDAO.delete(PICaseToModify.getCaseId());
                            WCCaseDAO.add(caseWorkersCompensation);
                            GeneralController.changePage(actionEvent,"Main");

                        }



                    }
                    if (pi_radio.isSelected()){
                        if (op_or_co_textfield.getText().equals("")){
                            op_or_co_textfield.setText("John Doe");
                        }
                        System.out.println(desc_textarea.getText());

                        PICaseToModify.setCaseTitle(customer_choicebox.getValue().getCustomerName()+ " vs " + op_or_co_textfield.getText());
                        PICaseToModify.setCaseDescription(desc_textarea.getText());
                        PICaseToModify.setIncidentDate(Date.valueOf(incident_datepicker.getValue()));
                        PICaseToModify.setCaseCustomerId(customer_choicebox.getValue().getCustomerId());
                        PICaseToModify.setCaseContactId(contact_choicebox.getValue().getContactId());
                        PICaseToModify.setCaseDefendantName(op_or_co_textfield.getText());

                        if (PICaseToModify.isValid()){
                            System.out.println(PICaseToModify.getCaseDescription());
                            PICaseDAO.update(PICaseToModify);
                            GeneralController.changePage(actionEvent,"Main");

                        }

                    }
                }

            }catch (NullPointerException | ValidationException n){
                n.printStackTrace();
                Alert alert = GeneralController.alertUser(Alert.AlertType.WARNING,"Empty Form Field","There is an error in the form:",n.getMessage());
                alert.showAndWait();
            }





        }
        if (WCCaseToModify != null){


            try {
                if (isFormComplete()){
                    if (wc_radio.isSelected()){
                        if (op_or_co_textfield.getText().equals("")){
                            op_or_co_textfield.setText("Unknown Co.");
                        }


                        WCCaseToModify.setCaseTitle(customer_choicebox.getValue().getCustomerName()+ " vs " + op_or_co_textfield.getText());
                        WCCaseToModify.setCaseDescription(desc_textarea.getText());
                        WCCaseToModify.setIncidentDate(Date.valueOf(incident_datepicker.getValue()));
                        WCCaseToModify.setCaseCustomerId(customer_choicebox.getValue().getCustomerId());
                        WCCaseToModify.setCaseContactId(contact_choicebox.getValue().getContactId());
                        WCCaseToModify.setCaseCompanyName(op_or_co_textfield.getText());

                        if (WCCaseToModify.isValid()){
                            WCCaseDAO.update(WCCaseToModify);
                            GeneralController.changePage(actionEvent,"Main");
                        }



                    }
                    if (pi_radio.isSelected()){
                        if (op_or_co_textfield.getText().equals("")){
                            op_or_co_textfield.setText("John Doe");
                        }

                        CasePersonalInjury casePersonalInjury = new CasePersonalInjury(
                                customer_choicebox.getValue().getCustomerName() + " vs " + op_or_co_textfield.getText(),
                                desc_textarea.getText(),
                                Date.valueOf(incident_datepicker.getValue()),
                                customer_choicebox.getValue().getCustomerId(),
                                contact_choicebox.getValue().getContactId(),
                                op_or_co_textfield.getText()
                        );

                        if (casePersonalInjury.isValid()){
                            WCCaseDAO.delete(WCCaseToModify.getCaseId());
                            PICaseDAO.add(casePersonalInjury);
                            GeneralController.changePage(actionEvent,"Main");

                        }

                    }
                }

            }catch (NullPointerException | ValidationException n){
                n.printStackTrace();
                Alert alert = GeneralController.alertUser(Alert.AlertType.WARNING,"Empty Form Field","There is an error in the form:",n.getMessage());
                alert.showAndWait();
            }
        }
        if (PICaseToModify == null && WCCaseToModify == null){


            try {
                if (isFormComplete()){
                    if (wc_radio.isSelected()){
                        if (op_or_co_textfield.getText().equals("")){
                            op_or_co_textfield.setText("Unknown Co.");
                        }

                        CaseWorkersCompensation caseWorkersCompensation = new CaseWorkersCompensation(
                                customer_choicebox.getValue().getCustomerName() + " vs " + op_or_co_textfield.getText(),
                                desc_textarea.getText(),
                                Date.valueOf(incident_datepicker.getValue()),
                                customer_choicebox.getValue().getCustomerId(),
                                contact_choicebox.getValue().getContactId(),
                                op_or_co_textfield.getText()
                        );

                        if (caseWorkersCompensation.isValid()){
                            WCCaseDAO.add(caseWorkersCompensation);
                            GeneralController.changePage(actionEvent,"Main");
                        }



                    }
                    if (pi_radio.isSelected()){
                        if (op_or_co_textfield.getText().equals("")){
                            op_or_co_textfield.setText("John Doe");
                        }
                        System.out.println(desc_textarea.getText());
                        CasePersonalInjury casePersonalInjury = new CasePersonalInjury(
                                customer_choicebox.getValue().getCustomerName() + " vs " + op_or_co_textfield.getText(),
                                desc_textarea.getText(),
                                Date.valueOf(incident_datepicker.getValue()),
                                customer_choicebox.getValue().getCustomerId(),
                                contact_choicebox.getValue().getContactId(),
                                op_or_co_textfield.getText()
                        );
                        System.out.println(casePersonalInjury.getCaseDescription());
                        if (casePersonalInjury.isValid()){
                            PICaseDAO.add(casePersonalInjury);
                            GeneralController.changePage(actionEvent,"Main");

                        }

                    }
                }

            }catch (NullPointerException | ValidationException n){
                n.printStackTrace();
                Alert alert = GeneralController.alertUser(Alert.AlertType.WARNING,"Empty Form Field","There is an error in the form:",n.getMessage());
                alert.showAndWait();
            }
        }



    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        GeneralController.changePage(actionEvent,"Main");
    }

    private Contact getContactById(int id){
        Contact con = null;

        for (Contact c: contactList
        ) {
            if (c.getContactId() != id){
                continue;
            }
            else {
                con = c;
                break;
            }
        }
        return con;
    }

    private Customer getCustomerById(int id){
        Customer cust = null;

        for (Customer c: customerList
        ) {
            if (c.getCustomerId() != id){
                continue;
            }
            else {
                cust = c;
            }
        }
        return cust;
    }

    public boolean isFormComplete() throws NullPointerException{

        if (contact_choicebox.getValue().toString().equals("")){
            throw new NullPointerException("Contact field cannot be empty");
        }
        if (incident_datepicker.getValue().toString().equals("")){
            throw new NullPointerException("Incident date field cannot be empty");
        }
        if (customer_choicebox.getValue().toString().equals("")){
            throw new NullPointerException("Customer field cannot be empty");
        }
        if (contact_choicebox.getValue().toString().equals("")){
            throw new NullPointerException("Contact field cannot be empty");
        }


        return true;
    }

    public void wc_selected(ActionEvent actionEvent) {
        op_or_co_label.setText("Opposing Company");
    }

    public void pi_selected(ActionEvent actionEvent) {
        op_or_co_label.setText("Opposing Party");
    }
}
