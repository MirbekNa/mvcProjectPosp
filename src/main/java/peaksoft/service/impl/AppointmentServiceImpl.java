package peaksoft.service.impl;

import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;
import peaksoft.exeptions.MyException;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.impl.AppointmentRepositoryImpl;
import peaksoft.service.AppointmentService;

import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {
    AppointmentRepository repository = new AppointmentRepositoryImpl();

    public void saveAppointment(Appointment appointment) throws MyException {
        repository.saveAppointment(appointment);
    }

    public Appointment getAppointmentById(Long id) throws MyException {
        return repository.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments() throws MyException {
        return repository.getAllAppointments();
    }

    public void updateAppointment(Long id, Appointment updatedAppointment) throws MyException {
        repository.updateAppointment(id, updatedAppointment);
    }

    public void deleteAppointment(Long id) throws MyException {
        repository.deleteAppointment(id);
    }

}
