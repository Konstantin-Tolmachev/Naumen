package com.practikum.naumen.controllers;


import com.practikum.naumen.models.*;

import com.practikum.naumen.repo.*;
//import com.practikum.naumen.service.RequestValidationService;
import com.practikum.naumen.service.UserService;
import com.practikum.naumen.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
//    @Autowired
//    private RequestValidationService requestValidationService;
    @Autowired
    private UserValidationService validationService;

    @GetMapping("/admin-position")
    public String adminAddPosition( Model model) {
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        model.addAttribute("title", "Администратор");
        return "adminHTML/position";
    }

    /* Добавить нового сотрудника */

    @PostMapping("/admin-position")
    public String allPosition( @RequestParam String position,
                            Model model) {
        Position post = new Position (position);
        positionRepository.save(post);
        return "redirect:/admin-position";
    }

    /* Значения из БД занесены в форму редактирования */

    @GetMapping("/admin-position/{id}/edit")
    public String positionEdit (@PathVariable(value = "id") long id, Model model) {
        if(!positionRepository.existsById (id)){
            return "redirect:/admin-position";
        }
        Optional<Position> post = positionRepository.findById(id);
        ArrayList<Position> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "adminHTML/positionEdit";
    }

//    /*Редактирование сотрудника*/

    @PostMapping("/admin-position/{id}/edit")
    public String positionUpdate(@PathVariable(value = "id") long id,
                              @RequestParam String position,
                              Model model) throws Exception {
        Position post = positionRepository.findById(id).orElseThrow(Exception::new);

        post.setPosition(position);
        positionRepository.save(post);
        return "redirect:/admin-position";
    }

//    /* Удалить сотрудника */

    @PostMapping("/admin-position/{id}/remove")
    public String positionDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Position post = positionRepository.findById(id).orElseThrow(Exception::new);
        positionRepository.delete(post);
        return "redirect:/admin-position";
    }

    /* Выводим таблицу сотрудников */

    @GetMapping("/admin")
    public String admin( Model model) {
        Collection<Staff> staffs = staffRepository.findAllByOrderByIdDesc();
        model.addAttribute("positions", extractPositions(staffs));
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
//        Iterable<Position> positions = positionRepository.findAll();
//        model.addAttribute("positions", positions);
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

    @PostMapping("filter-staff")
    public String adminFilterStaff (@RequestParam String filter, Model model) {

        Collection<Staff> staffs = staffRepository.findAll();


        if (filter !=null && !filter.isEmpty()){
            staffs = staffRepository.findDistinctByPosition(filter);
        } else {
            staffs = staffRepository.findAllByOrderByIdDesc();
        }

        model.addAttribute("positions", extractPositions(staffs));
        model.addAttribute("staffs", staffs);
        return "adminHTML/admin";
    }
    private static Set<String> extractPositions (Collection<Staff> staffs) {
        return staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
    }


//    @PostMapping("staffFilter")
//    public String staffFilter (@RequestParam String filter, Model model) {
//
//        Iterable<Staff> staffs;
//
//        if (filter !=null && !filter.isEmpty()){
//            staffs =  staffRepository.findAllByPositionOrderByIdDesc(filter);
//        } else {
//            staffs = staffRepository.findAllByOrderByIdDesc();
//        }
//
////        if (filter !=null && !filter.isEmpty()){
////            staffs =  staffRepository.getUniqueStaffPositions();
////        } else {
////            staffs = staffRepository.findAll();
////        }
//
////        Map<String, List<Staff>> positions = staffs.stream(list)
////                .collect(Collectors.groupingBy(Staff::getPosition));
//
////        stream(() -> spliterator() map.entrySet().stream()
////        List<String> staffs = staffRepository.getUniqueStaffPositions(
//
////        Set<String> positions = staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
////        model.addAttribute("positions", positions);
//
//      //  List<String> positions = staffs.stream().distinct().collect(Collectors.toList());
//
//        model.addAttribute("staffs", staffs);
//        return "adminHTML/admin";
//    }



    @GetMapping("/admin-request")
    public String adminRequest(Model model) {
        Iterable<Request> requests = requestRepository.findAllByOrderByIdDesc();
        model.addAttribute("requests", requests);
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "AdminHTML/request";
    }


    @PostMapping("/admin-request")
    public String addAdminRequest(  @RequestParam String level,
                                    @RequestParam String room,
                                    @RequestParam String fromWhom,
                                    @RequestParam String text,
                                    @RequestParam String toWhom,
                                    Model model) {
        Request request;

        if  (room == "") {
            request = new Request (level, "-",fromWhom, text, toWhom,"Не выполнено","-", "-","-", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "-", "-");
        }
        else {
            request = new Request(level, room, fromWhom, text, toWhom, "Не выполнено", "-","-","-", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "-", "-");
        }
        model.addAttribute("requests", requestRepository.findAllByOrderByIdDesc());

        requestRepository.save(request);

        //model.addAttribute("requests", requestRepository.findAllByOrderByIdDesc());
        return "redirect:/admin-request";
    }




    /*!!!  Значения из БД занесены в форму редактирования !!!*/

    @GetMapping("/admin-request/{id}/edit")
    public String adminRequestEdit (@PathVariable(value = "id") long id, Model model) {
        if(!requestRepository.existsById (id)){
            return "redirect:/admin-request";
        }
        Optional<Request> post = requestRepository.findById(id);
        ArrayList<Request> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "adminHTML/requestEdit";
    }



    @PostMapping("/admin-request/{id}/edit")
    public String adminRequestUpdate(@PathVariable(value = "id") long id,
                                   // @RequestParam("createDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createDate,
                                   @RequestParam String level,
                                   @RequestParam String room,
                                   @RequestParam String fromWhom,
                                   @RequestParam String text,
                                   @RequestParam String toWhom,
//                                   @RequestParam String endDay,
                                   @RequestParam String status,
                                   @RequestParam String fname,
                                   @RequestParam String lname,
                                   @RequestParam String pname,
                                     @RequestParam String comment,
                                   Model model) throws Exception {
        Request post = requestRepository.findById(id).orElseThrow(Exception::new);
        post.setLevel(level);
        post.setRoom(room);
        post.setFromWhom(fromWhom);
        post.setText(text);
        post.setToWhom(toWhom);
        post.setStatus(status);
        post.setFname(fname);
        post.setLname(lname);
        post.setPname(pname);
        post.setComment(comment);

        post.setEndDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        //   post = new Request(korpus, room, fromWhom, text, toWhom, "", "", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "");
        requestRepository.save(post);
        return "redirect:/admin-request";
    }


    /* Удалить заявку */

    @PostMapping("/admin-request/{id}/remove")
    public String AllRequestDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Request post = requestRepository.findById(id).orElseThrow(Exception::new);
        requestRepository.delete(post);
        return "redirect:/admin-request";
    }

    @PostMapping("filter-request")
    public String AllRequestAdminFilter (@RequestParam String filter, Model model) {
        Iterable<Request> requests;

        if (filter !=null && !filter.isEmpty()){
            requests = requestRepository.findAllByStatusOrderByIdDesc(filter);
        } else {
            requests = requestRepository.findAllByOrderByIdDesc();
        }

        model.addAttribute("requests", requests);
        return "adminHTML/request";
    }

    @GetMapping("/admin-account")
    public String adminAccount( Model model) {
        Iterable<Role> roles = roleRepository.findAll();
        Iterable<Account> accounts = accountRepository.findAll();
        model.addAttribute("listRoles", roleRepository.findAll());
        model.addAttribute("userForm", new Account());
        model.addAttribute("roles", roles);
        model.addAttribute("accounts", accounts);
        model.addAttribute("title", "Аккаунты");
        return "adminHTML/account";
    }

    /* Добавить новый аккаунт */

    @PostMapping("/admin-account")
    public String addAccount(@ModelAttribute("userForm") @Valid Account userForm, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "adminHTML/account";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Аккаунт с таким логином уже существует");
            return "adminHTML/account";
        }

     //   accountRepository.save(userForm);
