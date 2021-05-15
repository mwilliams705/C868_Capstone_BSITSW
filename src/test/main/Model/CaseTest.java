package main.Model;

import main.Exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {


        CaseWorkersCompensation caseWorkersCompensation_underTest_forDescLength = new CaseWorkersCompensation(
                "test vs Unknown Co.",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
                        "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                        "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                        "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, " +
                        "venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. " +
                        "Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu,",
                Date.valueOf(LocalDate.now()),
                2,
                2,
                "Unknown Co."
        );
        CaseWorkersCompensation caseWorkersCompensation_underTest_forIncidentDate = new CaseWorkersCompensation(
                "test vs Unknown Co.",
                "test",
                Date.valueOf(LocalDate.now().plusDays(5)),
                2,
                2,
                "Unknown Co."
        );

        CasePersonalInjury casePersonalInjury_underTest_forDescLength = new CasePersonalInjury(
                "test vs John Doe",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
                        "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                        "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                        "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, " +
                        "venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. " +
                        "Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu,",
                Date.valueOf(LocalDate.now().plusDays(5)),
                2,
                2,
                "John Doe"
        );
        CasePersonalInjury casePersonalInjury_underTest_forIncidentDate = new CasePersonalInjury(
                "test vs John Doe",
                "test",
                Date.valueOf(LocalDate.now().plusDays(5)),
                2,
                2,
                "John Doe"
        );


    @Test
    void canValidatePI() {
        assertThrows(ValidationException.class, ()-> casePersonalInjury_underTest_forIncidentDate.isValid(), "PI Exception Thrown As Intended For Incident Date Validation");
        assertThrows(ValidationException.class, ()-> casePersonalInjury_underTest_forDescLength.isValid(),"PI Exception Thrown As Intended For Description Length Validation");

    }

    @Test
    void canValidateWC() {
        assertThrows(ValidationException.class, ()-> caseWorkersCompensation_underTest_forDescLength.isValid(),"WC Exception Thrown As Intended For Incident Date Validation");
        assertThrows(ValidationException.class, ()-> caseWorkersCompensation_underTest_forIncidentDate.isValid(),"WC Exception Thrown As Intended For Description Length Validation");
    }
}