package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Patient;
import peaksoft.exeptions.MyException;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientApi {

    private final PatientService patientService;
    private final HospitalService hospitalService;

    @Autowired
    public PatientApi(PatientService patientService, HospitalService hospitalService) {
        this.patientService = patientService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() throws MyException {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) throws MyException {
        return patientService.getPatientById(id);
    }

    @PostMapping("/hospitals/{hospitalId}/patients")
    public ResponseEntity<String> savePatient(@RequestBody Patient patient, @PathVariable Long hospitalId) {
        try {
            hospitalService.getHospitalById(hospitalId); // Check if hospital exists
            patientService.savePatient(patient, hospitalId);
            return ResponseEntity.ok("Patient saved successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error saving patient: " + e.getMessage());
        }
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<String> updatePatient(@RequestBody Patient updatedPatient, @PathVariable Long id) {
        try {
            patientService.updatePatient(id, updatedPatient);
            return ResponseEntity.ok("Patient updated successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error updating patient: " + e.getMessage());
        }
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient deleted successfully");
        } catch (MyException e) {
            return ResponseEntity.badRequest().body("Error deleting patient: " + e.getMessage());
        }
    }
}
