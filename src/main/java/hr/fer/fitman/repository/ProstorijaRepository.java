package hr.fer.fitman.repository;

import hr.fer.fitman.model.Prostorija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProstorijaRepository extends JpaRepository<Prostorija, Long> {
    Prostorija findByOznaka(String oznaka);
}