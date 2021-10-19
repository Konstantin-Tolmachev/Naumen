package com.practikum.naumen.repo;

import com.practikum.naumen.models.Position;
import com.practikum.naumen.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository <Position, Long> {
    List<Position> findAll();

    List<Position> findAllByOrderByIdDesc();
    List<Position> findAllByPositionOrderByIdDesc(String filter);
}
