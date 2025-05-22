package hr.fer.fitman.service;

import hr.fer.fitman.model.Trening;
import hr.fer.fitman.repository.TreningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidacijaService {
    private final TreningRepository treningRepository;

    public List<Trening> provjeriDostupnostTrenera(Long trenerId, LocalDateTime startDateTime, Integer trajanje, Long currentTreningId) {
        LocalDateTime endDateTime = startDateTime.plusMinutes(trajanje);

        List<Trening> conflicts = treningRepository.findByTreneriIdAndDatumBetween(
                trenerId,
                startDateTime.minusMinutes(120),
                endDateTime.plusMinutes(120)
        );

        if (currentTreningId != null) {
            conflicts.removeIf(t -> t.getId().equals(currentTreningId));
        }

        conflicts.removeIf(trening -> {
            LocalDateTime trainingEnd = trening.getDatum().plusMinutes(trening.getTrajanje());
            return trainingEnd.isBefore(startDateTime) || trening.getDatum().isAfter(endDateTime);
        });

        return conflicts;
    }
}