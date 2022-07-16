package com.example.doctorappointmentapp1;

public class Appointment {
    String appointmentID;
    String patientID;
    String patientName;
    String appointmentDate;
    String appointmentTime;
    String doctorName;
    String appointmentStatus;

    public Appointment(){

    }

    public Appointment(String appointmentID, String patientName, String appointmentDate, String appointmentTime,
                       String doctorName, String appointmentStatus) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }
}
