package Pojo;
public class Patient1 {
    private String patientId;
    private String patientName;
    private int patientAge;
    private String patientSex;
    private String patientPhone;

    // 构造函数
    public Patient1(String patientId, String patientName, int patientAge, String patientSex, String patientPhone) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientSex = patientSex;
        this.patientPhone = patientPhone;
    }

    // Getter 和 Setter 方法
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
}
