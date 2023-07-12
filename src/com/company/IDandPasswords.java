package com.company;

import java.util.HashMap;

public class IDandPasswords {

    HashMap<String, String> logininfo = new HashMap<String, String>();

    IDandPasswords() {

        logininfo.put("ashraf maged", "ashraf22");
        logininfo.put("ashraf el saad", "PASSWORD");
        logininfo.put("root", "password");
    }

    public HashMap getLoginInfo() {
        return logininfo;
    }
}
