package peaksoft.service.impl;

import peaksoft.entity.Department;
import peaksoft.exeptions.MyException;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.impl.DepartmentRepositoryImpl;
import peaksoft.service.DepartmentService;

import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository repository = new DepartmentRepositoryImpl();

    public void saveDepartment(Department department,Long hispitalId) throws MyException {
        repository.saveDepartment(department,hispitalId);
    }

    public Department getDepartmentById(Long id) throws MyException {
        return repository.getDepartmentById(id);
    }

    public List<Department> getAllDepartments() throws MyException {
        return repository.getAllDepartments();
    }

    public void updateDepartment(Long id, Department updatedDepartment) throws MyException {
        repository.updateDepartment(id, updatedDepartment);
    }

    public void deleteDepartment(Long id) throws MyException {
        repository.deleteDepartment(id);
    }

}
