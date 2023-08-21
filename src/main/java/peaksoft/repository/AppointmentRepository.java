package peaksoft.repository;

import peaksoft.entity.Appointment;
import peaksoft.exeptions.MyException;

import java.util.List;

public interface AppointmentRepository {
    void saveAppointment(Appointment appointment) throws MyException;
    Appointment getAppointmentById(Long id) throws MyException;
    List<Appointment> getAllAppointments() throws MyException;
    void updateAppointment(Long id, Appointment updatedAppointment) throws MyException;
    void deleteAppointment(Long id) throws MyException;
}
