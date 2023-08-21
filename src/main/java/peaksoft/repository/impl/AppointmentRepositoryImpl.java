package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;
import peaksoft.exeptions.MyException;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

@Repository
@Transactional

public class AppointmentRepositoryImpl implements AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager = null;

    @Autowired
    public void AgencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveAppointment(Appointment appointment) throws MyException {
        entityManager.persist(appointment);
    }

    public Appointment getAppointmentById(Long id) throws MyException {
        Appointment appointment = entityManager.find(Appointment.class, id);
        if (appointment == null) throw new MyException("Appointment with id:" + id + "not find");
        return appointment;
    }

    public List<Appointment> getAllAppointments() throws MyException {
        List<Appointment> appointments = entityManager.createQuery("select a from Appointment a", Appointment.class).getResultList();
        if (appointments.isEmpty()) throw new MyException("Appointment is empty");
        return appointments;
    }

    public void updateAppointment(Long id, Appointment updatedAppointment) throws MyException {
        Appointment appointment = getAppointmentById(id);
        if (appointment == null) throw new MyException("Appointment with id:" + id + "not find");
        appointment.setDate(updatedAppointment.getDate());
        appointment.setPatient(updatedAppointment.getPatient());
        appointment.setDoctor(updatedAppointment.getDoctor());
        appointment.setDepartment(updatedAppointment.getDepartment());
    }

    public void deleteAppointment(Long id) throws MyException {
        Appointment appointment = getAppointmentById(id);
        entityManager.remove(appointment);

    }
}
