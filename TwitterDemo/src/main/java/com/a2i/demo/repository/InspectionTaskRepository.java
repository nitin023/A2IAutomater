package com.a2i.demo.repository;

import com.a2i.demo.entity.InspectionTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;

@Repository
public interface InspectionTaskRepository extends JpaRepository<InspectionTask,Integer> {

    @Query(value ="select i from InspectionTask i where i.status <>:status and (i.appointmentDate <=:date or i.appointmentDate =:nextDate)")
    List<InspectionTask> findByAppointmentDateAndStatus(@Param("status") String status, @Param("date") Timestamp date, @Param("nextDate") Timestamp nextDate);

    InspectionTask findByAppointmentId(String appointmentId);
}
