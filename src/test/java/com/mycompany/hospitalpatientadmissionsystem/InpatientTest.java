/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InpatientTest {

    @Test
    public void testGetWardNumber() {

        Inpatient patient = new Inpatient(
                "P001",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.INPATIENT,
                "W01",
                "B01"
        );

        assertEquals("W01", patient.getWardNumber());
    }

    @Test
    public void testGetBedNumber() {

        Inpatient patient = new Inpatient(
                "P002",
                "Sarah",
                "Adams",
                25,
                "Female",
                "Asthma",
                PatientCategory.INPATIENT,
                "W01",
                "B05"
        );

        assertEquals("B05", patient.getBedNumber());
    }

    @Test
    public void testSetWardNumber() {

        Inpatient patient = new Inpatient(
                "P003",
                "Mike",
                "Brown",
                40,
                "Male",
                "Diabetes",
                PatientCategory.INPATIENT,
                "W01",
                "B10"
        );

        patient.setWardNumber("W02");

        assertEquals("W02", patient.getWardNumber());
    }

    @Test
    public void testSetBedNumber() {

        Inpatient patient = new Inpatient(
                "P004",
                "James",
                "Jones",
                50,
                "Male",
                "Pneumonia",
                PatientCategory.INPATIENT,
                "W01",
                "B10"
        );

        patient.setBedNumber("B15");

        assertEquals("B15", patient.getBedNumber());
    }

    @Test
    public void testInheritedPatientDetails() {

        Inpatient patient = new Inpatient(
                "P005",
                "Alice",
                "Taylor",
                35,
                "Female",
                "Infection",
                PatientCategory.INPATIENT,
                "W01",
                "B08"
        );

        assertEquals("P005", patient.getPatientId());
        assertEquals("Alice", patient.getFirstName());
        assertEquals("Taylor", patient.getLastName());
        assertEquals(35, patient.getAge());
        assertEquals("Female", patient.getGender());
        assertEquals("Infection", patient.getMedicalCondition());
        assertEquals(PatientCategory.INPATIENT, patient.getCategory());
    }

    @Test
    public void testInpatientIsSubclassOfPatient() {

        Inpatient patient = new Inpatient(
                "P006",
                "David",
                "Miller",
                45,
                "Male",
                "Heart Condition",
                PatientCategory.INPATIENT,
                "W01",
                "B12"
        );

        assertTrue(patient instanceof Patient);
    }

    @Test
    public void testDisplayDetailsIsOverridden() {

        Inpatient patient = new Inpatient(
                "P007",
                "Emma",
                "Davis",
                32,
                "Female",
                "Infection",
                PatientCategory.INPATIENT,
                "W01",
                "B20"
        );

        assertNotNull(patient);

        // The Inpatient class overrides displayDetails()
        // and includes ward and bed information.
        patient.displayDetails();
    }
}