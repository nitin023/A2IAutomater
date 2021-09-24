package com.twitter.demo.repository;

import com.twitter.demo.entity.InspectionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

//@Repository
public interface InspectionTaskRepository extends JpaRepository<InspectionTask,Integer> {

    @Query(value = "")
    List<InspectionTask> findByAppointmentDateAndStatus(String status, Date date);
}
