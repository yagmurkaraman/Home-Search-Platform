package com.bootstrappers.backend;

import java.util.ArrayList;

public abstract class SystemUserAbstract implements SystemUser{

    private String name;

    private String surname;

    private String password;

    private String phoneNumber;

    private String email;

    private String userType;

    /**
     * Constructor.
     * @param name name of the user.
     * @param surname last name of the user.
     * @param password password of the user.
     * @param phoneNumber phone number of the user.
     * @param email email of the user.
     * @param userType type of the user; admin or regular user.
     */
    public SystemUserAbstract(String name, String surname, String password, String phoneNumber, String email, String userType) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = userType;
    }

    /**
     * To string method.
     * @return string.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Getter.
     * @return name of the user.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter.
     * @return last name of the user.
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     * Getter.
     * @return password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Getter.
     * @return phone number of the user.
     */
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter.
     * @return email of the user.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Getter.
     * @return type of the user.
     */
    @Override
    public String getUserType(){
        return userType;
    }

    /**
     * Setter.
     * @param name sets name of the user.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter.
     * @param surname sets last name of the user.
     */
    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Setter.
     * @param password sets password of the user.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter.
     * @param phoneNumber sets phone number of the user.
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter.
     * @param email sets email of the user.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Login method.
     * @param userList list of users.
     * @return true if successfull, false otherwise.
     */
    public abstract boolean login(ArrayList<SystemUserAbstract> userList);
}
