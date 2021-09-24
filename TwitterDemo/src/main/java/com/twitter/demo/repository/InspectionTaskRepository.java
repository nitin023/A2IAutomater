package com.twitter.demo.repository;

import com.twitter.demo.entity.InspectionTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InspectionTaskRepository extends CrudRepository<InspectionTask,Integer> {

    @Query(value = "")
    List<InspectionTask> findByAppointmentDateAndStatus(String status, Date date);
}
