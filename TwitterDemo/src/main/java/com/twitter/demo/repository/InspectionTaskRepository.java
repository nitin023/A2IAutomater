package com.twitter.demo.repository;

import com.twitter.demo.entity.InspectionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InspectionTaskRepository extends JpaRepository<InspectionTask,Integer> {

    @Query(value ="select i from InspectionTask i where i.status <>:status and i.appointmentDate <=:date and i.appointmentDate =:nextDate")
    List<InspectionTask> findByAppointmentDateAndStatus(@Param("status") String status,@Param("date") Date date,@Param("nextDate") Date nextDate);
}
