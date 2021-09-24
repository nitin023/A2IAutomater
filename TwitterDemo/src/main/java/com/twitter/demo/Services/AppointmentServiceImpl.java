package com.twitter.demo.Services;

import com.twitter.demo.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twitter.demo.DTO.AppointmentRepository;

import java.util.UUID;

@Service
public class AppointmentServiceImpl  implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void addAppointment(Appointment appointment) {
        UUID uuid = UUID.randomUUID();
        appointment.setId(uuid.toString());
        appointmentRepository.save(appointment);
    }
}
