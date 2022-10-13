package pers.joy.entity;

public class SelectCourse {

    private String sNo;
    private String cCode;
    private String tNo;
    private float grade;
    private float point;
    private String cName;
    private int credit;

    public String getSNo() {
        return sNo;
    }

    public void setSNo(String sNo) {
        this.sNo = sNo;
    }

    public String getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    public String getTNo() {
        return tNo;
    }

    public void setTNo(String tNo) {
        this.tNo = tNo;
    }

    public float getGrade() { return grade; }

    public void setGrade(float grade) { this.grade = grade; }

    public float getPoint() { return point; }

    public void setPoint(float point) { this.point = point; }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public SelectCourse(String sNo, String cCode, float grade, float point, String cName, int credit) {
        this.sNo = sNo;
        this.cCode = cCode;
        this.grade = grade;
        this.point = point;
        this.cName = cName;
        this.credit = credit;
    }

    public SelectCourse(String sNo, String cCode, String tNo) {
        this.sNo = sNo;
        this.cCode = cCode;
        this.tNo = tNo;
    }

    public SelectCourse() {
    }

    @Override
    public String toString() {
        return "SelectCourse{" +
                "sNo='" + sNo + '\'' +
                ", cCode='" + cCode + '\'' +
                ", tNo='" + tNo + '\'' +
                ", grade=" + grade +
                ", point=" + point +
                ", cName='" + cName + '\'' +
                ", credit=" + credit +
                '}';
    }
}
