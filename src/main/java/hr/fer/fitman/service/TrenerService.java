package hr.fer.fitman.service;

import hr.fer.fitman.model.Trener;
import hr.fer.fitman.repository.TrenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrenerService {

    private final TrenerRepository trenerRepository;

    @Autowired
    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }

    public List<Trener> findAll() {
        return trenerRepository.findAll();
    }

    public Optional<Trener> findById(Long id) {
        return trenerRepository.findById(id);
    }

    public Trener save(Trener trener) {
        return trenerRepository.save(trener);
    }
}