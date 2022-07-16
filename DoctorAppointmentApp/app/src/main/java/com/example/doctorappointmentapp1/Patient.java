package com.example.doctorappointmentapp1;

public class Patient {
    String patientID;
    String patientName;
    String patientICorPasspordNum;
    String patientNationality;
    String patientGender;
    String patientDOB;
    String patientMobileNum;
    String patientEmail;

    public Patient(String patientID, String patientName, String patientMobileNum, String patientEmail) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientMobileNum = patientMobileNum;
        this.patientEmail = patientEmail;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientICorPasspordNum() {
        return patientICorPasspordNum;
    }

    public String getPatientNationality() {
        return patientNationality;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public String getPatientMobileNum() {
        return patientMobileNum;
    }

    public String getPatientEmail() {
        return patientEmail;
    }
}
