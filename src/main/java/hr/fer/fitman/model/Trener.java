package hr.fer.fitman.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trener")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ime", nullable = false, length = 50)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 50)
    private String prezime;

    @Column(name = "specijalizacija", nullable = false, length = 100)
    private String specijalizacija;

    @Column(name = "godine_iskustva")
    private Integer godineIskustva;

    @Column(name = "certifikati", length = 100)
    private String certifikati;

    @Column(name = "biografija", length = 500)
    private String biografija;

    @ManyToMany(mappedBy = "treneri")
    private Set<Trening> treninzi = new HashSet<>();
}