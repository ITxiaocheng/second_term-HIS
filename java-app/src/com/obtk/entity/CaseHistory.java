package com.obtk.entity;

public class CaseHistory {
    private Integer caseId; //序号
    private Integer caseCard; //就诊卡号
    private String caseName; //就诊人姓名
    private String caseGender; //就诊人性别
    private Integer caseAge; //就诊人年龄
    private String caseDate; //候诊日期
    private String caseIdNumber; //就诊人身份证号
    private String caseMoney; //费别
    private String caseDepartments; //科室
    private String caseDoctor; //预约医生
    private String caseComplaint; //主诉
    private String casePHI; //现病史
    private String casePreviousHistory; //既往史
    private String caseHealthCheckup; //体格检查
    private String caseAuxiliaryExamination; //辅助检查
    private String caseDiagnose; //诊断
    private String caseOpinion; //处理意见
    private String caseWriteDate; //书写日期

    public CaseHistory() {
    }

    public CaseHistory(Integer caseCard, String caseName, String caseGender, Integer caseAge, String caseDate, String caseIdNumber, String caseMoney, String caseDepartments, String caseDoctor, String caseComplaint, String casePHI, String casePreviousHistory, String caseHealthCheckup, String caseAuxiliaryExamination, String caseDiagnose, String caseOpinion) {
        this.caseCard = caseCard;
        this.caseName = caseName;
        this.caseGender = caseGender;
        this.caseAge = caseAge;
        this.caseDate = caseDate;
        this.caseIdNumber = caseIdNumber;
        this.caseMoney = caseMoney;
        this.caseDepartments = caseDepartments;
        this.caseDoctor = caseDoctor;
        this.caseComplaint = caseComplaint;
        this.casePHI = casePHI;
        this.casePreviousHistory = casePreviousHistory;
        this.caseHealthCheckup = caseHealthCheckup;
        this.caseAuxiliaryExamination = caseAuxiliaryExamination;
        this.caseDiagnose = caseDiagnose;
        this.caseOpinion = caseOpinion;
    }

    public CaseHistory(Integer caseId, Integer caseCard, String caseName, String caseGender, Integer caseAge, String caseDate, String caseIdNumber, String caseDepartments, String caseDoctor, String caseComplaint, String casePHI, String casePreviousHistory, String caseHealthCheckup, String caseAuxiliaryExamination, String caseDiagnose, String caseOpinion, String caseWriteDate) {
        this.caseId = caseId;
        this.caseCard = caseCard;
        this.caseName = caseName;
        this.caseGender = caseGender;
        this.caseAge = caseAge;
        this.caseDate = caseDate;
        this.caseIdNumber = caseIdNumber;
        this.caseDepartments = caseDepartments;
        this.caseDoctor = caseDoctor;
        this.caseComplaint = caseComplaint;
        this.casePHI = casePHI;
        this.casePreviousHistory = casePreviousHistory;
        this.caseHealthCheckup = caseHealthCheckup;
        this.caseAuxiliaryExamination = caseAuxiliaryExamination;
        this.caseDiagnose = caseDiagnose;
        this.caseOpinion = caseOpinion;
        this.caseWriteDate = caseWriteDate;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseCard() {
        return caseCard;
    }

    public void setCaseCard(Integer caseCard) {
        this.caseCard = caseCard;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseGender() {
        return caseGender;
    }

    public void setCaseGender(String caseGender) {
        this.caseGender = caseGender;
    }

    public Integer getCaseAge() {
        return caseAge;
    }

    public void setCaseAge(Integer caseAge) {
        this.caseAge = caseAge;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getCaseIdNumber() {
        return caseIdNumber;
    }

    public void setCaseIdNumber(String caseIdNumber) {
        this.caseIdNumber = caseIdNumber;
    }

    public String getCaseMoney() {
        return caseMoney;
    }

    public void setCaseMoney(String caseMoney) {
        this.caseMoney = caseMoney;
    }

    public String getCaseDepartments() {
        return caseDepartments;
    }

    public void setCaseDepartments(String caseDepartments) {
        this.caseDepartments = caseDepartments;
    }

    public String getCaseDoctor() {
        return caseDoctor;
    }

    public void setCaseDoctor(String caseDoctor) {
        this.caseDoctor = caseDoctor;
    }

    public String getCaseComplaint() {
        return caseComplaint;
    }

    public void setCaseComplaint(String caseComplaint) {
        this.caseComplaint = caseComplaint;
    }

    public String getCasePHI() {
        return casePHI;
    }

    public void setCasePHI(String casePHI) {
        this.casePHI = casePHI;
    }

    public String getCasePreviousHistory() {
        return casePreviousHistory;
    }

    public void setCasePreviousHistory(String casePreviousHistory) {
        this.casePreviousHistory = casePreviousHistory;
    }

    public String getCaseHealthCheckup() {
        return caseHealthCheckup;
    }

    public void setCaseHealthCheckup(String caseHealthCheckup) {
        this.caseHealthCheckup = caseHealthCheckup;
    }

    public String getCaseAuxiliaryExamination() {
        return caseAuxiliaryExamination;
    }

    public void setCaseAuxiliaryExamination(String caseAuxiliaryExamination) {
        this.caseAuxiliaryExamination = caseAuxiliaryExamination;
    }

    public String getCaseDiagnose() {
        return caseDiagnose;
    }

    public void setCaseDiagnose(String caseDiagnose) {
        this.caseDiagnose = caseDiagnose;
    }

    public String getCaseOpinion() {
        return caseOpinion;
    }

    public void setCaseOpinion(String caseOpinion) {
        this.caseOpinion = caseOpinion;
    }

    public String getCaseWriteDate() {
        return caseWriteDate;
    }

    public void setCaseWriteDate(String caseWriteDate) {
        this.caseWriteDate = caseWriteDate;
    }

    @Override
    public String toString() {
        return "CaseHistory{" +
                "caseId=" + caseId +
                ", caseCard=" + caseCard +
                ", caseName='" + caseName + '\'' +
                ", caseGender='" + caseGender + '\'' +
                ", caseAge=" + caseAge +
                ", caseDate='" + caseDate + '\'' +
                ", caseIdNumber='" + caseIdNumber + '\'' +
                ", caseMoney='" + caseMoney + '\'' +
                ", caseDepartments='" + caseDepartments + '\'' +
                ", caseDoctor='" + caseDoctor + '\'' +
                ", caseComplaint='" + caseComplaint + '\'' +
                ", casePHI='" + casePHI + '\'' +
                ", casePreviousHistory='" + casePreviousHistory + '\'' +
                ", caseHealthCheckup='" + caseHealthCheckup + '\'' +
                ", caseAuxiliaryExamination='" + caseAuxiliaryExamination + '\'' +
                ", caseDiagnose='" + caseDiagnose + '\'' +
                ", caseOpinion='" + caseOpinion + '\'' +
                ", caseWriteDate='" + caseWriteDate + '\'' +
                '}';
    }
}
