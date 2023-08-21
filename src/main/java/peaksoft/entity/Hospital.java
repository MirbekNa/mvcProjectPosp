package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_gen")
    @SequenceGenerator(name = "hospital_gen", sequenceName = "hospital_seq", allocationSize = 1)
    private Long id;
    @Column(length = 10000, columnDefinition = "")
    private String image;
    @PrePersist
    public void initializeImage() {
        if (image == null || image.isEmpty()) {
            image = "";
        }
    }
    @NotNull(message = "Field can't be empty")
    private String name;
    @NotNull(message = "Field can't be empty")
    private String address;
    @NotNull(message = "Field can't be empty")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departments;
    @NotNull(message = "Field can't be empty")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @NotNull(message = "Field can't be empty")
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Patient> patients;
    @NotNull(message = "Field can't be empty")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Doctor> doctors;
}
