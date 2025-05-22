package hr.fer.fitman.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trening")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @Column(name = "opis", length = 500)
    private String opis;

    @Column(name = "tezina", nullable = false, length = 20)
    private String tezina;

    @Column(name = "trajanje", nullable = false)
    private Integer trajanje;

    @Column(name = "max_broj_polaznika")
    private Integer maxBrojPolaznika;

    @Column(name = "datum", nullable = false)
    private LocalDateTime datum;

    @ManyToMany
    @JoinTable(
            name = "trening_trener",
            joinColumns = @JoinColumn(name = "trening_id"),
            inverseJoinColumns = @JoinColumn(name = "trener_id")
    )
    private Set<Trener> treneri = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prostorija_id", nullable = false)
    private Prostorija prostorija;

    public void addTrener(Trener trener) {
        this.treneri.add(trener);
        trener.getTreninzi().add(this);
    }

    public void removeTrener(Trener trener) {
        this.treneri.remove(trener);
        trener.getTreninzi().remove(this);
    }
}