/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;
import java.util.ArrayList;
/**
 *
 * @author emeris
 */
public class PatientManager {
 private ArrayList<Patient> patients;  

//constructor 
public PatientManager() {
    patients = new ArrayList<>();
} 
//register
public boolean registerPatient(Patient patient) {

    if (searchPatient(patient.getPatientId()) != null) {
        return false;
    }

    patients.add(patient);
    return true;
}
//search
public Patient searchPatient(String patientId) {

    for (Patient patient : patients) {

        if (patient.getPatientId().equalsIgnoreCase(patientId)) {
            return patient;
        }
    }

    return null;
}
/////Display
public void displayAllPatients() {

    if (patients.isEmpty()) {
        System.out.println("No patients are currently registered.");
        return;
    }

    for (Patient patient : patients) {
        System.out.println("------------------------------");
        patient.displayDetails();
    }

    System.out.println("------------------------------");
}

/////// Update Patient
public boolean updatePatient(String patientId, String firstName,
                             String lastName, int age, String gender,
                             String medicalCondition,
                             PatientCategory category) {

    Patient patient = searchPatient(patientId);

    if (patient == null) {
        return false;
    }

    // Prevent changing between patient categories
    // because Inpatient is a subclass of Patient.
    if (patient.getCategory() != category) {
        return false;
    }

    patient.setFirstName(firstName);
    patient.setLastName(lastName);
    patient.setAge(age);
    patient.setGender(gender);
    patient.setMedicalCondition(medicalCondition);

    return true;
}

////Delete Patient
public boolean deletePatient(String patientId) {

    Patient patient = searchPatient(patientId);

    if (patient == null) {
        return false;
    }

    patients.remove(patient);
    return true;
}
//Patent Count
public int getPatientCount() {
    return patients.size();
}

//ACCESS list 
public ArrayList<Patient> getPatients() {
    return patients;
}

public Patient[] getPatientsAsArray() {

    Patient[] patientArray = new Patient[patients.size()];

    for (int i = 0; i < patients.size(); i++) {
        patientArray[i] = patients.get(i);
    }

    return patientArray;
}

public void displayPatientsSortedBySurname() {

    Patient[] patientArray = getPatientsAsArray();

    // Sort patients by surname
    for (int i = 0; i < patientArray.length - 1; i++) {

        for (int j = 0; j < patientArray.length - i - 1; j++) {

            if (patientArray[j].getLastName()
                    .compareToIgnoreCase(patientArray[j + 1].getLastName()) > 0) {

                Patient temporary = patientArray[j];
                patientArray[j] = patientArray[j + 1];
                patientArray[j + 1] = temporary;
            }
        }
    }

    System.out.println("\n===== PATIENTS SORTED BY SURNAME =====");

    if (patientArray.length == 0) {
        System.out.println("No patients are currently registered.");
        return;
    }

    for (int i = 0; i < patientArray.length; i++) {

        System.out.println("------------------------------");
        patientArray[i].displayDetails();
    }

    System.out.println("------------------------------");
}

public void displayPatientsSortedByPatientId() {

    Patient[] patientArray = getPatientsAsArray();

    // Sort patients by Patient ID
    for (int i = 0; i < patientArray.length - 1; i++) {

        for (int j = 0; j < patientArray.length - i - 1; j++) {

            if (patientArray[j].getPatientId()
                    .compareToIgnoreCase(patientArray[j + 1].getPatientId()) > 0) {

                Patient temporary = patientArray[j];
                patientArray[j] = patientArray[j + 1];
                patientArray[j + 1] = temporary;
            }
        }
    }

    System.out.println("\n===== PATIENTS SORTED BY PATIENT ID =====");

    if (patientArray.length == 0) {
        System.out.println("No patients are currently registered.");
        return;
    }

    for (int i = 0; i < patientArray.length; i++) {

        System.out.println("------------------------------");
        patientArray[i].displayDetails();
    }

    System.out.println("------------------------------");
}
}
