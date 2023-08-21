package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Data;
import peaksoft.enums.Gender;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_gen")
    @SequenceGenerator(name = "patient_gen", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @NotNull(message = "Field can't be empty")
    private String firstName;

    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @NotNull(message = "Field can't be empty")
    private String lastName;
    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^(?!\\+996)\\d+$", message = "Phone number must not start with +996")
    @Size(max = 15, message = "Phone number must not exceed 15 characters")
    private String phoneNumber;
    @NotNull
    @Positive
    private int age;
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @NotNull(message = "Field can't be empty")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Field can't be empty")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "Field can't be empty")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    @NotNull(message = "Field can't be empty")
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
