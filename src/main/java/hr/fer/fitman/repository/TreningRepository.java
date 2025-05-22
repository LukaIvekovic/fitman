package hr.fer.fitman.repository;

import hr.fer.fitman.model.Trening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreningRepository extends JpaRepository<Trening, Long> {
}