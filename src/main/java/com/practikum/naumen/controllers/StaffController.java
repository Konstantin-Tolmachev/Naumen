package com.practikum.naumen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StaffController {

    @GetMapping("/staff")
    public String staff( Model model) {
        model.addAttribute("title", "Администратор");
        return "staffHTML/staff";
    }
}
