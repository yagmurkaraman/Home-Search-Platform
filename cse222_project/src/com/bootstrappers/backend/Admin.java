package com.bootstrappers.backend;

import java.util.ArrayList;

public class Admin extends SystemUserAbstract {


    /**
     * Constructor.
     * @param name name of admin.
     * @param surname lastname of admin.
     * @param password password of admin.
     * @param phoneNumber phone number of admin.
     * @param email email number of admin.
     * @param userType type whether admin or user.
     */
    public Admin(String name, String surname, String password, String phoneNumber, String email, String userType) {
        super(name, surname, password, phoneNumber, email, userType);
    }

    /**
     * Login method for admin.
     * @param userList list of users.
     * @return true if successfull, otherwise false.
     */
    @Override
    public boolean login(ArrayList<SystemUserAbstract> userList) {

        for (SystemUserAbstract target: userList) {
            if (target.getUserType().equals("com.bootstrappers.backend.Admin")){
                if (target.getEmail().equals(this.getEmail())){
                    if (target.getPassword().equals(this.getPassword()))
                        return true;
                }
            }
        }
        return false;
    }


}
