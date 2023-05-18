package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GugunRepository extends JpaRepository<Gugun, Long> {
    List<Gugun> findBySidoCode(int SidoCode);

}
