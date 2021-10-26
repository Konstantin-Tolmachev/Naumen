package com.practikum.naumen.repo;


import com.practikum.naumen.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {


    List<Request> findAllByOrderByIdDesc();

    List<Request> findAllByStatusOrderByIdDesc(String filter);

    List<Request> findAllByToWhomOrderByIdDesc(String toWhom);
    List<Request> findAllByFromWhomOrderByIdDesc(String toWhom);


    List<Request> findAllByRoomOrderByIdDesc(String room);

    /*-----------Фильтр статус и исполнитель-----------*/
    List<Request> findAllByStatusAndToWhom(String status, String toWhom);
    /*-----------Фильтр статус и заявитель-----------*/
    List<Request> findAllByStatusAndFromWhom(String status, String fromWhom);


}
