package Pojo;

public class Ward {
    private String wardNumber;
    private String wardName;
    private String wardScale;
    private String wardPhone;
    private String wardAddress;

    public Ward(String wardNumber, String wardName, String wardScale, String wardPhone, String wardAddress) {
        this.wardNumber = wardNumber;
        this.wardName = wardName;
        this.wardScale = wardScale;
        this.wardPhone = wardPhone;
        this.wardAddress = wardAddress;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardScale() {
        return wardScale;
    }

    public void setWardScale(String wardScale) {
        this.wardScale = wardScale;
    }

    public String getWardPhone() {
        return wardPhone;
    }

    public void setWardPhone(String wardPhone) {
        this.wardPhone = wardPhone;
    }

    public String getWardAddress() {
        return wardAddress;
    }

    public void setWardAddress(String wardAddress) {
        this.wardAddress = wardAddress;
    }
}
