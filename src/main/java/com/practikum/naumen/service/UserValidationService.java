package com.practikum.naumen.service;

import com.practikum.naumen.models.Account;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    public String validateUser(Account userForm) {

        String message = "";

//        if (user.getCountry() != null && user.getPhoneNumber() != null) {
//            if (user.getCountry()
//                    .equalsIgnoreCase("India")
//                    && !user.getPhoneNumber()
//                    .startsWith("91")) {
//                message = "Phone number is invalid for " + user.getCountry();
//            }
//        }
        return message;
    }
}
