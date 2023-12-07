package Pojo;

public class Drug {
    private String drugNumber;
    private String drugName;
    private String drugType;
    private double drugPrice;
    private String drugFace;
    private String drugDosage;

    public Drug(String drugNumber, String drugName, String drugType, double drugPrice, String drugFace, String drugDosage) {
        this.drugNumber = drugNumber;
        this.drugName = drugName;
        this.drugType = drugType;
        this.drugPrice = drugPrice;
        this.drugFace = drugFace;
        this.drugDosage = drugDosage;
    }

    public String getDrugNumber() {
        return drugNumber;
    }

    public void setDrugNumber(String drugNumber) {
        this.drugNumber = drugNumber;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(double drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getDrugFace() {
        return drugFace;
    }

    public void setDrugFace(String drugFace) {
        this.drugFace = drugFace;
    }

    public String getDrugDosage() {
        return drugDosage;
    }

    public void setDrugDosage(String drugDosage) {
        this.drugDosage = drugDosage;
    }
}
