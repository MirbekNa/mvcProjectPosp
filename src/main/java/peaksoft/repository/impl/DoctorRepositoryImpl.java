package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.exeptions.MyException;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;

import java.util.List;

@Repository
@Transactional


public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager = null;

    @Autowired
    public void AgencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private DepartmentRepository repository = new DepartmentRepositoryImpl();

    public void saveDoctor(Doctor doctor, Long departmentId) throws MyException {
        Department department = repository.getDepartmentById(departmentId);
        department.setDoctors((List<Doctor>) doctor);
        entityManager.merge(doctor);
        entityManager.persist(doctor);
    }


    public Doctor getDoctorById(Long id) throws MyException {
        Doctor doctor = entityManager.find(Doctor.class, id);
        if (doctor == null) throw new MyException("Doctor with id not" + id + " find");
        return doctor;
    }

    public List<Doctor> getAllDoctors() throws MyException {
        List<Doctor> doctors = entityManager.createQuery("select d from Doctor d").getResultList();
        if (doctors == null) throw new MyException("There is no Doctors =|");
        return doctors;
    }

    public void updateDoctor(Long id, Doctor updatedDoctor) throws MyException {
        Doctor doctor = getDoctorById(id);
        doctor.setFirstName(updatedDoctor.getFirstName());
        doctor.setLastName(updatedDoctor.getLastName());
    }

    public void deleteDoctor(Long id) throws MyException {
Doctor doctor = getDoctorById(id);
entityManager.remove(doctor);
    }
}
