package hr.fer.fitman.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prostorija")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prostorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oznaka", nullable = false, length = 20)
    private String oznaka;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @Column(name = "kapacitet", nullable = false)
    private Integer kapacitet;

    @Column(name = "opis")
    private String opis;

    @Column(name = "aktivna", nullable = false)
    private Boolean aktivna = true;
}
