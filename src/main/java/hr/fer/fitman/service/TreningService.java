package hr.fer.fitman.service;

import hr.fer.fitman.dto.TreningFormDTO;
import hr.fer.fitman.model.Prostorija;
import hr.fer.fitman.model.Trener;
import hr.fer.fitman.model.Trening;
import hr.fer.fitman.repository.TrenerRepository;
import hr.fer.fitman.repository.TreningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TreningService {
    private final TreningRepository treningRepository;
    private final TrenerRepository trenerRepository;
    private final ProstorijaService prostorijaService;

    public List<Trening> findAll() {
        return treningRepository.findAll();
    }

    public Optional<Trening> findById(Long id) {
        return treningRepository.findById(id);
    }

    @Transactional
    public Trening saveTrening(TreningFormDTO treningDTO) {
        Trening trening;

        if (treningDTO.getId() != null) {
            trening = treningRepository.findById(treningDTO.getId())
                    .orElse(new Trening());
            trening.getTreneri().clear();
        } else {
            trening = new Trening();
        }

        trening.setNaziv(treningDTO.getNaziv());
        trening.setOpis(treningDTO.getOpis());
        trening.setTezina(treningDTO.getTezina());
        trening.setTrajanje(treningDTO.getTrajanje());
        trening.setMaxBrojPolaznika(treningDTO.getMaxBrojPolaznika());
        trening.setDatum(treningDTO.getDatum());

        if (treningDTO.getProstorijaOznaka() != null) {
            Prostorija prostorija = prostorijaService.findByOznaka(treningDTO.getProstorijaOznaka());
            trening.setProstorija(prostorija);
        }

        if (treningDTO.getTrenerId() != null && !treningDTO.getTrenerId().isEmpty()) {
            Set<Trener> treneri = treningDTO.getTrenerId().stream()
                    .map(id -> trenerRepository.findById(id).orElse(null))
                    .filter(t -> t != null)
                    .collect(Collectors.toSet());

            for (Trener trener : treneri) {
                trening.addTrener(trener);
            }
        }

        return treningRepository.save(trening);
    }

    @Transactional
    public void deleteTrening(Long id) {
        treningRepository.deleteById(id);
    }

    public List<Trening> findByFilters(String naziv, LocalDateTime datumOd, LocalDateTime datumDo) {
        if (naziv != null && !naziv.isEmpty()) {
            return treningRepository.findByNazivContainingIgnoreCaseAndDatumBetween(naziv, datumOd, datumDo);
        } else {
            return treningRepository.findByDatumBetween(datumOd, datumDo);
        }
    }
}