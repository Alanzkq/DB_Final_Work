package Pojo;

public class Reservation {
    private String patientNumber;
    private String patientName;
    private int patientAge;
    private String patientPhone;
    private String doctorNumber;
    private String doctorName;
    private String doctorPhone;
    private String timeAppointment;

    public Reservation(String patientNumber, String patientName, int patientAge, String patientPhone,
                       String doctorNumber, String doctorName, String doctorPhone, String timeAppointment) {
        this.patientNumber = patientNumber;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientPhone = patientPhone;
        this.doctorNumber = doctorNumber;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.timeAppointment = timeAppointment;
    }

    // Getters
    public String getPatientNumber() {
        return patientNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public String getTimeAppointment() {
        return timeAppointment;
    }
}
