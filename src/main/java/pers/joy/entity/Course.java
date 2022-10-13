package pers.joy.entity;

import java.util.*;

public class Course {

    private String cCode;
    private String cName;
    private int credit;
    private String cDept;
    private String tName;
    private String tNo;

    public String getCCode() {
        return cCode;
    }

    public String getCName() {
        return cName;
    }

    public int getCredit() {
        return credit;
    }

    public String getCDept() {
        return cDept;
    }

    public String getTName() {
        return tName;
    }

    public String getTNo() {
        return tNo;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setCDept(String cDept) {
        this.cDept = cDept;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public void setTNo(String tNo) {
        this.tNo = tNo;
    }

    public Course(String cCode, String cName, int credit, String cDept, String tName, String tNo) {
        this.cCode = cCode;
        this.cName = cName;
        this.credit = credit;
        this.cDept = cDept;
        this.tName = tName;
        this.tNo = tNo;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "cCode='" + cCode + '\'' +
                ", cName='" + cName + '\'' +
                ", credit=" + credit +
                ", cDept='" + cDept + '\'' +
                ", tName='" + tName + '\'' +
                ", tNo='" + tNo + '\'' +
                '}';
    }

    public Map<String, String> getNotNullValue() {
        Map<String, String> notNullValue = new HashMap<>();
        if (cCode != null) {
            notNullValue.put("cCode", cCode);
        }
        if (cName != null) {
            notNullValue.put("cName", cName);
        }
        if (credit != 0) {
            notNullValue.put("credit", String.valueOf(credit));
        }
        if (cDept != null) {
            notNullValue.put("cDept", cDept);
        }
        if (tNo != null) {
            notNullValue.put("tNo", tNo);
        }
        return notNullValue;
    }
}
