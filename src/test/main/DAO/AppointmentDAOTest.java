package main.DAO;

import main.Model.Appointment;
import main.Util.DBConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDAOTest {

    @BeforeEach
    void setUp() {
        DBConnector.startConnection();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAppointment() {
        Appointment appointment = AppointmentDAO.getAppointment(2);
        assertNotNull(appointment);
    }

    @Test
    void canGetAllAppointments() {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>(Objects.requireNonNull(AppointmentDAO.getAllAppointments()));
        assertNotNull(appointmentArrayList);
    }

    @Test
    void getAllAppointmentsThisWeek() {
    }

    @Test
    void getAllAppointmentsThisMonth() {
    }

    @Test
    void isAppointmentInNext15Minutes() {
    }

    @Test
    void addAppointment() {
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void deleteAppointment() {
    }

    @Test
    void getOverlappingAppts() {
    }
}