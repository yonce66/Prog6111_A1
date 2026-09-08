/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospitalpatientadmissionsystem;
import java.util.Scanner;
/**
 *
 * @author emeris
 */
public class HospitalPatientAdmissionSystem {
  public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PatientManager patientManager = new PatientManager();
        BedManager bedManager = new BedManager();

        int choice = 0;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("       MEDICARE HOSPITAL");
            System.out.println("   PATIENT ADMISSION SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Patient Management");
            System.out.println("2. Bed Management");
            System.out.println("3. Reports");
            System.out.println("4. Exit");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        patientManagementMenu(scanner, patientManager);
                        break;

                    case 2:
                        bedManagementMenu(
                                scanner,
                                patientManager,
                                bedManager
                        );
                        break;

                    case 3:
                        reportsMenu(scanner, patientManager, bedManager);
                        break;

                    case 4:
                        System.out.println();
                        System.out.println(
                                "Thank you for using the "
                                + "MediCare Hospital Patient Admission System."
                        );
                        break;

                    default:
                        System.out.println();
                        System.out.println(
                                "Invalid choice. Please select 1-4."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println();
                System.out.println(
                        "Invalid input. Please enter a number."
                );

                choice = 0;
            }

        } while (choice != 4);

        scanner.close();
    }

    // ==============================
    // PATIENT MANAGEMENT MENU


    private static void patientManagementMenu(
            Scanner scanner,
            PatientManager patientManager) {

        int choice = 0;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("        PATIENT MANAGEMENT");
            System.out.println("======================================");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Return to Main Menu");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        registerPatient(scanner, patientManager);
                        break;

                    case 2:
                        searchPatient(scanner, patientManager);
                        break;

                    case 3:
                        updatePatient(scanner, patientManager);
                        break;

                    case 4:
                        deletePatient(scanner, patientManager);
                        break;

                    case 5:
                        patientManager.displayAllPatients();
                        break;

                    case 6:
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1-6."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                choice = 0;
            }

        } while (choice != 6);
    }


    // REGISTER PATIENT

    private static void registerPatient(
            Scanner scanner,
            PatientManager patientManager) {

        System.out.println();
        System.out.println("===== REGISTER PATIENT =====");

        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();

        if (patientManager.searchPatient(patientId) != null) {

            System.out.println(
                    "Patient ID already exists."
            );

            return;
        }

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age;

        try {

            age = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid age. Patient was not registered."
            );

            return;
        }

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String medicalCondition = scanner.nextLine();

        System.out.println();
        System.out.println("Patient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");
        System.out.print("Choose category: ");

        int categoryChoice;

        try {

            categoryChoice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid category. Patient was not registered."
            );

            return;
        }

        PatientCategory category;

        switch (categoryChoice) {

            case 1:
                category = PatientCategory.INPATIENT;
                break;

            case 2:
                category = PatientCategory.OUTPATIENT;
                break;

            case 3:
                category = PatientCategory.EMERGENCY;
                break;

            default:
                System.out.println(
                        "Invalid category. Patient was not registered."
                );

                return;
        }

        Patient patient;

        if (category == PatientCategory.INPATIENT) {

            System.out.print("Ward Number: ");
            String wardNumber = scanner.nextLine();

            patient = new Inpatient(
                    patientId,
                    firstName,
                    lastName,
                    age,
                    gender,
                    medicalCondition,
                    category,
                    wardNumber,
                    ""
            );

        } else {

            patient = new Patient(
                    patientId,
                    firstName,
                    lastName,
                    age,
                    gender,
                    medicalCondition,
                    category
            );
        }

        if (patientManager.registerPatient(patient)) {

            System.out.println();
            System.out.println(
                    "Patient registered successfully."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Patient could not be registered."
            );
        }
    }


    // SEARCH PATIENT


    private static void searchPatient(
            Scanner scanner,
            PatientManager patientManager) {

        System.out.println();
        System.out.println("===== SEARCH PATIENT =====");

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        Patient patient = patientManager.searchPatient(patientId);

        if (patient != null) {

            System.out.println();
            System.out.println("Patient found:");
            System.out.println("------------------------------");

            patient.displayDetails();

            System.out.println("------------------------------");

        } else {

            System.out.println();
            System.out.println("Patient not found.");
        }
    }

    
    // UPDATE PATIENt
    private static void updatePatient(
            Scanner scanner,
            PatientManager patientManager) {

        System.out.println();
        System.out.println("===== UPDATE PATIENT =====");

        System.out.print("Enter Patient ID to update: ");
        String patientId = scanner.nextLine();

        Patient patient = patientManager.searchPatient(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        System.out.println();
        System.out.println("Enter the patient's new information.");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age;

        try {

            age = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Invalid age.");

            return;
        }

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String medicalCondition = scanner.nextLine();

        System.out.println();
        System.out.println("Patient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");
        System.out.print("Choose category: ");

        int categoryChoice;

        try {

            categoryChoice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Invalid category.");

            return;
        }

        PatientCategory category;

        switch (categoryChoice) {

            case 1:
                category = PatientCategory.INPATIENT;
                break;

            case 2:
                category = PatientCategory.OUTPATIENT;
                break;

            case 3:
                category = PatientCategory.EMERGENCY;
                break;

            default:
                System.out.println("Invalid category.");

                return;
        }

        boolean updated = patientManager.updatePatient(
                patientId,
                firstName,
                lastName,
                age,
                gender,
                medicalCondition,
                category
        );

        if (updated) {

            System.out.println();
            System.out.println(
                    "Patient updated successfully."
            );

        } else {

            System.out.println(
                    "Patient could not be updated."
            );
        }
    }

  
    // DELETE PATIENT
    private static void deletePatient(
            Scanner scanner,
            PatientManager patientManager) {

        System.out.println();
        System.out.println("===== DELETE PATIENT =====");

        System.out.print("Enter Patient ID to delete: ");
        String patientId = scanner.nextLine();

        boolean deleted = patientManager.deletePatient(patientId);

        if (deleted) {

            System.out.println();
            System.out.println(
                    "Patient deleted successfully."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Patient not found."
            );
        }
    }

    
    // BED MANAGEMENT MENU

    private static void bedManagementMenu(
            Scanner scanner,
            PatientManager patientManager,
            BedManager bedManager) {

        int choice = 0;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("          BED MANAGEMENT");
            System.out.println("======================================");
            System.out.println("1. Display Bed Layout");
            System.out.println("2. Allocate Bed");
            System.out.println("3. Release Bed");
            System.out.println("4. Display Available Beds");
            System.out.println("5. Display Occupied Beds");
            System.out.println("6. Return to Main Menu");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        bedManager.displayBedLayout();
                        break;

                    case 2:
                        allocateBed(
                                scanner,
                                patientManager,
                                bedManager
                        );
                        break;

                    case 3:
                        releaseBed(scanner, bedManager);
                        break;

                    case 4:
                        System.out.println();
                        System.out.println(
                                "Available beds: "
                                + bedManager.getAvailableBedCount()
                        );
                        break;

                    case 5:
                        System.out.println();
                        System.out.println(
                                "Occupied beds: "
                                + bedManager.getOccupiedBedCount()
                        );
                        break;

                    case 6:
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1-6."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                choice = 0;
            }

        } while (choice != 6);
    }

    
    // ALLOCATE BED
    

    private static void allocateBed(
            Scanner scanner,
            PatientManager patientManager,
            BedManager bedManager) {

        System.out.println();
        System.out.println("===== ALLOCATE BED =====");

        System.out.print("Enter Inpatient ID: ");
        String patientId = scanner.nextLine();

        Patient patient = patientManager.searchPatient(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        if (!(patient instanceof Inpatient)) {

            System.out.println(
                    "Only Inpatients can be allocated a bed."
            );

            return;
        }

        Inpatient inpatient = (Inpatient) patient;

        if (!inpatient.getBedNumber().isEmpty()) {

            System.out.println(
                    "This patient already has a bed."
            );

            return;
        }

        System.out.print(
                "Enter Bed Number (B01-B20): "
        );

        String bedNumber = scanner.nextLine().toUpperCase();

        if (bedManager.allocateBed(bedNumber, inpatient)) {

            System.out.println();
            System.out.println(
                    "Bed " + bedNumber
                    + " allocated successfully to "
                    + inpatient.getFirstName()
                    + " "
                    + inpatient.getLastName()
                    + "."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Bed could not be allocated."
            );

            System.out.println(
                    "Check that the bed exists and is available."
            );
        }
    }

   
    // RELEASE BED
    

    private static void releaseBed(
            Scanner scanner,
            BedManager bedManager) {

        System.out.println();
        System.out.println("===== RELEASE BED =====");

        System.out.print(
                "Enter Bed Number (B01-B20): "
        );

        String bedNumber = scanner.nextLine().toUpperCase();

        if (bedManager.releaseBed(bedNumber)) {

            System.out.println();
            System.out.println(
                    "Bed " + bedNumber
                    + " released successfully."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Bed could not be released."
            );
        }
    }

   
    // REPORTS MENU
   

  private static void reportsMenu(
        Scanner scanner,
        PatientManager patientManager,
        BedManager bedManager) {

    int choice;

    do {
        System.out.println();
        System.out.println("======================================");
        System.out.println("              REPORTS");
        System.out.println("======================================");
        System.out.println("1. Display Patient Report");
        System.out.println("2. Display Bed Report");
        System.out.println("3. Sort Patients by Surname");
        System.out.println("4. Sort Patients by Patient ID");
        System.out.println("5. Return to Main Menu");
        System.out.println("======================================");
        System.out.print("Enter your choice: ");

        try {
            choice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            choice = 0;
        }

        switch (choice) {

            case 1:
                System.out.println();
                System.out.println("===== PATIENT REPORT =====");

                System.out.println(
                        "Total registered patients: "
                        + patientManager.getPatientCount()
                );

                patientManager.displayAllPatients();
                break;

            case 2:
                System.out.println();
                System.out.println("===== BED REPORT =====");

                System.out.println(
                        "Total occupied beds: "
                        + bedManager.getOccupiedBedCount()
                );

                System.out.println(
                        "Total available beds: "
                        + bedManager.getAvailableBedCount()
                );

                double occupancyPercentage =
                        (bedManager.getOccupiedBedCount() / 20.0) * 100;

                System.out.printf(
                        "Ward occupancy: %.2f%%%n",
                        occupancyPercentage
                );

                bedManager.displayAvailableBeds();
                bedManager.displayOccupiedBeds();
                break;

            case 3:
                patientManager.displayPatientsSortedBySurname();
                break;

            case 4:
                patientManager.displayPatientsSortedByPatientId();
                break;

            case 5:
                break;

            default:
                System.out.println(
                        "Invalid choice. Please select 1-5."
                );
        }

    } while (choice != 5);
}
}
