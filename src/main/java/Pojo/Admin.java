package Pojo;

public class Admin {
    private String adminLogin;
    private String adminPassword;


    public Admin(String adminLogin, String adminPassword) {
        this.adminLogin = adminLogin;
        this.adminPassword = adminPassword;
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
