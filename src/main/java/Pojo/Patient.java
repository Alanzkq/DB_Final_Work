package Pojo;

public class Patient {
    private String patientName;
    private int patientAge;
    private String patientSex;
    private String patientPhone;
    private String patientID;
    private String patientLogin;
    private String patientPassword;

    public Patient(String patientName, int patientAge, String patientSex, String patientPhone, String patientID, String patientLogin, String patientPassword) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientSex = patientSex;
        this.patientPhone = patientPhone;
        this.patientID = patientID;
        this.patientLogin = patientLogin;
        this.patientPassword = patientPassword;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientLogin() {
        return patientLogin;
    }

    public void setPatientLogin(String patientLogin) {
        this.patientLogin = patientLogin;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }
}