//        model.addAttribute("users", accountRepository.findAll());

//        accountRepository.save(userForm);
        return "redirect:/admin-account";
    }

    /* Значения из БД занесены в форму редактирования */
//
//    @GetMapping("/admin-account/{id}/edit")
//    public String adminEditAccount (@PathVariable(value = "id") long id, Model model) {
//        if(!accountRepository.existsById (id)){
//            return "redirect:/admin-account";
//        }
//        Optional<Account> post = accountRepository.findById(id);
//        ArrayList<Account> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("listRoles", roleRepository.findAll());
//        model.addAttribute("account", accountRepository.findById(id).get());
//        model.addAttribute("post", res);
//        return "adminHTML/accountEdit";
//    }
//
////    /*Редактирование сотрудника*/
//
//    @PostMapping("/admin-account/{id}/edit")
//    public String adminAccountUpdate(@PathVariable(value = "id") long id,
//                                 @RequestParam String username,
//                                 @RequestParam String password,
//                                 Model model) throws Exception {
//        Account post = accountRepository.findById(id).orElseThrow(Exception::new);
//
//        post.setUsername(username);
//        post.setPassword(password);
//        accountRepository.save(post);
//        return "redirect:/admin-account";
//    }

//    /* Удалить аккаунт */

    @PostMapping("/admin-account/{id}/remove")
    public String adminAccountDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Account post = accountRepository.findById(id).orElseThrow(Exception::new);
        accountRepository.delete(post);
        return "redirect:/admin-account";
    }




//    @GetMapping("/registration")
//    public String Registration(Model model) {
//        model.addAttribute("userForm", new Account());
//        return "homeHTML/registration";
//    }
//
//
//    @PostMapping("/registration")
//    public String addAccount(@ModelAttribute("userForm") @Valid Account userForm, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "homeHTML/registration";
//        }
//        if (!userService.saveUser(userForm)){
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "homeHTML/registration";
//        }
//
//        return "redirect:/";
//    }

}
