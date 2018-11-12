package com.xiongxh.employeeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employee implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("employee_name")
    @Expose
    private String name;

    @SerializedName("employee_salary")
    @Expose
    private String salary;

    @SerializedName("employee_age")
    @Expose
    private String age;

    @SerializedName("profile_image")
    @Expose
    private String image;

    public Employee(){}

    public Employee(String id, String name, String age, String salary, String image){
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.image = image;
    }

    public Employee(String name, String age, String salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary){
        this.salary = salary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
