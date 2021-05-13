package main.DAO;

import main.Model.CaseWorkersCompensation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WCCaseDAOTest {

    CaseWorkersCompensation caseWorkersCompensation = new CaseWorkersCompensation(
            "Michael Williams vs Unknown Co.",
            "broken foot from pallet jack",
            Date.valueOf(LocalDate.now()),
            1,
            1,
            "Unknown Co."

    );

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCases() {

    }

    @Test
    void add() {
//        WCCaseDAO.add(caseWorkersCompensation);

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}