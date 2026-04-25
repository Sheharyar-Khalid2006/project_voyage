package com.oop.voyage.project_voyage.model;

public abstract class User {
    private String name;
    private String cnic;
    private String phone;
    private String email;

    public User(String cnic, String phone, String email) {
        this.cnic  = cnic;
        this.phone = phone;
        this.email = email;
        this.name  = "User";
    }

    // Getter methods
    public String getCnic()  {
        return cnic;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getName()  {
        return name;
    }

    // Setter methods
    public void setName(String name)   {
        this.name = name;
    }
    public void setCnic(String cnic)   {
        this.cnic = cnic;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public abstract String getRole();
}