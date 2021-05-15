package main.Model;

import main.Exception.BusinessHoursException;
import main.Exception.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    Appointment inTestForInvertedDate = new Appointment(
            "in test",
            "in test",
            "at test",
            1,
            "test",
            Timestamp.from(Instant.now().plusSeconds(3000)),
            Timestamp.from(Instant.now().minusSeconds(3000)),
            1
    );

    Appointment inTestForApptInPast = new Appointment(
            "in test",
            "in test",
            "at test",
            1,
            "test",
            Timestamp.from(Instant.now()),
            Timestamp.from(Instant.now()),
            1
    );


    @Test
    void canValidateAppointmentTime_NotInPast() {


        LocalDate l = inTestForApptInPast.getApptStart().toLocalDateTime().toLocalDate();
        inTestForApptInPast.setApptStart(Timestamp.valueOf(LocalDateTime.of(l.minusDays(2), LocalTime.now())));
        inTestForApptInPast.setApptEnd(Timestamp.valueOf(LocalDateTime.of(l.minusDays(2), LocalTime.now().plusMinutes(30))));

        assertThrows(BusinessHoursException.class,()-> inTestForApptInPast.isValidTime());


    }

    @Test
    void canValidateAppointmentTime_NotStartBeforeEnd() {


        Exception exception = assertThrows(ValidationException.class, ()->inTestForInvertedDate.isValid());
        assertEquals("The appointment start time cannot be before the end time",exception.getMessage());
    }


}