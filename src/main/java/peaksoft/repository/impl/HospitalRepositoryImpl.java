package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Hospital;
import peaksoft.exeptions.MyException;
import peaksoft.repository.HospitalRepository;

import java.util.List;

@Repository
@Transactional

public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager = null;

    @Autowired
    public void AgencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveHospital(Hospital hospital) throws MyException {
        entityManager.persist(hospital);
    }

    public Hospital getHospitalById(Long id) throws MyException {
        Hospital hospital = entityManager.find(Hospital.class, id);
        if (hospital == null) throw new MyException("Hospital with id:" + id + "not found");
        return hospital;
    }

    public List<Hospital> getAllHospitals() throws MyException {
        List<Hospital> hospitals = entityManager.createQuery("select h from Hospital h").getResultList();
        if (hospitals.isEmpty()) throw new MyException("Can't find any Hospital");
        return hospitals;
    }

    public void updateHospital(Long id, Hospital updatedHospital) throws MyException {
        Hospital hospital = getHospitalById(id);
        hospital.setImage(updatedHospital.getImage());
        hospital.setName(updatedHospital.getName());
        hospital.setDoctors(updatedHospital.getDoctors());
        hospital.setDepartments(updatedHospital.getDepartments());
        hospital.setAppointments(updatedHospital.getAppointments());
        hospital.setPatients(updatedHospital.getPatients());
        hospital.setAddress(updatedHospital.getAddress());
    }

    public void deleteHospital(Long id) throws MyException {
        Hospital hospital = getHospitalById(id);
        entityManager.remove(hospital);
    }
}
