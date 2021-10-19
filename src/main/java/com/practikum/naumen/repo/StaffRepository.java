package com.practikum.naumen.repo;


import com.practikum.naumen.models.Position;
import com.practikum.naumen.models.Request;
import com.practikum.naumen.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.stream.Stream;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    //    Вывод всей таблицы сотрудников getUniqueTopicTitles

    //    ФИЛЬТРЫ
    List<Staff> findByPosition(String position);

   // Stream<Staff> findDistinctByPosition(String position);

    List<Staff> findDistinctByPosition(String position);


    List<Staff> findAllByOrderByIdDesc();
    List<Staff> findAllByPositionOrderByIdDesc(String filter);

//    @Query("select distinct t.position from Staff t")
//    List<Staff> getUniqueStaffPositions();

    //position> Collection<String> findDistinctByPosition(position);

   // Set<String> findDistinctByPosition();

//    @Query("SELECT DISTINCT a.position FROM Staff a")
//    List<String> findDistinctPosition();

//    List<Staff> findDistinctByPosition ();

//    @Query("SELECT u FROM Staff u WHERE u.position = :position")
//    Staff findStaffByPositionNamedParamsAndByPosition(
//            @Param("position") String position);



//    @Query("select distinct t from Topic t  left join fetch t.comments")
//    List<Topic> getTopicsWithComments();

//    @Query("select distinct position from Staff")
////    List<String> findDistinctFirstByPosition();



//    List<Staff> findDistinctByPositionNotIn(List<String> position);

//    List<Staff> groupByPosition(String position);

//    @Query("SELECT  FROM Staff")
//    List<Staff> findByPositionn();

//    @Query("SELECT u FROM u Staff")
//    Collection<Staff> findByPositionn();

//    @Query("SELECT u.position FROM Staff u GROUP BY u.position")
//    List<Staff> findByPositionn();

//    List<Staff> findDistinctByPosition(List<String> position);
//    List<Staff> findDistinctByPosition(String position);
//    @Query("SELECT DISTINCT position FROM Staff")
//    List<Staff> findDistinctByPosition();
}
