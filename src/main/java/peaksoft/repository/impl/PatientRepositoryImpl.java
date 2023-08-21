package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Patient;
import peaksoft.exeptions.MyException;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;

import java.util.List;

@Repository
@Transactional


public class PatientRepositoryImpl implements PatientRepository {

    @PersistenceContext
    private EntityManager entityManager = null;

    @Autowired
    public void AgencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void savePatient(Patient patient) throws MyException {
        entityManager.persist(patient);
    }

    public Patient getPatientById(Long id) throws MyException {
        Patient patient = entityManager.find(Patient.class, id);
        if (patient == null) throw new MyException("Patient with id not:" + id + "found");
        return patient;
    }

    public List<Patient> getAllPatients() throws MyException {
        List<Patient> patients = entityManager.createQuery("select p from Patient p").getResultList();
        if (patients.isEmpty()) throw new MyException("Your Patients is empty or cant find him");
        return patients;
    }

    public void updatePatient(Long id, Patient updatedPatient) throws MyException {
        Patient patient = getPatientById(id);
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setAppointments(updatedPatient.getAppointments());
        patient.setHospital(updatedPatient.getHospital());
        patient.setLastName(updatedPatient.getLastName());
        patient.setEmail(updatedPatient.getEmail());
        patient.setPhoneNumber(updatedPatient.getPhoneNumber());
    }

    public void deletePatient(Long id) throws MyException {
        Patient patient = getPatientById(id);
        entityManager.remove(patient);
    }
}
