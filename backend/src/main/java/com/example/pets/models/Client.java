package com.example.pets.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
public class Client {

    @Id
    private String id = "";
    private String surname = "";
    private String name = "";
    private String patronymic = "";
    private Date birth = new Date();
    private String address = "";
    private String phone = "";
    private String email = "";

    // region private methods
    private boolean validatePhone(String phone) {
        Pattern p = Pattern.compile("^\\+[0-9]{11,}$");
        return p.matcher(phone).find();
    }

    private boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
        return p.matcher(email).find();
    }
    // endregion private methods

    // region constructors
    public Client() {
    }

    public Client(String id, String surname, String name, String patronymic,
                  Date birth, String address, String phone, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
// endregion constructors

    // region getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if ("".equals(phone) || validatePhone(phone))
            this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if ("".equals(email) || validateEmail(email))
            this.email = email;
    }

    // endregion getters and setters
}
