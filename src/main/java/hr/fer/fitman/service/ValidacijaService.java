package hr.fer.fitman.service;

import hr.fer.fitman.model.Trening;
import hr.fer.fitman.repository.TreningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidacijaService {

    private final TreningRepository treningRepository;

    @Autowired
    public ValidacijaService(TreningRepository treningRepository) {
        this.treningRepository = treningRepository;
    }

    /**
     * Checks if a trainer is available for a specific training time slot
     * @param trenerId The trainer to check
     * @param startDateTime The start time of the training
     * @param trajanje Duration of training in minutes
     * @param currentTreningId ID of current training (if editing)
     * @return List of conflicting trainings
     */
    public List<Trening> provjeriDostupnostTrenera(Long trenerId, LocalDateTime startDateTime, Integer trajanje, Long currentTreningId) {
        // Calculate when the training will end
        LocalDateTime endDateTime = startDateTime.plusMinutes(trajanje);

        // Find trainings that overlap with this time slot
        // We search within a window of 2 hours before and after to catch any overlaps
        List<Trening> conflicts = treningRepository.findByTreneriIdAndDatumBetween(
                trenerId,
                startDateTime.minusMinutes(120),  // Look for trainings starting before
                endDateTime.plusMinutes(120)      // and ending after
        );

        // If we're editing an existing training, exclude it from conflicts
        if (currentTreningId != null) {
            conflicts.removeIf(t -> t.getId().equals(currentTreningId));
        }

        // Filter out trainings that don't actually overlap
        conflicts.removeIf(trening -> {
            LocalDateTime trainingEnd = trening.getDatum().plusMinutes(trening.getTrajanje());
            // No conflict if one training ends before the other starts
            return trainingEnd.isBefore(startDateTime) || trening.getDatum().isAfter(endDateTime);
        });

        return conflicts;
    }
}