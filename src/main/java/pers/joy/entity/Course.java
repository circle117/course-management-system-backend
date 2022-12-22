package pers.joy.entity;

import java.io.Serializable;

public class Course implements Serializable {

    private String code;
    private String name;
    private int credit;
    private Department department;
    private User teacher;

    public Course() {
    }

    public Course(String code) {
        this.code = code;
    }

    public Course(String code, String name, int credit, Department department, User teacher) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.department = department;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", department='" + department + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public Department getDepartment() {
        return department;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

}
