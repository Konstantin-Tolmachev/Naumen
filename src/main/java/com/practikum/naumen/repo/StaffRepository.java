package com.practikum.naumen.repo;


import com.practikum.naumen.models.Request;
import com.practikum.naumen.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    /*-----------Фильтр должность-----------*/
  //  List<Staff> findByPosition(String position);

 //   List<Staff> findDistinctByPosition(String position);

    List<Staff> findAllByPositionOrderByIdDesc(String position);

    List<Staff> findAllByOrderByIdDesc();
    List<Staff> findAllByPosition(String filter);
}
