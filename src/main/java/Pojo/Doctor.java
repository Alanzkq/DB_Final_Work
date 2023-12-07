package Pojo;

public class Doctor {
    private String doctorNumber;
    private String doctorName;
    private int doctorAge;
    private String doctorSex;
    private String doctorPhone;

    // 构造函数
    public Doctor(String doctorNumber, String doctorName, int doctorAge, String doctorSex, String doctorPhone) {
        this.doctorNumber = doctorNumber;
        this.doctorName = doctorName;
        this.doctorAge = doctorAge;
        this.doctorSex = doctorSex;
        this.doctorPhone = doctorPhone;
    }

    // Getters and Setters
    // 根据需要添加属性的获取和设置方法
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
}
