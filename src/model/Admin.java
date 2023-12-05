/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kelompok5
 */
public class Admin {

    //Declare Admin Table Columns
    private final IntegerProperty id_admin;
    private final StringProperty username;
    private final StringProperty password;

    //Constructor
    public Admin() {
        this.id_admin = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    //id_admin
    public int getIdAdmin() {
        return id_admin.get();
    }

    public void setIdAdmin(int id_admin) {
        this.id_admin.set(id_admin);
    }

    public IntegerProperty idAdminProperty() {
        return id_admin;
    }

    //username
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    //password
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
