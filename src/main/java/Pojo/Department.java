package Pojo;

public class Department {
    private String departmentNumber;
    private String doctorNumberDeptHead;
    private String departmentName;
    private String departmentPhone;
    private String departmentAddress;
    private int departmentScale;

    public Department(String departmentNumber, String doctorNumberDeptHead, String departmentName,
                      String departmentPhone, String departmentAddress, int departmentScale) {
        this.departmentNumber = departmentNumber;
        this.doctorNumberDeptHead = doctorNumberDeptHead;
        this.departmentName = departmentName;
        this.departmentPhone = departmentPhone;
        this.departmentAddress = departmentAddress;
        this.departmentScale = departmentScale;
    }

    // Getters and Setters
    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDoctorNumberDeptHead() {
        return doctorNumberDeptHead;
    }

    public void setDoctorNumberDeptHead(String doctorNumberDeptHead) {
        this.doctorNumberDeptHead = doctorNumberDeptHead;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public int getDepartmentScale() {
        return departmentScale;
    }

    public void setDepartmentScale(int departmentScale) {
        this.departmentScale = departmentScale;
    }
}
