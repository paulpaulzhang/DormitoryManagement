package cn.paulpaulzhang.dormitorymanagement.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.database.model
 * 创建时间: 8/3/2019
 * 创建人: zlm31
 * 描述:
 */
@Entity
public class Student {
    @Id(assignable = true)
    private long id;

    private String dormitory;

    private String username;

    private String password;

    private String name;

    private String gender;

    public Student(long id, String dormitory, String username, String password, String name, String gender) {
        this.id = id;
        this.dormitory = dormitory;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
