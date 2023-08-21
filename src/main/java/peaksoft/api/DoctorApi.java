package peaksoft.api;

import peaksoft.entity.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import peaksoft.exeptions.MyException;


import java.util.List;
@RestController
@RequestMapping("/doctors")
public class DoctorApi {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorApi(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() throws MyException {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable Long id) throws MyException {
        return doctorService.getDoctorById(id);
    }

    @PostMapping("/departments/{departmentId}/doctors")
    public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor, @PathVariable Long departmentId) {
        try {
            departmentService.getDepartmentById(departmentId); // Check if department exists
            doctorService.saveDoctor(doctor, departmentId);
            return ResponseEntity.ok("Doctor saved successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error saving doctor: " + e.getMessage());
        }
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor updatedDoctor, @PathVariable Long id) {
        try {
            doctorService.updateDoctor(id, updatedDoctor);
            return ResponseEntity.ok("Doctor updated successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error updating doctor: " + e.getMessage());
        }
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.ok("Doctor deleted successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error deleting doctor: " + e.getMessage());
        }
    }
}
