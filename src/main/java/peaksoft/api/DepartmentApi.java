package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Department;
import peaksoft.exeptions.MyException;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentApi {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public DepartmentApi(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() throws  MyException {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Long id) throws MyException {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/hospitals/{hospitalId}/departments")
    public ResponseEntity<String> saveDepartment(@RequestBody Department department, @PathVariable Long hospitalId) {
        try {
            hospitalService.getHospitalById(hospitalId); // Check if hospital exists
            departmentService.saveDepartment(department, hospitalId);
            return ResponseEntity.ok("Department saved successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error saving department: " + e.getMessage());
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<String> updateDepartment(@RequestBody Department updatedDepartment, @PathVariable Long id) {
        try {
            departmentService.updateDepartment(id, updatedDepartment);
            return ResponseEntity.ok("Department updated successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error updating department: " + e.getMessage());
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok("Department deleted successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error deleting department: " + e.getMessage());
        }
    }
}
