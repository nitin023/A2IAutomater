package com.a2i.demo.service.serviceImpl;

import com.a2i.demo.entity.Appointment;
import com.a2i.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.a2i.demo.repository.AppointmentRepository;

import java.util.UUID;

@Service
public class AppointmentServiceImpl  implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void addAppointment(Appointment appointment) {
        UUID uuid = UUID.randomUUID();
        appointment.setId(uuid.toString());
        appointmentRepository.save(appointment);
    }
}
