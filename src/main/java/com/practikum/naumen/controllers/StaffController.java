package com.practikum.naumen.controllers;

import com.practikum.naumen.models.Position;
import com.practikum.naumen.models.Request;
import com.practikum.naumen.repo.PositionRepository;
import com.practikum.naumen.repo.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;


@Controller
public class StaffController {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PositionRepository positionRepository;


    @GetMapping("/staff-request")
    public String staffRequest(Model model) {
        Iterable<Request> requests = requestRepository.findAllByOrderByIdDesc();
        model.addAttribute("requests", requests);
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "StaffHTML/request";
    }


    @PostMapping("/staff-request")
    public String addStaffRequest(@RequestParam String level,
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

        return "redirect:/staff-request";
    }




    /*!!!  Значения из БД занесены в форму редактирования !!!*/

    @GetMapping("/staff-request/{id}/edit")
    public String staffRequest (@PathVariable(value = "id") long id, Model model) {
        if(!requestRepository.existsById (id)){
            return "redirect:/staff-request";
        }
        Optional<Request> post = requestRepository.findById(id);
        ArrayList<Request> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "staffHTML/requestEdit";
    }



    @PostMapping("/staff-request/{id}/edit")
    public String staffRequestUpdate(@PathVariable(value = "id") long id,
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
        return "redirect:/staff-request";
    }


    /* Удалить заявку */

//    @PostMapping("/staff-request/{id}/remove")
//    public String staffRequestDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
//        Request post = requestRepository.findById(id).orElseThrow(Exception::new);
//        requestRepository.delete(post);
//        return "redirect:/staff-request";
//    }


    @PostMapping("staff-filter-request")
    public String staffRequestFilter (@RequestParam String filter, @RequestParam String toWhom, Model model) {
        Iterable<Request> requests;

        if (filter !=null && !filter.isEmpty()){
            requests = requestRepository.findAllByStatusAndToWhom(filter, toWhom);
        } else {
            return "redirect:/staff-request";

        }

        model.addAttribute("requests", requests);
        return "staffHTML/request";
    }



}
