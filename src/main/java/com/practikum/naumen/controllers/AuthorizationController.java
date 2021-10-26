package com.practikum.naumen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthorizationController {

    /*-----------Стартовая страница авторизации-----------*/

    @GetMapping("/")
    public String authorization( Model model) {
        model.addAttribute("title", "Авторизация");
        return "homeHTML/authorization";
    }

}
