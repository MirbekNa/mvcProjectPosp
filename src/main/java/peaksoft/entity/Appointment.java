package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_gen")
    @SequenceGenerator(name = "appointment_gen", sequenceName = "appointment_seq", allocationSize = 1)
    private Long id;
    @NotNull(message = "Field can't be empty")
    private LocalDate date;
    @NotNull(message = "Field can't be empty")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @NotNull(message = "Field can't be empty")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @NotNull(message = "Field can't be empty")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}