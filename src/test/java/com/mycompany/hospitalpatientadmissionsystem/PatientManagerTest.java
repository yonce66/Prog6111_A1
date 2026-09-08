/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientManagerTest {

    @Test
    public void testRegisterPatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        boolean result = manager.registerPatient(patient);

        assertTrue(result);
        assertEquals(1, manager.getPatientCount());
    }

    @Test
    public void testSearchPatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P002",
                "Sarah",
                "Adams",
                30,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        Patient result = manager.searchPatient("P002");

        assertNotNull(result);
        assertEquals("Sarah", result.getFirstName());
        assertEquals("Adams", result.getLastName());
    }

    @Test
    public void testUpdatePatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P003",
                "Mike",
                "Brown",
                40,
                "Male",
                "Back Pain",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        boolean result = manager.updatePatient(
                "P003",
                "Michael",
                "Brown",
                41,
                "Male",
                "Back Pain",
                PatientCategory.OUTPATIENT
        );

        assertTrue(result);

        Patient updatedPatient = manager.searchPatient("P003");

        assertEquals("Michael", updatedPatient.getFirstName());
        assertEquals(41, updatedPatient.getAge());
    }

    @Test
    public void testDeletePatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P004",
                "James",
                "Jones",
                50,
                "Male",
                "Diabetes",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        boolean result = manager.deletePatient("P004");

        assertTrue(result);
        assertNull(manager.searchPatient("P004"));
        assertEquals(0, manager.getPatientCount());
    }

    @Test
    public void testDuplicatePatientId() {

        PatientManager manager = new PatientManager();

        Patient firstPatient = new Patient(
                "P005",
                "Alice",
                "Smith",
                28,
                "Female",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient secondPatient = new Patient(
                "P005",
                "Bob",
                "Jones",
                35,
                "Male",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        assertTrue(manager.registerPatient(firstPatient));
        assertFalse(manager.registerPatient(secondPatient));

        assertEquals(1, manager.getPatientCount());
    }

    @Test
    public void testGetPatientsAsArray() {

        PatientManager manager = new PatientManager();

        manager.registerPatient(new Patient(
                "P006",
                "Tom",
                "Zulu",
                20,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        ));

        manager.registerPatient(new Patient(
                "P007",
                "Amy",
                "Adams",
                22,
                "Female",
                "Cold",
                PatientCategory.OUTPATIENT
        ));

        Patient[] patients = manager.getPatientsAsArray();

        assertEquals(2, patients.length);
        assertEquals("P006", patients[0].getPatientId());
        assertEquals("P007", patients[1].getPatientId());
    }

    @Test
    public void testSortBySurname() {

        PatientManager manager = new PatientManager();

        manager.registerPatient(new Patient(
                "P008",
                "John",
                "Zulu",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        ));

        manager.registerPatient(new Patient(
                "P009",
                "Sarah",
                "Adams",
                30,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        ));

        manager.registerPatient(new Patient(
                "P010",
                "Mike",
                "Brown",
                35,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        ));

        Patient[] patients = manager.getPatientsAsArray();

        // Sort the array by surname for testing
        for (int i = 0; i < patients.length - 1; i++) {

            for (int j = 0; j < patients.length - i - 1; j++) {

                if (patients[j].getLastName()
                        .compareToIgnoreCase(patients[j + 1].getLastName()) > 0) {

                    Patient temporary = patients[j];
                    patients[j] = patients[j + 1];
                    patients[j + 1] = temporary;
                }
            }
        }

        assertEquals("Adams", patients[0].getLastName());
        assertEquals("Brown", patients[1].getLastName());
        assertEquals("Zulu", patients[2].getLastName());
    }

    @Test
    public void testSortByPatientId() {

        PatientManager manager = new PatientManager();

        manager.registerPatient(new Patient(
                "P010",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        ));

        manager.registerPatient(new Patient(
                "P001",
                "Sarah",
                "Adams",
                30,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        ));

        manager.registerPatient(new Patient(
                "P005",
                "Mike",
                "Brown",
                35,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        ));

        Patient[] patients = manager.getPatientsAsArray();

        // Sort the array by Patient ID for testing
        for (int i = 0; i < patients.length - 1; i++) {

            for (int j = 0; j < patients.length - i - 1; j++) {

                if (patients[j].getPatientId()
                        .compareToIgnoreCase(patients[j + 1].getPatientId()) > 0) {

                    Patient temporary = patients[j];
                    patients[j] = patients[j + 1];
                    patients[j + 1] = temporary;
                }
            }
        }

        assertEquals("P001", patients[0].getPatientId());
        assertEquals("P005", patients[1].getPatientId());
        assertEquals("P010", patients[2].getPatientId());
    }
    
}
