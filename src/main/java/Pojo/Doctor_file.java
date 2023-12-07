package Pojo;

public class Doctor_file {
    private String doctorNumber;
    private String doctorName;
    private int doctorAge;
    private String doctorSex;
    private String doctorPhone;
    private String doctorLogin;
    private String doctorPassword;
    private String departmentNumDoctorIn;

    public Doctor_file(String doctorNumber, String doctorName, int doctorAge, String doctorSex, String doctorPhone, String doctorLogin, String doctorPassword, String departmentNumDoctorIn) {
        this.doctorNumber = doctorNumber;
        this.doctorName = doctorName;
        this.doctorAge = doctorAge;
        this.doctorSex = doctorSex;
        this.doctorPhone = doctorPhone;
        this.doctorLogin = doctorLogin;
        this.doctorPassword = doctorPassword;
        this.departmentNumDoctorIn = departmentNumDoctorIn;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(int doctorAge) {
        this.doctorAge = doctorAge;
    }

    public String getDoctorSex() {
        return doctorSex;
    }

    public void setDoctorSex(String doctorSex) {
        this.doctorSex = doctorSex;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorLogin() {
        return doctorLogin;
    }

    public void setDoctorLogin(String doctorLogin) {
        this.doctorLogin = doctorLogin;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public String getDepartmentNumDoctorIn() {
        return departmentNumDoctorIn;
    }

    public void setDepartmentNumDoctorIn(String departmentNumDoctorIn) {
        this.departmentNumDoctorIn = departmentNumDoctorIn;
    }
}