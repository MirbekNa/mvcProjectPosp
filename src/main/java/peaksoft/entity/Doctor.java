package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Data;
import peaksoft.enums.DoctorType;
import peaksoft.enums.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_gen")
    @SequenceGenerator(name = "doctor_gen", sequenceName = "doctor_seq", allocationSize = 1)
    private Long id;
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @NotNull(message = "Field can't be empty")
    private String firstName;
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @NotNull(message = "Field can't be empty")
    private String lastName;
    @NotNull(message = "Field can't be empty")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Field can't be empty")
    private DoctorType position;
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @NotNull(message = "Field can't be empty")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Field can't be empty")
    @ManyToMany(mappedBy = "doctors")
    private List<Department> department;
    @NotNull(message = "Field can't be empty")
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}

