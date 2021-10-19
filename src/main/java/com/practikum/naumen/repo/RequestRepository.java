package com.practikum.naumen.repo;


import com.practikum.naumen.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

//    List<Request> findAllByOrderByIdDesc();



    List<Request> findAllByOrderByIdDesc();

    List<Request> findAllByStatusOrderByIdDesc(String filter);

    //List<Request> findAllByStatusOrderByIdDescT (String filter);
    List<Request> findAllByRoomOrderByIdDesc(String room);

//    default Collection<Request> findElectro() {
//        return findByToWhomOrderByIdDesc("Электромонтер");
//    }
    //Collection<Request> findByToWhomOrderByIdDesc(String электромонтер);



    // Iterable<Request> findByStatusAndToWhom(String filter, String электромонтер);
//    Iterable<Request> findByStatusAndToWhom(String filter, String toWhom);

    List<Request> findAllByStatusAndToWhom(String status, String toWhom);


}
