package peaksoft.service.impl;

import peaksoft.entity.Doctor;
import peaksoft.exeptions.MyException;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.impl.DoctorRepositoryImpl;
import peaksoft.service.DoctorService;

import java.util.List;
import org.springframework.stereotype.Service;
@Service

public class DoctorServiceImpl implements DoctorService {
    DoctorRepository repository = new DoctorRepositoryImpl();

    public void saveDoctor(Doctor doctor,Long departmentId) throws MyException {
        repository.saveDoctor(doctor,departmentId);
    }

    public Doctor getDoctorById(Long id) throws MyException {
        return repository.getDoctorById(id);
    }

    public List<Doctor> getAllDoctors() throws MyException {
        return repository.getAllDoctors();
    }

    public void updateDoctor(Long id, Doctor updatedDoctor) throws MyException {
        repository.updateDoctor(id, updatedDoctor);
    }

    public void deleteDoctor(Long id) throws MyException {
        repository.deleteDoctor(id);
    }

}
