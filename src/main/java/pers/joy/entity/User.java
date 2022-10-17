package pers.joy.entity;

import com.alibaba.druid.support.ibatis.SpringIbatisBeanNameAutoProxyCreator;

public class User {

    private String no;
    private String name;
    private String department;
    private String gender;
    private String birthday;
    private String username;
    private String password;

    public String getNo() {
        return no;
    }

    public String getName() { return name; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() { return department; }

    public String getGender() { return gender; }

    public String getBirthday() { return birthday; }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) { this.name = name; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(String department) { this.department = department; }

    public void setGender(String gender) { this.gender = gender; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    @Override
    public String toString() {
        return "User{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String no, String name, String department, String gender, String birthday, String username, String password) {
        this.no = no;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
    }

    public User(String no, String username, String password) {
        this.no = no;
        this.username = username;
        this.password = password;
    }

    public User(String no, String name) {
        this.no = no;
        this.name = name;
    }

    public User() {
    }

}
