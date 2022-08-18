package com.obtk.entity;

public class Medicine {
    private String  medName; //名称
    private String medUsualDose; //常用量
    private String medUnit; //单位
    private String medUsageAndDosage; //用法用量
    private String medSpell; //拼音
    private Integer medShapeCode; //条形码
    private String medClassification; //分类
    private Double medPurchasingPrice; //进价
    private Integer medSellingPrice; //售价
    private Integer medStock; //库存

    public Medicine() {
    }

    //插入
    //获取
    public Medicine(String medName, String medUsualDose, String medUnit, String medUsageAndDosage, String medSpell, Integer medShapeCode, String medClassification, Double medPurchasingPrice, Integer medSellingPrice, Integer medStock) {
        this.medName = medName;
        this.medUsualDose = medUsualDose;
        this.medUnit = medUnit;
        this.medUsageAndDosage = medUsageAndDosage;
        this.medSpell = medSpell;
        this.medShapeCode = medShapeCode;
        this.medClassification = medClassification;
        this.medPurchasingPrice = medPurchasingPrice;
        this.medSellingPrice = medSellingPrice;
        this.medStock = medStock;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedUsualDose() {
        return medUsualDose;
    }

    public void setMedUsualDose(String medUsualDose) {
        this.medUsualDose = medUsualDose;
    }

    public String getMedUnit() {
        return medUnit;
    }

    public void setMedUnit(String medUnit) {
        this.medUnit = medUnit;
    }

    public String getMedUsageAndDosage() {
        return medUsageAndDosage;
    }

    public void setMedUsageAndDosage(String medUsageAndDosage) {
        this.medUsageAndDosage = medUsageAndDosage;
    }

    public String getMedSpell() {
        return medSpell;
    }

    public void setMedSpell(String medSpell) {
        this.medSpell = medSpell;
    }

    public Integer getMedShapeCode() {
        return medShapeCode;
    }

    public void setMedShapeCode(Integer medShapeCode) {
        this.medShapeCode = medShapeCode;
    }

    public String getMedClassification() {
        return medClassification;
    }

    public void setMedClassification(String medClassification) {
        this.medClassification = medClassification;
    }

    public Double getMedPurchasingPrice() {
        return medPurchasingPrice;
    }

    public void setMedPurchasingPrice(Double medPurchasingPrice) {
        this.medPurchasingPrice = medPurchasingPrice;
    }

    public Integer getMedSellingPrice() {
        return medSellingPrice;
    }

    public void setMedSellingPrice(Integer medSellingPrice) {
        this.medSellingPrice = medSellingPrice;
    }

    public Integer getMedStock() {
        return medStock;
    }

    public void setMedStock(Integer medStock) {
        this.medStock = medStock;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                ", medName='" + medName + '\'' +
                ", medUsualDose='" + medUsualDose + '\'' +
                ", medUnit='" + medUnit + '\'' +
                ", medUsageAndDosage='" + medUsageAndDosage + '\'' +
                ", medSpell='" + medSpell + '\'' +
                ", medShapeCode=" + medShapeCode +
                ", medClassification='" + medClassification + '\'' +
                ", medPurchasingPrice=" + medPurchasingPrice +
                ", medSellingPrice=" + medSellingPrice +
                ", medStock=" + medStock +
                '}';
    }
}
