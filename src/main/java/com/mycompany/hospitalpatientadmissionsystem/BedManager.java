/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author emeris
 */
public class BedManager {
  private Inpatient[][] beds;

    public BedManager() {
        beds = new Inpatient[4][5];
    }

    public boolean allocateBed(String bedNumber, Inpatient patient) {

        int bedIndex = getBedIndex(bedNumber);

    if (bedIndex == -1) {
        return false;
    }

    if (patient == null || !patient.getBedNumber().isEmpty()) {
        return false;
    }

    int row = bedIndex / 5;
    int column = bedIndex % 5;

    if (beds[row][column] != null) {
        return false;
    }

    beds[row][column] = patient;

    patient.setBedNumber(bedNumber);

    return true;
    }

    public boolean releaseBed(String bedNumber) {

        int bedIndex = getBedIndex(bedNumber);

        if (bedIndex == -1) {
            return false;
        }

        int row = bedIndex / 5;
        int column = bedIndex % 5;

        if (beds[row][column] == null) {
            return false;
        }

        beds[row][column].setBedNumber("");
        beds[row][column] = null;

        return true;
    }

    public boolean isBedAvailable(String bedNumber) {

        int bedIndex = getBedIndex(bedNumber);

        if (bedIndex == -1) {
            return false;
        }

        int row = bedIndex / 5;
        int column = bedIndex % 5;

        return beds[row][column] == null;
    }

    public void displayBedLayout() {

        System.out.println("\n===== WARD BED LAYOUT =====");

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0; column < beds[row].length; column++) {

                String bedNumber = String.format(
                        "B%02d", (row * 5) + column + 1
                );

                if (beds[row][column] == null) {
                    System.out.print("[" + bedNumber + ": Available] ");
                } else {
                    System.out.print(
                            "[" + bedNumber + ": "
                            + beds[row][column].getFirstName()
                            + " "
                            + beds[row][column].getLastName()
                            + "] "
                    );
                }
            }

            System.out.println();
        }

        System.out.println("============================");
    }

    public int getAvailableBedCount() {

        int count = 0;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0; column < beds[row].length; column++) {

                if (beds[row][column] == null) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getOccupiedBedCount() {

        int count = 0;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0; column < beds[row].length; column++) {

                if (beds[row][column] != null) {
                    count++;
                }
            }
        }

        return count;
    }

    private int getBedIndex(String bedNumber) {

        if (bedNumber == null) {
            return -1;
        }

        bedNumber = bedNumber.toUpperCase();

        if (!bedNumber.matches("B(0[1-9]|1[0-9]|20)")) {
            return -1;
        }

        return Integer.parseInt(bedNumber.substring(1)) - 1;
    }
    
    public void displayAvailableBeds() {

    System.out.println("\n===== AVAILABLE BEDS =====");

    boolean found = false;

    for (int row = 0; row < beds.length; row++) {

        for (int column = 0; column < beds[row].length; column++) {

            if (beds[row][column] == null) {

                String bedNumber = String.format(
                        "B%02d", (row * 5) + column + 1
                );

                System.out.println(bedNumber);
                found = true;
            }
        }
    }

    if (!found) {
        System.out.println("No beds are currently available.");
    }

    System.out.println("==========================");
}

public void displayOccupiedBeds() {

    System.out.println("\n===== OCCUPIED BEDS =====");

    boolean found = false;

    for (int row = 0; row < beds.length; row++) {

        for (int column = 0; column < beds[row].length; column++) {

            if (beds[row][column] != null) {

                String bedNumber = String.format(
                        "B%02d", (row * 5) + column + 1
                );

                Inpatient patient = beds[row][column];

                System.out.println(
                        bedNumber + " - "
                        + patient.getFirstName() + " "
                        + patient.getLastName()
                        + " (Patient ID: "
                        + patient.getPatientId() + ")"
                );

                found = true;
            }
        }
    }

    if (!found) {
        System.out.println("No beds are currently occupied.");
    }

    System.out.println("=========================");
}
}