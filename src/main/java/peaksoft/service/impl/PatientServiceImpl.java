package peaksoft.service.impl;

import peaksoft.entity.Patient;
import peaksoft.exeptions.MyException;
import peaksoft.repository.PatientRepository;
import peaksoft.repository.impl.PatientRepositoryImpl;
import peaksoft.service.PatientService;

import java.util.List;
import org.springframework.stereotype.Service;
@Service

public class PatientServiceImpl implements PatientService {
    PatientRepository patientRepository = new PatientRepositoryImpl();

    public void savePatient(Patient patient, Long hospitalId) throws MyException {
        patientRepository.savePatient(patient);
    }

    public Patient getPatientById(Long id) throws MyException {
        return patientRepository.getPatientById(id);
    }

    public List<Patient> getAllPatients() throws MyException {
        return patientRepository.getAllPatients();
    }

    public void updatePatient(Long id, Patient updatedPatient) throws MyException {
        patientRepository.updatePatient(id, updatedPatient);
    }

    public void deletePatient(Long id) throws MyException {
        patientRepository.deletePatient(id);
    }
}
