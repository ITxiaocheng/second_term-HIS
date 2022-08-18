package com.obtk.entity;

public class Doctor {
    private int docId;
    private String docName; //专家名称
    private String docVisitTime; //出诊时间
    private int docTotalNumber; //总号
    private int docRemainingNumber; //余号
    private String docIntroduction; //简介

    public Doctor() {
    }

    public Doctor(String docName, String docVisitTime, int docTotalNumber, int docRemainingNumber, String docIntroduction) {
        this.docName = docName;
        this.docVisitTime = docVisitTime;
        this.docTotalNumber = docTotalNumber;
        this.docRemainingNumber = docRemainingNumber;
        this.docIntroduction = docIntroduction;
    }

    public Doctor(int docId, String docName, String docVisitTime, int docTotalNumber, int docRemainingNumber, String docIntroduction) {
        this.docId = docId;
        this.docName = docName;
        this.docVisitTime = docVisitTime;
        this.docTotalNumber = docTotalNumber;
        this.docRemainingNumber = docRemainingNumber;
        this.docIntroduction = docIntroduction;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocVisitTime() {
        return docVisitTime;
    }

    public void setDocVisitTime(String docVisitTime) {
        this.docVisitTime = docVisitTime;
    }

    public int getDocTotalNumber() {
        return docTotalNumber;
    }

    public void setDocTotalNumber(int docTotalNumber) {
        this.docTotalNumber = docTotalNumber;
    }

    public int getDocRemainingNumber() {
        return docRemainingNumber;
    }

    public void setDocRemainingNumber(int docRemainingNumber) {
        this.docRemainingNumber = docRemainingNumber;
    }

    public String getDocIntroduction() {
        return docIntroduction;
    }

    public void setDocIntroduction(String docIntroduction) {
        this.docIntroduction = docIntroduction;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "docId=" + docId +
                ", docName='" + docName + '\'' +
                ", docVisitTime='" + docVisitTime + '\'' +
                ", docTotalNumber=" + docTotalNumber +
                ", docRemainingNumber=" + docRemainingNumber +
                ", docIntroduction='" + docIntroduction + '\'' +
                '}';
    }
}
