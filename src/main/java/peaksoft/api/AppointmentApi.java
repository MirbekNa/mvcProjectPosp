package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Appointment;
import peaksoft.exeptions.MyException;
import peaksoft.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentApi {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() throws MyException {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) throws MyException {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/appointments")
    public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment) {
        try {
            appointmentService.saveAppointment(appointment);
            return ResponseEntity.ok("Appointment saved successfully");
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving appointment");
        }
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        try {
            appointmentService.updateAppointment(id, updatedAppointment);
            return ResponseEntity.ok("Appointment updated successfully");
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating appointment");
        }
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment deleted successfully");
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting appointment");
        }
    }

}
