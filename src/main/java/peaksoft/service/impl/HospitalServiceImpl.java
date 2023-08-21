package peaksoft.service.impl;

import peaksoft.entity.Hospital;
import peaksoft.exeptions.MyException;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.impl.HospitalRepositoryImpl;
import peaksoft.service.HospitalService;

import java.util.List;
import org.springframework.stereotype.Service;
@Service

public class HospitalServiceImpl implements HospitalService {
    HospitalRepository repository = new HospitalRepositoryImpl();

    public void saveHospital(Hospital hospital) throws MyException {
        repository.saveHospital(hospital);
    }

    public Hospital getHospitalById(Long id) throws MyException {
        return repository.getHospitalById(id);
    }

    public List<Hospital> getAllHospitals() throws MyException {
        return repository.getAllHospitals();
    }

    public void updateHospital(Long id, Hospital updatedHospital) throws MyException {
        repository.updateHospital(id, updatedHospital);
    }

    public void deleteHospital(Long id) throws MyException {
        repository.deleteHospital(id);
    }

}
