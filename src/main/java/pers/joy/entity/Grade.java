package pers.joy.entity;


import java.io.Serializable;

public class Grade implements Serializable {

    private String studentNo;
    private Course course;
    private String teacherNo;
    private float grade;
    private float point;

    public Grade() {
    }

    public Grade(String studentNo, Course course, String teacherNo, float grade, float point) {
        this.studentNo = studentNo;
        this.course = course;
        this.teacherNo = teacherNo;
        this.grade = grade;
        this.point = point;
    }

    public Grade(String studentNo, Course course, String teacherNo) {
        this.studentNo = studentNo;
        this.course = course;
        this.teacherNo = teacherNo;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "studentNo='" + studentNo + '\'' +
                ", course='" + course + '\'' +
                ", teacherNo='" + teacherNo + '\'' +
                ", grade=" + grade +
                ", point=" + point +
                '}';
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setCourseCode(Course course) {
        this.course = course;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public Course getCourse() {
        return course;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public float getGrade() {
        return grade;
    }

    public float getPoint() {
        return point;
    }

}
