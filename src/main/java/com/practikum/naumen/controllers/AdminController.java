package com.practikum.naumen.controllers;


import com.practikum.naumen.models.Account;
import com.practikum.naumen.models.Staff;
import com.practikum.naumen.repo.StaffRepository;
import com.practikum.naumen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.stream.Collectors;


import javax.swing.text.Position;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private StaffRepository staffRepository;

    /* Выводим таблицу сотрудников */

    @GetMapping("/admin")
    public String admin( Model model) {
        Iterable<Staff> staffs = staffRepository.findAll();
        model.addAttribute("staffs", staffs);
        model.addAttribute("title", "Администратор");
        return "adminHTML/admin";
    }

    /* Добавить нового сотрудника */

    @PostMapping("/admin")
    public String allStaff( @RequestParam String fname,
                            @RequestParam String lname,
                            @RequestParam String pname,
                            @RequestParam String position,
                            Model model) {
        Staff post = new Staff (fname,lname,pname,position);
        staffRepository.save(post);
        return "redirect:/admin";
    }

    /* Значения из БД занесены в форму редактирования */

    @GetMapping("/admin/{id}/edit")
    public String staffEdit (@PathVariable(value = "id") long id, Model model) {
        if(!staffRepository.existsById (id)){
            return "redirect:/admin";
        }
        Optional<Staff> post = staffRepository.findById(id);
        ArrayList<Staff> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "adminHTML/staffEdit";
    }

    /*Редактирование сотрудника*/

    @PostMapping("/admin/{id}/edit")
    public String staffUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String fname,
                                 @RequestParam String lname,
                                 @RequestParam String pname,
                                 @RequestParam String position,
                                 Model model) throws Exception {
        Staff post = staffRepository.findById(id).orElseThrow(Exception::new);
        post.setFname(fname);
        post.setLname(lname);
        post.setPname(pname);
        post.setPosition(position);
        staffRepository.save(post);
        return "redirect:/admin";
    }

    /* Удалить сотрудника */

    @PostMapping("/admin/{id}/remove")
    public String staffDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Staff post = staffRepository.findById(id).orElseThrow(Exception::new);
        staffRepository.delete(post);
        return "redirect:/admin";
    }

   /* Фильтр для поиска сотрудников по должности */

    @PostMapping("staffFilter")
    public String staffFilter (@RequestParam String filter, Model model) {
        Iterable<Staff> staffs;
        if (filter !=null && !filter.isEmpty()){
            staffs = staffRepository.findByPosition(filter);
        } else {
            staffs = staffRepository.findAll();
        }

        Set<String> positions = staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
        model.addAttribute("positions", positions);

      //  List<String> positions = staffs.stream().distinct().collect(Collectors.toList());
//        List<String> positions = staffs.stream().distinct().collect(Collectors.toList());
//        model.addAttribute("positions", positions);
        model.addAttribute("staffs", staffs);
        return "adminHTML/admin";
    }




//    @GetMapping("/adminn")
//    public String adminn(Model model) {
//        Iterable<Staff> staffs = staffRepository.findAll();
//        List<String> positions = staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
//        model.addAttribute("positions", positions);
//        model.addAttribute("staffs", staffs);
//        model.addAttribute("title", "Администратор");
//        return "adminHTML/adminn";
//    }

//    @PostMapping("adminFil")
//    public String adminFil( Model model)  {
//        Iterable<Staff> staffs = staffRepository.findAll();
//        Set<String> positions = staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
//        model.addAttribute("positions", positions);
//        model.addAttribute("staffs", staffs);
//        return "adminHTML/adminn";
//    }
//    @PathVariable(value = "position") String position,


    @GetMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("userForm", new Account());
        return "homeHTML/registration";
    }


    @PostMapping("/registration")
    public String addAccount(@ModelAttribute("userForm") @Valid Account userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "homeHTML/registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "homeHTML/registration";
        }

        return "redirect:/";
    }

}
