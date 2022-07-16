package com.example.doctorappointmentapp1;

public class Doctor {
    String doctorID;
    String image;
    String doctorName;
    String doctorSpeciality;
    String educationBackground;

    public Doctor() {

    }

    public Doctor(String doctorID, String image, String doctorName, String doctorSpeciality, String educationBackground) {
        this.doctorID = doctorID;
        this.image = image;
        this.doctorName = doctorName;
        this.doctorSpeciality = doctorSpeciality;
        this.educationBackground = educationBackground;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getImage() {
        return image;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public String getEducationBackground() {
        return educationBackground;
    }
}
