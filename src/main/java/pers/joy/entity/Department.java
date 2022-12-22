package pers.joy.entity;

import java.io.Serializable;

public class Department implements Serializable {

    private String no;
    private String name;

    public Department() {
    }

    public Department(String no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }
}
