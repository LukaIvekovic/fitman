package hr.fer.fitman.repository;

import hr.fer.fitman.model.Trening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TreningRepository extends JpaRepository<Trening, Long> {
    List<Trening> findByNazivContainingIgnoreCaseAndDatumBetween(String naziv, LocalDateTime datumOd, LocalDateTime datumDo);
    List<Trening> findByDatumBetween(LocalDateTime datumOd, LocalDateTime datumDo);
    List<Trening> findByTreneriIdAndDatumBetween(Long trenerId, LocalDateTime startTime, LocalDateTime endTime);
}