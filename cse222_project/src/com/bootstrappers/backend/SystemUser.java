package com.bootstrappers.backend;

import java.util.ArrayList;

public interface SystemUser {
    String getName();

    String getSurname();

    String getPassword();

    String getPhoneNumber();

    String getEmail();

    String getUserType();

    void setName(String name);

    void setSurname(String surname);

    void setPassword(String password);

    void setPhoneNumber(String phoneNumber);

    void setEmail(String email);

    boolean login(ArrayList<SystemUserAbstract> userList);
}
