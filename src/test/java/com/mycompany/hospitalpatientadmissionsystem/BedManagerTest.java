/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BedManagerTest {

    @Test
    public void testAllocateBed() {

        BedManager manager = new BedManager();

        Inpatient patient = new Inpatient(
                "P001",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        boolean result = manager.allocateBed("B01", patient);

        assertTrue(result);
        assertEquals(1, manager.getOccupiedBedCount());
        assertEquals("B01", patient.getBedNumber());
    }

    @Test
    public void testReleaseBed() {

        BedManager manager = new BedManager();

        Inpatient patient = new Inpatient(
                "P002",
                "Sarah",
                "Adams",
                25,
                "Female",
                "Asthma",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        manager.allocateBed("B02", patient);

        boolean result = manager.releaseBed("B02");

        assertTrue(result);
        assertEquals(0, manager.getOccupiedBedCount());
        assertTrue(manager.isBedAvailable("B02"));
        assertEquals("", patient.getBedNumber());
    }

    @Test
    public void testIsBedAvailable() {

        BedManager manager = new BedManager();

        assertTrue(manager.isBedAvailable("B01"));

        Inpatient patient = new Inpatient(
                "P003",
                "Mike",
                "Brown",
                40,
                "Male",
                "Cold",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        manager.allocateBed("B01", patient);

        assertFalse(manager.isBedAvailable("B01"));
    }

    @Test
    public void testOccupiedBedCannotBeAllocatedAgain() {

        BedManager manager = new BedManager();

        Inpatient firstPatient = new Inpatient(
                "P004",
                "James",
                "Jones",
                50,
                "Male",
                "Diabetes",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        Inpatient secondPatient = new Inpatient(
                "P005",
                "Alice",
                "Taylor",
                35,
                "Female",
                "Pneumonia",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        assertTrue(manager.allocateBed("B03", firstPatient));

        boolean result = manager.allocateBed("B03", secondPatient);

        assertFalse(result);
        assertEquals(1, manager.getOccupiedBedCount());
    }

    @Test
    public void testInvalidBedNumber() {

        BedManager manager = new BedManager();

        Inpatient patient = new Inpatient(
                "P006",
                "Tom",
                "Williams",
                28,
                "Male",
                "Flu",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        boolean result = manager.allocateBed("B21", patient);

        assertFalse(result);
        assertEquals(0, manager.getOccupiedBedCount());
    }

    @Test
    public void testInitialBedCounts() {

        BedManager manager = new BedManager();

        assertEquals(20, manager.getAvailableBedCount());
        assertEquals(0, manager.getOccupiedBedCount());
    }

    @Test
    public void testOneBedPerInpatient() {

        BedManager manager = new BedManager();

        Inpatient patient = new Inpatient(
                "P007",
                "Emma",
                "Davis",
                32,
                "Female",
                "Infection",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        assertTrue(manager.allocateBed("B04", patient));

        /*
         * The patient already has a bed.
         * The BedManager should not allow the same inpatient
         * to be allocated to another bed.
         */
        boolean result = manager.allocateBed("B05", patient);

        assertFalse(result);
        assertEquals(1, manager.getOccupiedBedCount());
    }

    @Test
    public void testFullWard() {

        BedManager manager = new BedManager();

        for (int i = 1; i <= 20; i++) {

            String bedNumber = String.format("B%02d", i);

            Inpatient patient = new Inpatient(
                    "P" + i,
                    "Patient" + i,
                    "Test",
                    30,
                    "Male",
                    "Condition",
                    PatientCategory.INPATIENT,
                    "W01",
                    ""
            );

            assertTrue(manager.allocateBed(bedNumber, patient));
        }

        assertEquals(20, manager.getOccupiedBedCount());
        assertEquals(0, manager.getAvailableBedCount());

        Inpatient extraPatient = new Inpatient(
                "P021",
                "Extra",
                "Patient",
                30,
                "Female",
                "Condition",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        boolean result = manager.allocateBed("B01", extraPatient);

        assertFalse(result);
        assertEquals(20, manager.getOccupiedBedCount());
    }

    @Test
    public void testDisplayBedLayout() {

        BedManager manager = new BedManager();

        Inpatient patient = new Inpatient(
                "P008",
                "David",
                "Miller",
                45,
                "Male",
                "Heart Condition",
                PatientCategory.INPATIENT,
                "W01",
                ""
        );

        manager.allocateBed("B10", patient);

        manager.displayBedLayout();

        assertEquals(19, manager.getAvailableBedCount());
        assertEquals(1, manager.getOccupiedBedCount());
    }

    @Test
    public void testReleaseEmptyBed() {

        BedManager manager = new BedManager();

        boolean result = manager.releaseBed("B15");

        assertFalse(result);
        assertEquals(20, manager.getAvailableBedCount());
    }

    @Test
    public void testInvalidBedIsNotAvailable() {

        BedManager manager = new BedManager();

        assertFalse(manager.isBedAvailable("B21"));
        assertFalse(manager.isBedAvailable("B00"));
    }
}