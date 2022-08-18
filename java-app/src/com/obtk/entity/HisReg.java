package com.obtk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HisReg {
    private Integer regId; //id
    private Integer regCard; //就诊卡号
    private String regName;
    private String regGender;
    private Integer regAge;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String regDate;
    private String regIdNumber;
    private String regSite;
    private String regDepartments;
    private String regDoctor;
    private String regState;

    public HisReg() {
    }

    public HisReg(String regName, String regGender, Integer regAge, String regDate, String regIdNumber, String regSite, String regDepartments, String regDoctor) {
        this.regName = regName;
        this.regGender = regGender;
        this.regAge = regAge;
        this.regDate = regDate;
        this.regIdNumber = regIdNumber;
        this.regSite = regSite;
        this.regDepartments = regDepartments;
        this.regDoctor = regDoctor;
    }

    public HisReg(String regName, String regGender, Integer regAge, String regDate, String regIdNumber, String regSite, String regDepartments, String regDoctor, String regState) {
        this.regName = regName;
        this.regGender = regGender;
        this.regAge = regAge;
        this.regDate = regDate;
        this.regIdNumber = regIdNumber;
        this.regSite = regSite;
        this.regDepartments = regDepartments;
        this.regDoctor = regDoctor;
        this.regState = regState;
    }

    public HisReg(Integer regCard, String regName, String regGender, Integer regAge, String regDate, String regIdNumber, String regSite, String regDepartments, String regDoctor) {
        this.regCard = regCard;
        this.regName = regName;
        this.regGender = regGender;
        this.regAge = regAge;
        this.regDate = regDate;
        this.regIdNumber = regIdNumber;
        this.regSite = regSite;
        this.regDepartments = regDepartments;
        this.regDoctor = regDoctor;
    }

    public HisReg(Integer regId, String regName, String regGender, Integer regAge, String regDate, String regIdNumber, String regSite, String regDepartments, String regDoctor, String regState) {
        this.regId = regId;
        this.regName = regName;
        this.regGender = regGender;
        this.regAge = regAge;
        this.regDate = regDate;
        this.regIdNumber = regIdNumber;
        this.regSite = regSite;
        this.regDepartments = regDepartments;
        this.regDoctor = regDoctor;
        this.regState = regState;
    }

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public Integer getRegCard() {
        return regCard;
    }

    public void setRegCard(Integer regCard) {
        this.regCard = regCard;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegGender() {
        return regGender;
    }

    public void setRegGender(String regGender) {
        this.regGender = regGender;
    }

    public Integer getRegAge() {
        return regAge;
    }

    public void setRegAge(Integer regAge) {
        this.regAge = regAge;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegIdNumber() {
        return regIdNumber;
    }

    public void setRegIdNumber(String regIdNumber) {
        this.regIdNumber = regIdNumber;
    }

    public String getRegSite() {
        return regSite;
    }

    public void setRegSite(String regSite) {
        this.regSite = regSite;
    }

    public String getRegDepartments() {
        return regDepartments;
    }

    public void setRegDepartments(String regDepartments) {
        this.regDepartments = regDepartments;
    }

    public String getRegDoctor() {
        return regDoctor;
    }

    public void setRegDoctor(String regDoctor) {
        this.regDoctor = regDoctor;
    }

    public String getRegState() {
        return regState;
    }

    public void setRegState(String regState) {
        this.regState = regState;
    }

    @Override
    public String toString() {
        return "HisReg{" +
                "regId=" + regId +
                ", regCard=" + regCard +
                ", regName='" + regName + '\'' +
                ", regGender='" + regGender + '\'' +
                ", regAge=" + regAge +
                ", regDate='" + regDate + '\'' +
                ", regIdNumber='" + regIdNumber + '\'' +
                ", regSite='" + regSite + '\'' +
                ", regDepartments='" + regDepartments + '\'' +
                ", regDoctor='" + regDoctor + '\'' +
                ", regState='" + regState + '\'' +
                '}';
    }
}
